package com.samonenko.recipeproject.services;

import com.samonenko.recipeproject.domain.Recipe;
import com.samonenko.recipeproject.dto.RecipeDTO;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    RecipeDTO findRecipeById(Long id);

    RecipeDTO saveRecipe(RecipeDTO recipeDTO);

    void deleteById(Long id);

}
