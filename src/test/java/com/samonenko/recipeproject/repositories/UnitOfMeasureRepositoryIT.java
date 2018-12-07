package com.samonenko.recipeproject.repositories;

import com.samonenko.recipeproject.domain.UnitOfMeasure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    private UnitOfMeasureRepository uomRepository;

    @Test
    @DirtiesContext
    public void findByType() {
        Optional<UnitOfMeasure> uomOptional = uomRepository.findByType("Teaspoon");

        assertEquals("Teaspoon", uomOptional.get().getType());
    }
}