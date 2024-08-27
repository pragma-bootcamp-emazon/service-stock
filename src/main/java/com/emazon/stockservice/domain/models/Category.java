package com.emazon.stockservice.domain.models;

import com.emazon.stockservice.domain.exceptions.CategoryValidationException;

public class Category {
    private Long id;
    private String name;
    private String description;

    public Category() {
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new CategoryValidationException("The category name cannot be empty");
        }
        if (name.length() > 50) {
            throw new CategoryValidationException("The category name cannot exceed 50 characters");
        }
    }

    private void validateDescription(String description) {
        if (description != null && description.length() > 200) {
            throw new CategoryValidationException("The category description cannot exceed 200 characters");
        }
    }
}
