package com.samonenko.recipeproject.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(exclude = "recipe")
@NoArgsConstructor
public class Ingredient {

    private String id = UUID.randomUUID().toString();

    private String description;

    private BigDecimal amount;

    private Recipe recipe;

    @DBRef
    private UnitOfMeasure unitOfMeasure;

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.amount = amount;
        this.description = description;
        this.unitOfMeasure = uom;
        this.recipe = recipe;
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.amount = amount;
        this.description = description;
        this.unitOfMeasure = uom;
    }
}
