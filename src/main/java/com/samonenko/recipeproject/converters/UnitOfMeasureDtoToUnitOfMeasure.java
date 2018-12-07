package com.samonenko.recipeproject.converters;

import com.samonenko.recipeproject.domain.UnitOfMeasure;
import com.samonenko.recipeproject.dto.UnitOfMeasureDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureDtoToUnitOfMeasure implements Converter<UnitOfMeasureDTO, UnitOfMeasure> {

    @Override
    public UnitOfMeasure convert(UnitOfMeasureDTO unitOfMeasureDTO) {
        if (unitOfMeasureDTO == null)
            return null;
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(unitOfMeasureDTO.getId());
        uom.setType(unitOfMeasureDTO.getType());
        return uom;
    }
}
