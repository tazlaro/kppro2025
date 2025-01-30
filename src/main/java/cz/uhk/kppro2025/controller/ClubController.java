package cz.uhk.kppro2025.controller;

import cz.uhk.kppro2025.model.*;
import cz.uhk.kppro2025.service.AddressService;
import cz.uhk.kppro2025.service.ClubService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/clubs")
public class ClubController {

    private ClubService clubService;
    private AddressService addressService;

    @Autowired
    public ClubController(ClubService clubService, AddressService addressService) {
        this.clubService = clubService;
        this.addressService = addressService;
    }

    @GetMapping
    public String getAllClubs(Model model) {
        List<Club> clubs = clubService.getAllClubs();
        model.addAttribute("clubs", clubs);
        return "club-list";
    }

    @GetMapping("/new")
    public String showNewClubForm(Model model) {
        Club club = new Club();
        model.addAttribute("club", club);
        return "club-form";
    }

    @PostMapping
    public String saveClub(@Valid @ModelAttribute("club") Club club, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("club", club);
            return "club-form";
        }
        Address address = club.getAddress();
        if (address != null) {
            addressService.saveAddress(address);
        }
        clubService.saveClub(club);
        return "redirect:/clubs";
    }

    @GetMapping("/edit/{id}")
    public String showEditClubForm(@PathVariable("id") Long id, Model model) {
        Club club = clubService.getClubById(id);
        model.addAttribute("club", club);
        return "club-form";
    }

    @PostMapping("/update/{id}")
    public String updateClub(@PathVariable("id") Long id, @Valid @ModelAttribute("club") Club club, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("club", club);
            return "club-form";
        }
        club.setId(id);
        clubService.saveClub(club);
        return "redirect:/clubs";
    }

    @GetMapping("/delete/{id}")
    public String deleteClub(@PathVariable("id") Long id) {
        clubService.deleteClubById(id);
        return "redirect:/clubs";
    }

    @GetMapping("/detail/{id}")
    public String showClubDetail(@PathVariable("id") Long id, Model model) {
        Club club = clubService.getClubById(id);

        // Sort the users by Last Name and then First Name
        List<User> sortedUsers = club.getUsers().stream()
                .sorted(Comparator.comparing(User::getLastName)
                        .thenComparing(User::getFirstName))
                .collect(Collectors.toList());
        club.setUsers(sortedUsers);

        // Sort the competitions by date
        List<Competition> sortedCompetitions = club.getCompetitions().stream()
                .sorted(Comparator.comparing(Competition::getDate))
                .collect(Collectors.toList());
        club.setCompetitions(sortedCompetitions);

        model.addAttribute("club", club);
        return "club-detail";
    }
}
