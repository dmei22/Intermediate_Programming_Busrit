package nl.miwnn.ch16.dennis.busrit.controller;

import nl.miwnn.ch16.dennis.busrit.model.Bus;
import nl.miwnn.ch16.dennis.busrit.model.Route;
import nl.miwnn.ch16.dennis.busrit.repositories.BusRepository;
import nl.miwnn.ch16.dennis.busrit.repositories.RouteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/route")
public class RouteController {
    private final RouteRepository routeRepository;
    private final BusRepository busRepository;

    public RouteController(RouteRepository routeRepository, BusRepository busRepository) {
        this.routeRepository = routeRepository;
        this.busRepository = busRepository;
    }

    @GetMapping("/new/{busId}")
    private String createNewRoute(@PathVariable("busId") Long busId) {
        Optional<Bus> optionalBus = busRepository.findById(busId);

        if (optionalBus.isPresent()) {
            Route route = new Route(optionalBus.get());
            routeRepository.save(route);
        }

        return "redirect:/";
    }

    @GetMapping("/route/toggle/{routeId}")
    private String toggleRoute(@PathVariable("routeId") Long routeId) {
        Optional<Route> routeOptional = routeRepository.findById(routeId);

        if (routeOptional.isPresent()) {
            boolean isOperating = routeOptional.get().getOperating();

            if (isOperating) {
                routeOptional.get().setOperating(false);
            } else {
                routeOptional.get().setOperating(true);
            }

            routeRepository.save(routeOptional.get());
        }

        return "redirect:/bus/overview/";
    }
}
