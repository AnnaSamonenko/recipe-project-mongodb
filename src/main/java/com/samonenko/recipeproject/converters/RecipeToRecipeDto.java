package com.samonenko.recipeproject.converters;

import com.samonenko.recipeproject.domain.Recipe;
import com.samonenko.recipeproject.dto.CategoryDTO;
import com.samonenko.recipeproject.dto.IngredientDTO;
import com.samonenko.recipeproject.dto.RecipeDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RecipeToRecipeDto implements Converter<Recipe, RecipeDTO> {

    private final NoteToNoteDto noteConverter;
    private final CategoryToCategoryDto categoryConverter;
    private final IngredientToIngredientDto ingredientConverter;

    public RecipeToRecipeDto(NoteToNoteDto noteConverter, CategoryToCategoryDto categoryConverter, IngredientToIngredientDto ingredientConverter) {
        this.noteConverter = noteConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Override
    public RecipeDTO convert(Recipe recipe) {
        if (recipe == null)
            return null;
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(recipe.getId());
        recipeDTO.setCookTime(recipe.getCookTime());
        recipeDTO.setPrepTime(recipe.getPrepTime());
        recipeDTO.setDescription(recipe.getDescription());
        recipeDTO.setDifficulty(recipe.getDifficulty());
        recipeDTO.setDirection(recipe.getDirection());
        recipeDTO.setServings(recipe.getServings());
        recipeDTO.setSource(recipe.getSource());
        recipeDTO.setUrl(recipe.getUrl());
        recipeDTO.setImage(recipe.getImage());
        recipeDTO.setNote(noteConverter.convert(recipe.getNote()));

        Set<CategoryDTO> categoriesDto = recipe.getCategories().stream().map(c -> categoryConverter.convert(c)).collect(Collectors.toSet());
        recipeDTO.setCategories(categoriesDto);

        Set<IngredientDTO> ingredientsDto = recipe.getIngredients().stream().map(i -> ingredientConverter.convert(i)).collect(Collectors.toSet());
        recipeDTO.setIngredients(ingredientsDto);

        return recipeDTO;
    }
}
