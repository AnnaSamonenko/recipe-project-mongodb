package com.samonenko.recipeproject.services;

import com.samonenko.recipeproject.converters.RecipeDtoToRecipe;
import com.samonenko.recipeproject.converters.RecipeToRecipeDto;
import com.samonenko.recipeproject.domain.Recipe;
import com.samonenko.recipeproject.dto.RecipeDTO;
import com.samonenko.recipeproject.exceptions.NotFoundException;
import com.samonenko.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Transactional
    public RecipeDTO findRecipeById(Long l) {
        Optional<Recipe> recipe = recipeRepository.findById(l);
        if (!recipe.isPresent())
            throw new NotFoundException("Recipe not found for id" + l);
        return converterRecipeToRecipeDto.convert(recipe.get());
    }

    @Override
    @Transactional
    public RecipeDTO saveRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = converterRecipeDtoToRecipe.convert(recipeDTO);
        Recipe savedRecipe = recipeRepository.save(recipe);
        log.debug("Saved recipe with id " + savedRecipe.getId());
        return converterRecipeToRecipeDto.convert(savedRecipe);
    }

    @Transactional
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
