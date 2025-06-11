package nl.miwnn.ch16.dennis.busrit.controller;

import nl.miwnn.ch16.dennis.busrit.model.Bus;
import nl.miwnn.ch16.dennis.busrit.repositories.BusRepository;
import nl.miwnn.ch16.dennis.busrit.repositories.TravelerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BusController {

    private final BusRepository busRepository;
    private final TravelerRepository travelerRepository;

    public BusController(BusRepository busRepository, TravelerRepository travelerRepository) {
        this.busRepository = busRepository;
        this.travelerRepository = travelerRepository;
    }

    @GetMapping({"/", "/bus/overview"})
    private String showBusOverview(Model dataModel) {
        dataModel.addAttribute("allBusses", busRepository.findAll());

        return "busOverview";
    }

    @GetMapping("/bus/new")
    private String showNewBusForm(Model dataModel) {
        dataModel.addAttribute("busForm", new Bus());
        dataModel.addAttribute("allTravelers", travelerRepository.findAll());

        return "busForm";
    }

    @PostMapping("/bus/save")
    private String saveOrUpdateBus(@ModelAttribute("busForm") Bus busToBeSaved, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.err.println(bindingResult.getAllErrors());
        } else {
            busRepository.save(busToBeSaved);
        }

        return "redirect:/bus/overview";
    }

    @GetMapping("/bus/delete/{busId}")
    private String deleteBus(@PathVariable("busId") Long busId) {
        busRepository.deleteById(busId);

        return "redirect:/bus/overview";
    }
}

