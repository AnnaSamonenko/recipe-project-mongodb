package com.samonenko.recipeproject.services;

import com.samonenko.recipeproject.dto.IngredientDTO;

public interface IngredientService {

    IngredientDTO findIngredientByIds(String recipeId, Long ingredientId);

    void deleteIngredientByIds(String recipeId, Long ingredientId);

    IngredientDTO save(IngredientDTO ingredientDTO);
}
