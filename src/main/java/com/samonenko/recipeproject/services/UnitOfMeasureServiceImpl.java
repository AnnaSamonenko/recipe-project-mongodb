package com.samonenko.recipeproject.services;

import com.samonenko.recipeproject.converters.UnitOfMeasureToUnitOfMeasureDTO;
import com.samonenko.recipeproject.dto.UnitOfMeasureDTO;
import com.samonenko.recipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository repository;
    private final UnitOfMeasureToUnitOfMeasureDTO converter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository repository, UnitOfMeasureToUnitOfMeasureDTO converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public Set<UnitOfMeasureDTO> listOfUOMs() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(converter::convert).collect(Collectors.toSet());
    }


}
