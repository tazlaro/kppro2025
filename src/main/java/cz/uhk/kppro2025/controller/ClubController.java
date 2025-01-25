package cz.uhk.kppro2025.controller;

import cz.uhk.kppro2025.model.Club;
import cz.uhk.kppro2025.service.ClubService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/clubs")
public class ClubController {

    private ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
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
    public String saveClub(@Valid @ModelAttribute("club") Club club, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "club-form";
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
    public String updateClub(@PathVariable("id") Long id, @Valid @ModelAttribute("club") Club club, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
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
}
