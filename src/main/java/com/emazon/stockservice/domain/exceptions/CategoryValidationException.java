package com.emazon.stockservice.domain.exceptions;

public class CategoryValidationException extends RuntimeException {

    public CategoryValidationException(String message) {
        super(message);
    }
}
