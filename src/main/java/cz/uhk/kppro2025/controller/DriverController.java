package cz.uhk.kppro2025.controller;

import cz.uhk.kppro2025.model.Driver;
import cz.uhk.kppro2025.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DriverController {

    private DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService){
        this.driverService = driverService;
    }

    @GetMapping("/driverDetail/{id}")
    public String driverDetail(@PathVariable Long id, Model model){
        Driver driver = driverService.getDriver(id);
        model.addAttribute("driver", driver);
        model.addAttribute("cars", driver.getCars());
        return "driver_detail";
    }

    @GetMapping("/driverEdit/{id}")
    public String driverEdit(@PathVariable Long id, Model model){
        model.addAttribute("driver", driverService.getDriver(id));
        model.addAttribute("edit", true);
        return "driver_edit";
    }

    @GetMapping("/driverCreate")
    public String driverCreate(Model model){
        model.addAttribute("driver", new Driver());
        model.addAttribute("edit", false);
        return "driver_edit";
    }

    @GetMapping("/driverDelete/{id}")
    public String driverDelete(@PathVariable Long id){
        driverService.deleteDriver(id);
        return "redirect:/list";
    }

    @PostMapping("/driverSave")
    public String driverSave(@Valid Driver driver, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", false);
            return "driver_edit";
        }
        driverService.addDriver(driver);
        return "redirect:/list";
    }

    @PostMapping("/driverUpdate")
    public String driverUpdate(@Valid Driver driver, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "driver_edit";
        }
        driverService.updateDriver(driver);
        return "redirect:/list";
    }
}
