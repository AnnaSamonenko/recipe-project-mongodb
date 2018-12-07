package com.samonenko.recipeproject.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Document
public class Recipe {

    @Id
    private String id;

    private Integer prepTime;

    private Integer cookTime;

    private Integer servings;

    private String source;

    private String url;

    private String direction;

    private String description;

    private Byte[] image;

    private Difficulty difficulty;

    private Note note;

    private Set<Ingredient> ingredients = new HashSet<>();

    @DBRef
    private Set<Category> categories = new HashSet<>();
}
