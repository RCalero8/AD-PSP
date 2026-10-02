package com.salesianos.dam.primerejemplo.dto;
import com.salesianos.dam.primerejemplo.model.Product;

public record GetProductDetail(
        Long id,
        String name,
        Double price,
        String details
) {

    public static GetProductDetail of(Product p) {
        return new GetProductDetail(
                p.getId(),
                p.getName(),
                p.getPrice(),
                p.getDetails()
        );
    }

}