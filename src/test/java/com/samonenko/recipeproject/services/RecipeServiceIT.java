package com.samonenko.recipeproject.services;


import com.samonenko.recipeproject.converters.RecipeDtoToRecipe;
import com.samonenko.recipeproject.converters.RecipeToRecipeDto;
import com.samonenko.recipeproject.domain.Recipe;
import com.samonenko.recipeproject.dto.RecipeDTO;
import com.samonenko.recipeproject.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeToRecipeDto recipeToRecipeDto;

    @Autowired
    private RecipeDtoToRecipe recipeDtoToRecipe;

    @Autowired
    private RecipeRepository recipeRepository;

    private Recipe recipe;

    @Before
    public void setUp() {
        recipeService = new RecipeServiceImpl(recipeRepository, recipeDtoToRecipe, recipeToRecipeDto);
        recipe = new Recipe();
        recipe.setId("1L");
    }

    @Test
    public void saveRecipeTest() {
        RecipeDTO recipeDTO = recipeToRecipeDto.convert(recipe);
        RecipeDTO savedRecipe = recipeService.saveRecipe(recipeDTO);
        assertEquals(recipeDTO.getId(), savedRecipe.getId());
        assertEquals(recipeDTO.getCategories(), savedRecipe.getCategories());
        assertEquals(recipeDTO.getIngredients(), savedRecipe.getIngredients());
    }

}
