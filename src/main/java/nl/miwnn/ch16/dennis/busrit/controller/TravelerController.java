package nl.miwnn.ch16.dennis.busrit.controller;

import nl.miwnn.ch16.dennis.busrit.model.Bus;
import nl.miwnn.ch16.dennis.busrit.model.Traveler;
import nl.miwnn.ch16.dennis.busrit.repositories.BusRepository;
import nl.miwnn.ch16.dennis.busrit.repositories.TravelerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/traveler")
public class TravelerController {

    private final TravelerRepository travelerRepository;
    private final BusRepository busRepository;

    public TravelerController(TravelerRepository travelerRepository, BusRepository busRepository) {
        this.travelerRepository = travelerRepository;
        this.busRepository = busRepository;
    }

    @GetMapping("/overview")
    private String showTravelerOverview(Model datamodel) {
        datamodel.addAttribute("allTravelers", travelerRepository.findAll());
        datamodel.addAttribute("formTraveler", new Traveler());
        return "/travelerOverview";
    }

    @GetMapping("/delete/{travelerId}")
    private String deleteTraveler(@PathVariable("travelerId") Long travelerId) {
        //
        Optional<Traveler> travelerOptional = travelerRepository.findById(travelerId);

        List<Bus> buses = busRepository.findAll();
        for (Bus bus : buses) {
            if (travelerOptional.isPresent()) {
                bus.getTravelers().remove(travelerOptional.get());
                busRepository.save(bus);
            }
        }
        travelerRepository.deleteById(travelerId);

        return "redirect:/traveler/overview";
    }

    @PostMapping("/save")
    private String saveOrUpdateTraveler(@ModelAttribute("formTraveler") Traveler travelerToBeSaved, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            travelerRepository.save(travelerToBeSaved);
        }
        return "redirect:/traveler/overview";
    }

}
