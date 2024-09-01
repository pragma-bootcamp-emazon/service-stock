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
    INVALID_ARTICLE_NAME("The article name is invalid"),
    INVALID_ARTICLE_DESCRIPTION("The article description is invalid"),
    INVALID_ARTICLE_QUANTITY("The article quantity is invalid"),
    INVALID_ARTICLE_PRICE("The article price is invalid"),
    INVALID_ARTICLE_CATEGORIES("The article categories are invalid"),
    ARTICLE_ALREADY_EXISTS("Article name already exists"),
    ;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
