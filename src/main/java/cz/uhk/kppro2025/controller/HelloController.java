package cz.uhk.kppro2025.controller;

import cz.uhk.kppro2025.service.CarService;
import cz.uhk.kppro2025.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    private CarService carService;
    private DriverService driverService;

    @Autowired
    public HelloController(CarService carService, DriverService driverService){
        this.carService = carService;
        this.driverService = driverService;
    }

    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("drivers", driverService.getAllDrivers());
        return "list";
    }

}
