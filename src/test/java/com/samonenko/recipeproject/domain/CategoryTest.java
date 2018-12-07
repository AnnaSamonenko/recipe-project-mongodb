package com.samonenko.recipeproject.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() {
        category = new Category();
    }

    @Test
    public void getId() {
        String expectedValue = "42";
        category.setId(expectedValue);
        assertEquals(expectedValue, category.getId());
    }
}