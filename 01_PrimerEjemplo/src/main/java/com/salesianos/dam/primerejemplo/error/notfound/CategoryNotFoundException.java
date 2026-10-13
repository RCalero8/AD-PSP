package com.salesianos.dam.primerejemplo.error.notfound;

public class CategoryNotFoundException extends EntityNotFoundException {
    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException() {
        super("Categories not found");
    }
}
