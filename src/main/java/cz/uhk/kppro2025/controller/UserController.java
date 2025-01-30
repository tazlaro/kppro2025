package cz.uhk.kppro2025.controller;

import cz.uhk.kppro2025.model.Address;
import cz.uhk.kppro2025.model.Club;
import cz.uhk.kppro2025.model.User;
import cz.uhk.kppro2025.service.AddressService;
import cz.uhk.kppro2025.service.ClubService;
import cz.uhk.kppro2025.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AddressService addressService;
    private final ClubService clubService;

    @Autowired
    public UserController(UserService userService, AddressService addressService, ClubService clubService) {
        this.userService = userService;
        this.addressService = addressService;
        this.clubService = clubService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/new")
    public String showNewUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        List<Club> clubs = clubService.getAllClubs();
        model.addAttribute("clubs", clubs);
        return "user-form";
    }

    @PostMapping
    public String saveUser(@Valid  @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            List<Club> clubs = clubService.getAllClubs();
            model.addAttribute("clubs", clubs);
            return "user-form";
        }
        Address address = user.getAddress();
        if (address != null) {
            addressService.saveAddress(address);
        }
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        List<Club> clubs = clubService.getAllClubs();
        model.addAttribute("clubs", clubs);
        return "user-form";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "user-form";
        }
        user.setId(id);
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/detail/{id}")
    public String showUserDetail(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-detail";
    }
}
