package com.salesianos.dam.primerejemplo.dto;

import com.salesianos.dam.primerejemplo.model.Product;

public record EditProductDto(
        String name,
        Double price,
        String details
) {

    public Product to() {
        return Product.builder()
                .name(name)
                .price(price)
                .details(details)
                .build();
    }

}