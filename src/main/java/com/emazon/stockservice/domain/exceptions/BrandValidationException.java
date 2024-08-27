package com.emazon.stockservice.domain.exceptions;

public class BrandValidationException extends RuntimeException {
    public BrandValidationException(String message) {
        super(message);
    }
}
