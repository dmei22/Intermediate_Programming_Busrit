package nl.miwnn.ch16.dennis.busrit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BusritController {

    @GetMapping
    private String showBusOverview(Model dataModel) {
        dataModel.addAttribute("attribute", "value");

        return "busOverview";
    }

}
