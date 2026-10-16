package com.salesianos.dam.primerejemplo.error;

public class CategoryNotFoundException extends NotFoundException {

    public CategoryNotFoundException() {
        super("No se ha encontrado la categoría");
    }

    public CategoryNotFoundException(Long id) {
        super("No se ha encontrado la categoría con id " + id);
    }
}