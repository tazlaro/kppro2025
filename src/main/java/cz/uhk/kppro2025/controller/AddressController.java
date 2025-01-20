package cz.uhk.kppro2025.controller;

import cz.uhk.kppro2025.model.Address;
import cz.uhk.kppro2025.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/addresses")
public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public String getAllAddresses(Model model) {
        List<Address> addresses = addressService.getAllAddresses();
        model.addAttribute("addresses", addresses);
        return "address-list";
    }

    @GetMapping("/new")
    public String showNewAddressForm(Model model) {
        Address address = new Address();
        model.addAttribute("address", address);
        return "address-form";
    }

    @PostMapping
    public String saveAddress(@ModelAttribute("address") Address address) {
        addressService.saveAddress(address);
        return "redirect:/addresses";
    }

    @GetMapping("/edit/{id}")
    public String showEditAddressForm(@PathVariable("id") Long id, Model model) {
        Address address = addressService.getAddressById(id);
        model.addAttribute("address", address);
        return "address-form";
    }

    @PostMapping("/update/{id}")
    public String updateAddress(@PathVariable("id") Long id, @ModelAttribute("address") Address address) {
        address.setId(id);
        addressService.saveAddress(address);
        return "redirect:/addresses";
    }

    @GetMapping("/delete/{id}")
    public String deleteAddress(@PathVariable("id") Long id) {
        addressService.deleteAddressById(id);
        return "redirect:/addresses";
    }
}
