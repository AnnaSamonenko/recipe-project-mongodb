package com.samonenko.recipeproject.converters;

import com.samonenko.recipeproject.domain.UnitOfMeasure;
import com.samonenko.recipeproject.dto.UnitOfMeasureDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureDTO implements Converter<UnitOfMeasure, UnitOfMeasureDTO> {

    @Override
    public UnitOfMeasureDTO convert(UnitOfMeasure unitOfMeasure) {
        if (unitOfMeasure == null)
            return null;
        UnitOfMeasureDTO uomDto = new UnitOfMeasureDTO();
        uomDto.setId(unitOfMeasure.getId());
        uomDto.setType(unitOfMeasure.getType());
        return uomDto;
    }
}
