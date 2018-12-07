package com.samonenko.recipeproject.converters;

import com.samonenko.recipeproject.domain.Category;
import com.samonenko.recipeproject.domain.Ingredient;
import com.samonenko.recipeproject.domain.Recipe;
import com.samonenko.recipeproject.dto.RecipeDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RecipeDtoToRecipe implements Converter<RecipeDTO, Recipe> {

    private final NoteDtoToNote noteConverter;

    private final IngredientDtoToIngredient ingredientConverter;

    private final CategoryDtoToCategory categoryConverter;

    public RecipeDtoToRecipe(NoteDtoToNote noteConverter, IngredientDtoToIngredient ingredientConverter, CategoryDtoToCategory categoryConverter) {
        this.noteConverter = noteConverter;
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public Recipe convert(RecipeDTO recipeDTO) {
        if (recipeDTO == null)
            return null;
        Recipe recipe = new Recipe();
        recipe.setId(recipeDTO.getId());
        recipe.setCookTime(recipeDTO.getCookTime());
        recipe.setPrepTime(recipeDTO.getPrepTime());
        recipe.setDescription(recipeDTO.getDescription());
        recipe.setDifficulty(recipeDTO.getDifficulty());
        recipe.setDirection(recipeDTO.getDirection());
        recipe.setServings(recipeDTO.getServings());
        recipe.setSource(recipeDTO.getSource());
        recipe.setUrl(recipeDTO.getUrl());
        recipe.setImage(recipeDTO.getImage());
        recipe.setNote(noteConverter.convert(recipeDTO.getNote()));

        // add check on null
        if (recipeDTO.getIngredients() != null) {
            Set<Ingredient> ingredients = recipeDTO.getIngredients().stream().map(i -> ingredientConverter.convert(i)).collect(Collectors.toSet());
            recipe.setIngredients(ingredients);
        }

        // add check on null
        if (recipeDTO.getCategories() != null) {
            Set<Category> categories = recipeDTO.getCategories().stream().map(c -> categoryConverter.convert(c)).collect(Collectors.toSet());
            recipe.setCategories(categories);
        }

        return recipe;
    }
}
