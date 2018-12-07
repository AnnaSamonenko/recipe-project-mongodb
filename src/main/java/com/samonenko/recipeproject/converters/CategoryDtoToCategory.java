package com.samonenko.recipeproject.converters;

import com.samonenko.recipeproject.domain.Category;
import com.samonenko.recipeproject.dto.CategoryDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoToCategory implements Converter<CategoryDTO, Category> {

    @Override
    public Category convert(CategoryDTO categoryDTO) {
        if (categoryDTO == null)
            return null;
        final Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        return category;
    }
}
