package com.samonenko.recipeproject.controllers;

import com.samonenko.recipeproject.dto.RecipeDTO;
import com.samonenko.recipeproject.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeById(Long.valueOf(id)));
        return "recipe/show";
    }

    @GetMapping("/recipe/new")
    public String addRecipe(Model model) {
        model.addAttribute("recipe", new RecipeDTO());
        return "recipe/recipe_form";
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeById(id));
        return "recipe/recipe_form";
    }

    @PostMapping("/recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeDTO recipeDTO, BindingResult result) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return "recipe/recipe_form";
        }

        RecipeDTO savedRecipeDto = recipeService.saveRecipe(recipeDTO);
        return "redirect:/recipe/" + savedRecipeDto.getId() + "/show";
    }

    @DeleteMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        recipeService.deleteById(id);
        return "redirect:/";
    }

}
