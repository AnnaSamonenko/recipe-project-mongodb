package com.samonenko.recipeproject.services;

import com.samonenko.recipeproject.converters.RecipeDtoToRecipe;
import com.samonenko.recipeproject.converters.RecipeToRecipeDto;
import com.samonenko.recipeproject.domain.Recipe;
import com.samonenko.recipeproject.dto.RecipeDTO;
import com.samonenko.recipeproject.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeServiceImplTest {

    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeToRecipeDto recipeToRecipeDto;

    @Mock
    private RecipeDtoToRecipe recipeDtoToRecipe;

    @Mock
    private RecipeRepository recipeRepository;

    private Recipe recipe;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeDtoToRecipe, recipeToRecipeDto);
        recipe = new Recipe();
        recipe.setId(1L);
    }

    @Test
    public void getRecipesTest() {
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(recipe);

        Mockito.when(recipeService.getRecipes()).thenReturn(recipes);

        Set<Recipe> recipesActual = recipeService.getRecipes();

        assertEquals(1, recipesActual.size());
        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void findRecipeByIdTest() {
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(1L);
        Mockito.when(recipeRepository.findById(recipe.getId())).thenReturn(Optional.of(recipe));
        Mockito.when(recipeToRecipeDto.convert(recipe)).thenReturn(recipeDTO);

        assertEquals(recipeDTO, recipeService.findRecipeById(recipe.getId()));

        Mockito.verify(recipeRepository,
                Mockito.times(1)).findById(recipe.getId());
        Mockito.verify(recipeToRecipeDto,
                Mockito.times(1)).convert(recipe);
    }

    @Test
    public void testDeleteById() {
        Long idToDelete = 2L;

        recipeService.deleteById(idToDelete);
        Mockito.verify(recipeRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

}