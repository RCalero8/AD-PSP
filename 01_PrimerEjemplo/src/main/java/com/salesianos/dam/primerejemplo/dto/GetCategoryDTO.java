package com.salesianos.dam.primerejemplo.dto;

import jdk.jfr.Category;

public record GetCategoryDTO(Long id, String name) {

    public static GetCategoryDTO of(Category c) {
        return new GetCategoryDTO(c.getId(), c.getName());
    }
}