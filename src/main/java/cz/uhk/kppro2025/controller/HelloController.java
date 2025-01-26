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

    // new home page - template home.html (with navigation)
    @GetMapping("/")
    public String main(){
        return "home";
    }

    // changed from main() (with mapping "/") to list() (with mapping "/list")
    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("drivers", driverService.getAllDrivers());
        return "list";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/403")
    public String error403(){
        return "403";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }


//    @GetMapping("/403")
//    @ResponseBody
//    public String forbidden(){
//        return "<h1 style=\"color: red;\">Access Denied</h1>";
//    }
//
//    @GetMapping("/admin")
//    @ResponseBody
//    public String admin(){
//        return "<h1 style=\"color: green;\">Admin Section</h1>";
//    }

}
