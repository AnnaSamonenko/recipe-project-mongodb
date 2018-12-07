package com.samonenko.recipeproject.controllers;

import com.samonenko.recipeproject.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("recipes", recipeService.getRecipes());
        log.debug("some logging info");
        return "index";
    }

}
