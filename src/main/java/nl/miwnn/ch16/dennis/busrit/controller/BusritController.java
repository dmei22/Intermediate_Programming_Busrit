package nl.miwnn.ch16.dennis.busrit.controller;

import nl.miwnn.ch16.dennis.busrit.model.Bus;
import nl.miwnn.ch16.dennis.busrit.repositories.BusRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BusritController {

    private final BusRepository busRepository;

    public BusritController(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @GetMapping("/")
    private String showBusOverview(Model dataModel) {
        dataModel.addAttribute("allBusses", busRepository.findAll());

        return "busOverview";
    }

    @PostMapping("/bus/new")
    private String showNewBusForm(Model dataModel) {
        return "";
    }

}

