package com.emazon.stockservice.domain.models;

import com.emazon.stockservice.domain.exceptions.DomainException;
import com.emazon.stockservice.domain.exceptions.ErrorCode;

import java.time.LocalDateTime;

public class Brand {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Brand() {
    }

    public Brand(Long id) {
        this.id = id;
    }

    public Brand(Long id, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (name != null) {
            validateName(name);
        }
        if (description != null) {
            validateDescription(description);
        }
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Brand createWithId(Long id) {
        return new Brand(id, null, null, null, null);
    }

    public Brand(String name, String description) {
        this(null, name, description, null, null);
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

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new DomainException(ErrorCode.INVALID_BRAND_NAME);
        }
        if (name.length() > 50) {
            throw new DomainException(ErrorCode.INVALID_BRAND_NAME);
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new DomainException(ErrorCode.BRAND_NOT_EMPTY);
        }
        if (description.length() > 120) {
            throw new DomainException(ErrorCode.INVALID_BRAND_DESCRIPTION);
        }
    }
}
