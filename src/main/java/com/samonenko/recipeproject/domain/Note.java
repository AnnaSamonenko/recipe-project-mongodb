package com.samonenko.recipeproject.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(exclude = "recipe")
public class Note {

    private String id;

    private Recipe recipe;

    private String recipeNotes;
}
