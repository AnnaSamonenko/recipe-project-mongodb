package com.samonenko.recipeproject.services;

import com.samonenko.recipeproject.domain.Recipe;
import com.samonenko.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveRecipeImage(String recipeId, MultipartFile multipartFile) {
        Recipe recipe = recipeRepository.findById(recipeId).get();

        try {
            Byte[] byteObject = new Byte[multipartFile.getBytes().length];

            int i = 0;
            for (byte b : multipartFile.getBytes())
                byteObject[i++] = b;

            recipe.setImage(byteObject);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
