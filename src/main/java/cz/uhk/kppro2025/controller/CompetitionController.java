package cz.uhk.kppro2025.controller;

import cz.uhk.kppro2025.model.Club;
import cz.uhk.kppro2025.model.Competition;
import cz.uhk.kppro2025.service.ClubService;
import cz.uhk.kppro2025.service.CompetitionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;
    private final ClubService clubService;

    @Autowired
    public CompetitionController(CompetitionService competitionService, ClubService clubService) {
        this.competitionService = competitionService;
        this.clubService = clubService;
    }

    @GetMapping
    public String getAllCompetitions(Model model) {
        List<Competition> competitions = competitionService.getAllCompetitions();
        model.addAttribute("competitions", competitions);
        return "competition-list";
    }

    @GetMapping("/new")
    public String showNewCompetitionForm(Model model) {
        Competition competition = new Competition();
        model.addAttribute("competition", competition);

        List<Club> clubs = clubService.getAllClubs();
        model.addAttribute("clubs", clubs);

        return "competition-form";
    }

    @PostMapping
    public String saveCompetition(@Valid @ModelAttribute("competition") Competition competition, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Club> clubs = clubService.getAllClubs();
            model.addAttribute("clubs", clubs);
            return "competition-form";
        }
        competitionService.saveCompetition(competition);
        return "redirect:/competitions";
    }

    @GetMapping("/edit/{id}")
    public String showEditCompetitionForm(@PathVariable("id") Long id, Model model) {
        Competition competition = competitionService.getCompetitionById(id);
        model.addAttribute("competition", competition);
        List<Club> clubs = clubService.getAllClubs();
        model.addAttribute("clubs", clubs);
        return "competition-form";
    }

    @PostMapping("/update/{id}")
    public String updateCompetition(@PathVariable("id") Long id, @Valid @ModelAttribute("competition") Competition competition, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Club> clubs = clubService.getAllClubs();
            model.addAttribute("clubs", clubs);
            return "competition-form";
        }
        competition.setId(id);
        competitionService.saveCompetition(competition);
        return "redirect:/competitions";
    }

    @GetMapping("/delete/{id}")
    public String deleteCompetition(@PathVariable("id") Long id) {
        competitionService.deleteCompetitionById(id);
        return "redirect:/competitions";
    }

    @GetMapping("/detail/{id}")
    public String showCompetitionDetail(@PathVariable("id") Long id, Model model) {
        Competition competition = competitionService.getCompetitionById(id);
        model.addAttribute("competition", competition);
        return "competition-detail";
    }
}
