package com.emazon.stockservice.domain.exceptions;

public enum ErrorCode {
    CATEGORY_ALREADY_EXISTS("Category name already exists"),
    INVALID_CATEGORY_NAME("The category name is invalid"),
    CATEGORY_NOT_FOUND("Category not found"),
    INVALID_CATEGORY_DESCRIPTION("The category description is invalid")
    ;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
