package nl.miwnn.ch16.dennis.busrit.controller;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import nl.miwnn.ch16.dennis.busrit.model.Bus;
import nl.miwnn.ch16.dennis.busrit.model.Route;
import nl.miwnn.ch16.dennis.busrit.model.Traveler;
import nl.miwnn.ch16.dennis.busrit.repositories.BusRepository;
import nl.miwnn.ch16.dennis.busrit.repositories.RouteRepository;
import nl.miwnn.ch16.dennis.busrit.repositories.TravelerRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Controller
public class InitializeController {
    private final BusRepository busRepository;
    private final RouteRepository routeRepository;
    private final TravelerRepository travelerRepository;
    private final Map<String, Traveler> travelerCache = new HashMap<>();

    public InitializeController(BusRepository busRepository, RouteRepository routeRepository, TravelerRepository travelerRepository) {
        this.busRepository = busRepository;
        this.routeRepository = routeRepository;
        this.travelerRepository = travelerRepository;
    }

    @EventListener
    private void seed(ContextRefreshedEvent ignoredEvent) {
        if (travelerRepository.count() == 0) {
            initializeDB();
        }
    }

    private void initializeDB() {
        try {
            loadTravelers();
            loadBusses();
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("Failed to initialize database from csv files", e);
        }

    }

    private void loadTravelers() throws IOException, CsvValidationException {

        try (CSVReader reader = new CSVReader(new InputStreamReader(
            new ClassPathResource("/example_data/travelers.csv").getInputStream()))) {

            reader.skip(1);

            for (String[] line : reader) {
                Traveler traveler = new Traveler();
                traveler.setName(line[0]);
                travelerRepository.save(traveler);
                travelerCache.put(traveler.getName(), traveler);

            }

        }
    }

    private void loadBusses() throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
                new ClassPathResource("/example_data/busses.csv").getInputStream()))) {

            reader.skip(1);

            for (String[] line : reader) {
                Bus bus  = new Bus();
                bus.setLineNumber(Integer.parseInt(line[0]));
                bus.setRegion(line[1]);
                bus.setNumberOfSeats(Integer.parseInt(line[2]));
                bus.setImageUrl(line[3]);

                Set<Traveler> travelers = new HashSet<>();
                String[] travelerNames = line[4].split(",");
                for (String travelerName : travelerNames) {
                    travelers.add(travelerCache.get(travelerName.trim()));
                }
                bus.setTravelers(travelers);

                busRepository.save(bus);

                createRoutes(line, bus);
            }
        }
    }

    private void createRoutes(String[] busLine, Bus bus) {
        int totalRoutes = Integer.parseInt(busLine[5]);
        int operatingRoutes = Integer.parseInt(busLine[6]);

        for (int index = 0; index < totalRoutes; index++) {
            Route route = new Route();
            route.setBus(bus);
            route.setOperating(operatingRoutes-- <= 0);
            routeRepository.save(route);
        }
    }

}
