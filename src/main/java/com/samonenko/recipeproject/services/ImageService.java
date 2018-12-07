package com.samonenko.recipeproject.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveRecipeImage(String recipeId, MultipartFile multipartFile);
}
