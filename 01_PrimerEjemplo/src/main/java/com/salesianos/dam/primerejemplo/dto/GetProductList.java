package com.salesianos.dam.primerejemplo.dto;

import com.salesianos.dam.primerejemplo.model.Product;

public record GetProductList(
        Long id,
        String name,
        Double price
) {

    public static GetProductList of(Product p) {
        return new GetProductList(p.getId(), p.getName(), p.getPrice());
    }
}