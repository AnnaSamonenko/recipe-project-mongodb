package com.samonenko.recipeproject.dto;

import com.samonenko.recipeproject.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeDTO {

    private String id;

    @Max(1)
    @Min(999)
    private Integer prepTime;

    @Max(1)
    @Min(999)
    private Integer cookTime;

    @Max(1)
    @Min(100)
    private Integer servings;
    private String source;

    @URL
    private String url;

    @NotBlank
    private String direction;

    @NotBlank
    @Size(min = 3, max = 100)
    private String description;
    private Byte[] image;
    private Difficulty difficulty;
    private NoteDTO note;
    private Set<IngredientDTO> ingredients = new HashSet<>();
    private Set<CategoryDTO> categories;

}
