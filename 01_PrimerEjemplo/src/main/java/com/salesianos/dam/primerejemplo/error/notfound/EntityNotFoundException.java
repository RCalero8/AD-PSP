package com.salesianos.dam.primerejemplo.error.notfound;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}