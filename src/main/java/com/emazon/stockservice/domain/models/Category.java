package com.emazon.stockservice.domain.models;

import com.emazon.stockservice.domain.exceptions.DomainException;
import com.emazon.stockservice.domain.exceptions.ErrorCode;

public class Category {
    private final Long id;
    private final String name;
    private final String description;

    public Category(Long id, String name, String description) {
        validateName(name);
        validateDescription(description);
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Category(String name, String description) {
        this(null, name, description);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new DomainException(ErrorCode.INVALID_CATEGORY_NAME);
        }
        if (name.length() > 50) {
            throw new DomainException(ErrorCode.INVALID_CATEGORY_NAME);
        }
    }

    private void validateDescription(String description) {
        if (description != null && description.length() > 90) {
            throw new DomainException(ErrorCode.INVALID_CATEGORY_DESCRIPTION);
        }
    }
}
