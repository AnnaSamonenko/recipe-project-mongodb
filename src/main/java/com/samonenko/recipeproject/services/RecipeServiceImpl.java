package com.samonenko.recipeproject.services;

import com.samonenko.recipeproject.converters.RecipeDtoToRecipe;
import com.samonenko.recipeproject.converters.RecipeToRecipeDto;
import com.samonenko.recipeproject.domain.Recipe;
import com.samonenko.recipeproject.dto.RecipeDTO;
import com.samonenko.recipeproject.exceptions.NotFoundException;
import com.samonenko.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeDtoToRecipe converterRecipeDtoToRecipe;
    private final RecipeToRecipeDto converterRecipeToRecipeDto;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeDtoToRecipe converterRecipeDtoToRecipe, RecipeToRecipeDto converterRecipeToRecipeDto) {
        this.recipeRepository = recipeRepository;
        this.converterRecipeDtoToRecipe = converterRecipeDtoToRecipe;
        this.converterRecipeToRecipeDto = converterRecipeToRecipeDto;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("Im in the getRecipes()");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    public RecipeDTO findRecipeById(String id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (!recipe.isPresent())
            throw new NotFoundException("Recipe not found for id" + id);
        return converterRecipeToRecipeDto.convert(recipe.get());
    }

    @Override
    public RecipeDTO saveRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = converterRecipeDtoToRecipe.convert(recipeDTO);
        Recipe savedRecipe = recipeRepository.save(recipe);
        log.debug("Saved recipe with id " + savedRecipe.getId());
        return converterRecipeToRecipeDto.convert(savedRecipe);
    }

    public void deleteById(String id) {
        recipeRepository.deleteById(id);
    }
}
