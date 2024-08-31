package com.emazon.stockservice.domain.exceptions;

public enum ErrorCode {
    CATEGORY_ALREADY_EXISTS("Category name already exists"),
    INVALID_CATEGORY_NAME("The category name is invalid"),
    CATEGORY_NOT_FOUND("Category not found"),
    INVALID_CATEGORY_DESCRIPTION("The category description is invalid"),
    BRAND_ALREADY_EXISTS("Brand name already exists"),
    INVALID_BRAND_NAME("The brand name is invalid"),
    BRAND_NOT_FOUND("Brand not found"),
    INVALID_BRAND_DESCRIPTION("The brand description is invalid"),
    BRAND_NOT_EMPTY("The brand is not empty"),
    ;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
