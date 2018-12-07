package com.samonenko.recipeproject.services;

import com.samonenko.recipeproject.converters.IngredientDtoToIngredient;
import com.samonenko.recipeproject.converters.IngredientToIngredientDto;
import com.samonenko.recipeproject.domain.Ingredient;
import com.samonenko.recipeproject.domain.Recipe;
import com.samonenko.recipeproject.dto.IngredientDTO;
import com.samonenko.recipeproject.repositories.RecipeRepository;
import com.samonenko.recipeproject.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository uomRepository;
    private final IngredientToIngredientDto converter;
    private final IngredientDtoToIngredient ingrDtoToIngrConverter;

    public IngredientServiceImpl(RecipeRepository recipeRepository, UnitOfMeasureRepository uomRepository,
                                 IngredientToIngredientDto converter, IngredientDtoToIngredient ingDtoToIngConverter) {
        this.recipeRepository = recipeRepository;
        this.uomRepository = uomRepository;
        this.converter = converter;
        this.ingrDtoToIngrConverter = ingDtoToIngConverter;
    }

    @Override
    public IngredientDTO findIngredientByIds(String recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (!recipeOptional.isPresent()) {
            log.error("Recipe with such id isn't present");
        }
        Recipe recipe = recipeOptional.get();
        Optional<IngredientDTO> ingredientDTO = recipe.getIngredients().
                stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> converter.convert(ingredient)).findFirst();
        if (!ingredientDTO.isPresent()) {
            log.error("Ingredient not found");
        }
        return ingredientDTO.get();
    }

    @Override
    public IngredientDTO save(IngredientDTO ingredientDTO) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientDTO.getRecipeId());
        if (!recipeOptional.isPresent()) {
            log.error("There no recipe with such id");
            return new IngredientDTO();
        }
        Recipe recipe = recipeOptional.get();
        Optional<Ingredient> ingredientOptional = recipe
                .getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientDTO.getId()))
                .findFirst();

        if (ingredientOptional.isPresent()) {

            Ingredient ingredientFound = ingredientOptional.get();
            ingredientFound.setAmount(ingredientDTO.getAmount());
            ingredientFound.setUnitOfMeasure(uomRepository.findById(ingredientDTO.getUom().getId())
                    .orElseThrow(() -> (new RuntimeException("UOM not found"))));
            ingredientFound.setDescription(ingredientDTO.getDescription());

        } else {
            Ingredient ingredient = ingrDtoToIngrConverter.convert(ingredientDTO);
            ingredient.setRecipe(recipeOptional.get());
            recipe.getIngredients().add(ingredient);
        }

        Recipe savedRecipe = recipeRepository.save(recipe);
        // if was added ingredient
        Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                .filter(recipeIngredients -> recipeIngredients.getId().equals(ingredientDTO.getId()))
                .findFirst();

        //check by description
        if (!savedIngredientOptional.isPresent()) {
            //not totally safe... But best guess
            savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getDescription().equals(ingredientDTO.getDescription()))
                    .filter(recipeIngredients -> recipeIngredients.getAmount().equals(ingredientDTO.getAmount()))
                    .filter(recipeIngredients -> recipeIngredients.getUnitOfMeasure().getId().equals(ingredientDTO.getUom().getId()))
                    .findFirst();
        }

        return converter.convert(savedIngredientOptional.get());
    }

    @Override
    public void deleteIngredientByIds(String recipeId, Long ingredientId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (!recipe.isPresent()) {
            log.error("There no such recipe");
        }
        Optional<Ingredient> ingredientOptional = recipe.get().getIngredients()
                .stream().filter(i -> i.getId().equals(ingredientId)).findFirst();

        if (ingredientOptional.isPresent()) {
            Ingredient ingredient = ingredientOptional.get();
            ingredient.setRecipe(null);
            recipe.get().getIngredients().remove(ingredient);
        }
        recipeRepository.save(recipe.get());
    }
}
