package nl.miwnn.ch16.dennis.busrit.controller;

import jakarta.servlet.http.HttpServletRequest;
import nl.miwnn.ch16.dennis.busrit.model.Bus;
import nl.miwnn.ch16.dennis.busrit.model.Route;
import nl.miwnn.ch16.dennis.busrit.repositories.BusRepository;
import nl.miwnn.ch16.dennis.busrit.repositories.RouteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/toggle/{routeId}")
    private String toggleOperating(@PathVariable("routeId") Long routeId, HttpServletRequest request) {
        Optional<Route> routeOptional = routeRepository.findById(routeId);
        if (routeOptional.isPresent()) {
            routeOptional.get().toggleOperating();
            routeRepository.save(routeOptional.get());
        }
        return "redirect:" + request.getHeader("Referer");
    }
}
