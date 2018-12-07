package com.samonenko.recipeproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class NoteDTO {

    private Long id;
    private String recipeNotes;
}
