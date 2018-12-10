package com.samonenko.recipeproject.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = "recipes")
@Document
public class Category {

    private String id;

    private String name;

    @DBRef
    private Set<Recipe> recipes;
}
