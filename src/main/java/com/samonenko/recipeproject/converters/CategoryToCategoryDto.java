package com.samonenko.recipeproject.converters;

import com.samonenko.recipeproject.domain.Category;
import com.samonenko.recipeproject.dto.CategoryDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryDto implements Converter<Category, CategoryDTO> {

    @Override
    public CategoryDTO convert(Category category) {
        if (category == null)
            return null;
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }
}
