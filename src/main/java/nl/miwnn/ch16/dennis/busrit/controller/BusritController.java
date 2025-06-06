package nl.miwnn.ch16.dennis.busrit.controller;

import nl.miwnn.ch16.dennis.busrit.model.Bus;
import nl.miwnn.ch16.dennis.busrit.repositories.BusRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BusritController {

    private final BusRepository busRepository;

    public BusritController(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @GetMapping({"/", "/bus/overview"})
    private String showBusOverview(Model dataModel) {
        dataModel.addAttribute("allBusses", busRepository.findAll());

        return "busOverview";
    }

    @GetMapping("/bus/new")
    private String showNewBusForm(Model dataModel) {
        dataModel.addAttribute("busForm", new Bus());

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

}

