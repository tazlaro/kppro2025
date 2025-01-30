package cz.uhk.kppro2025.controller;

import cz.uhk.kppro2025.model.Result;
import cz.uhk.kppro2025.service.CompetitionService;
import cz.uhk.kppro2025.service.ResultService;
import cz.uhk.kppro2025.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/results")
public class ResultController {

    private final ResultService resultService;
    private final CompetitionService competitionService;
    private final UserService userService;

    @Autowired
    public ResultController(ResultService resultService, CompetitionService competitionService, UserService userService) {
        this.resultService = resultService;
        this.competitionService = competitionService;
        this.userService = userService;
    }

    @GetMapping
    public String getAllResults(Model model) {
        List<Result> results = resultService.getAllResults();
        model.addAttribute("results", results);
        return "result-list";
    }

    @GetMapping("/new")
    public String showNewResultForm(Model model) {
        Result result = new Result();
        model.addAttribute("result", result);
        model.addAttribute("competitions", competitionService.getAllCompetitions());
        model.addAttribute("users", userService.getAllUsers());
        return "result-form";
    }

    @PostMapping
    public String saveResult(@Valid @ModelAttribute("result") Result result, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("result", result);
            model.addAttribute("competitions", competitionService.getAllCompetitions());
            model.addAttribute("users", userService.getAllUsers());
            return "result-form";
        }
        resultService.saveResult(result);
        return "redirect:/results";
    }

    @GetMapping("/edit/{id}")
    public String showEditResultForm(@PathVariable("id") Long id, Model model) {
        Result result = resultService.getResultById(id);
        model.addAttribute("result", result);
        model.addAttribute("competitions", competitionService.getAllCompetitions());
        model.addAttribute("users", userService.getAllUsers());
        return "result-form";
    }

    @PostMapping("/update/{id}")
    public String updateResult(@PathVariable("id") Long id, @Valid @ModelAttribute("result") Result result, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "result-form";
        }
        result.setId(id);
        resultService.saveResult(result);
        return "redirect:/results";
    }

    @GetMapping("/delete/{id}")
    public String deleteResult(@PathVariable("id") Long id) {
        resultService.deleteResultById(id);
        return "redirect:/results";
    }
}
