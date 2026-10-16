package com.salesianos.dam.primerejemplo.dto;

import jakarta.validation.constraints.NotBlank;

public record EditCategoryDto(
        @NotBlank(message = "El nombre no puede estar vacío")
        String name
) {}