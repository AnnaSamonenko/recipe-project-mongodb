package com.samonenko.recipeproject.controllers;

import com.samonenko.recipeproject.domain.Recipe;
import com.samonenko.recipeproject.dto.RecipeDTO;
import com.samonenko.recipeproject.exceptions.NotFoundException;
import com.samonenko.recipeproject.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    private RecipeController recipeController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).setControllerAdvice(ControllerExceptionHandler.class).build();
    }

    // /recipe/{id}/show -- GET
    @Test
    public void testShowById() throws Exception {
        RecipeDTO recipe = new RecipeDTO();
        recipe.setId("1");

        Mockito.when(recipeService.findRecipeById("1")).thenReturn(recipe);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/" + recipe.getId() + "/show"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/show"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
    }

    @Test
    public void testGetRecipeNotFound() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId("1");

        Mockito.when(recipeService.findRecipeById(Mockito.eq("1"))).thenThrow(NotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/show"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetRecipeNumberFormatException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/cfh/show"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    // recipe/new -- GET
    @Test
    public void testGetAddRecipe() throws Exception {
        RecipeDTO recipeDTO = new RecipeDTO();

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"))
                .andExpect(MockMvcResultMatchers.view().name("recipe/recipe_form"));
    }

    // recipe/{id}/update -- GET
    @Test
    public void testGetUpdateRecipe() throws Exception {
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId("1");

        Mockito.when(recipeService.findRecipeById(recipeDTO.getId())).thenReturn(recipeDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/" + recipeDTO.getId() + "/update"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"))
                .andExpect(MockMvcResultMatchers.view().name("recipe/recipe_form"));
    }

    // /recipe - POST
    @Test
    public void testUpdateAndSaveRecipe() throws Exception {
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId("1");

        Mockito.when(recipeService.saveRecipe(Mockito.any())).thenReturn(recipeDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/recipe").param("id", "1")
                .param("description", "description").param("direction", "direction"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/recipe/1/show"));
    }

    @Test
    public void testUpdateAndSaveRecipeWithInvalidData() throws Exception {
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId("1");

        Mockito.when(recipeService.saveRecipe(Mockito.any())).thenReturn(recipeDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/recipe").param("id", "1")
                .param("description", "description"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/recipe/new"));
    }

    @Test
    public void testDeleteRecipe() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/recipe/1/delete"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"));

        Mockito.verify(recipeService, Mockito.times(1)).deleteById(Mockito.anyString());
    }

}