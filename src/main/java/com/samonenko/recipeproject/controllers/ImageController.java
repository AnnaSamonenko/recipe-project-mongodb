package com.samonenko.recipeproject.controllers;

import com.samonenko.recipeproject.dto.RecipeDTO;
import com.samonenko.recipeproject.services.ImageService;
import com.samonenko.recipeproject.services.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {

    private final ImageService imageService;

    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/image")
    public String uploadImagePage(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeById(id));
        return "recipe/upload_image";
    }

    @PostMapping("recipe/{id}/image")
    public String saveImage(@PathVariable String id, @RequestParam("imagefile") MultipartFile multipartFile) {
        imageService.saveRecipeImage(id, multipartFile);
        return "redirect:/recipe/" + id + "/show";
    }

    @GetMapping("recipe/{id}/recipe-image")
    public void renderRecipeImage(@PathVariable String id, HttpServletResponse response) throws IOException {
        RecipeDTO recipeDTO = recipeService.findRecipeById(id);

        byte[] byteArray = new byte[recipeDTO.getImage().length];

        int i = 0;
        for (byte b : recipeDTO.getImage())
            byteArray[i++] = b;

        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(byteArray);
        IOUtils.copy(is, response.getOutputStream());
    }
}
