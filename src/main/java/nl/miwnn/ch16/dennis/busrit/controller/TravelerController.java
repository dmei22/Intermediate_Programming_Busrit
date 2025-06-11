package nl.miwnn.ch16.dennis.busrit.controller;

import nl.miwnn.ch16.dennis.busrit.model.Traveler;
import nl.miwnn.ch16.dennis.busrit.repositories.TravelerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/traveler")
public class TravelerController {

    private final TravelerRepository travelerRepository;

    public TravelerController(TravelerRepository travelerRepository) {
        this.travelerRepository = travelerRepository;
    }

    @GetMapping("/overview")
    private String showTravelerOverview(Model datamodel) {
        datamodel.addAttribute("allTravelers", travelerRepository.findAll());
        datamodel.addAttribute("formTraveler", new Traveler());
        return "/travelerOverview";
    }

    @PostMapping("/save")
    private String saveOrUpdateTraveler(@ModelAttribute("formTraveler") Traveler travelerToBeSaved, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            travelerRepository.save(travelerToBeSaved);
        }
        return "redirect:/traveler/overview";
    }

}
