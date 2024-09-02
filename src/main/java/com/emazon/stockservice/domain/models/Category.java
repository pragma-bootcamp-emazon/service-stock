package com.emazon.stockservice.domain.models;

import com.emazon.stockservice.domain.exceptions.DomainException;
import com.emazon.stockservice.domain.exceptions.ErrorCode;

import java.time.LocalDateTime;

public class Category {
    private final Long id;
    private final String name;
    private final String description;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Category(Long id, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        validateName(name);
        validateDescription(description);
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Category(String name, String description) {
        this(null, name, description, null, null);
    }

    public Category(Long id) {
        this(id, null, null, null, null);
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    private void validateName(String name) {
        if (name != null) {
            if (name.trim().isEmpty()) {
                throw new DomainException(ErrorCode.INVALID_CATEGORY_NAME);
            }
            if (name.length() > 50) {
                throw new DomainException(ErrorCode.INVALID_CATEGORY_NAME);
            }
        }
    }

    private void validateDescription(String description) {
        if (description != null && description.length() > 90) {
            throw new DomainException(ErrorCode.INVALID_CATEGORY_DESCRIPTION);
        }
    }
}
