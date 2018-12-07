package com.samonenko.recipeproject.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(exclude = "recipe")
@NoArgsConstructor
public class Ingredient {

    private String id;

    private String description;

    private BigDecimal amount;

    private Recipe recipe;

    private UnitOfMeasure unitOfMeasure;

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.amount = amount;
        this.description = description;
        this.unitOfMeasure = uom;
        this.recipe = recipe;
    }
}
