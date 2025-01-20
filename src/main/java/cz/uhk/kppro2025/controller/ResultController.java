package cz.uhk.kppro2025.controller;

import cz.uhk.kppro2025.model.Result;
import cz.uhk.kppro2025.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/results")
public class ResultController {

    private final ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService) {
        this.resultService = resultService;
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
        return "result-form";
    }

    @PostMapping
    public String saveResult(@ModelAttribute("result") Result result) {
        resultService.saveResult(result);
        return "redirect:/results";
    }

    @GetMapping("/edit/{id}")
    public String showEditResultForm(@PathVariable("id") Long id, Model model) {
        Result result = resultService.getResultById(id);
        model.addAttribute("result", result);
        return "result-form";
    }

    @PostMapping("/update/{id}")
    public String updateResult(@PathVariable("id") Long id, @ModelAttribute("result") Result result) {
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
