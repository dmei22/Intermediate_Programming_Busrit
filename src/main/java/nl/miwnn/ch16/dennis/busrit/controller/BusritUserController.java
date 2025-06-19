package nl.miwnn.ch16.dennis.busrit.controller;

import nl.miwnn.ch16.dennis.busrit.dto.NewBusritUserDTO;
import nl.miwnn.ch16.dennis.busrit.service.BusritUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class BusritUserController {

    private final BusritUserService busritUserService;

    public BusritUserController(BusritUserService busritUserService) {
        this.busritUserService = busritUserService;
    }

    @GetMapping("/overview")
    private String showUserOverview(Model dataModel) {
        dataModel.addAttribute("allUsers", busritUserService.getAllUsers());
        return "userOverview";
    }

    @GetMapping("/new")
    private String newUser(Model dataModel) {
        dataModel.addAttribute("userForm", new NewBusritUserDTO());
        return "userForm";
    }

    @PostMapping("/save")
    private String saveOrUpdate(@ModelAttribute("userForm") NewBusritUserDTO userDtoToBeSaved,
                                BindingResult bindingResult) {

        if (busritUserService.usernameInUse(userDtoToBeSaved.getUsername())) {
            bindingResult.rejectValue("username", "duplicate", "This username is not available");
            System.err.println("duplicate username");
        }

        if (!userDtoToBeSaved.getPassword().equals(userDtoToBeSaved.getPasswordConfirm())) {
            bindingResult.rejectValue("password", "no.match", "The passwords do not match");
        }

        if (bindingResult.hasErrors()) {
            return "userForm";
        }

        busritUserService.save(userDtoToBeSaved);
        return "redirect:/user/overview";
    }

}
