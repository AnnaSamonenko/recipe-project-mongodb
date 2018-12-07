package com.samonenko.recipeproject.services;

import com.samonenko.recipeproject.dto.IngredientDTO;

public interface IngredientService {

    IngredientDTO findIngredientByIds(Long recipeId, Long ingredientId);

    void deleteIngredientByIds(Long recipeId, Long ingredientId);

    IngredientDTO save(IngredientDTO ingredientDTO);
}
