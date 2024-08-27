package com.emazon.stockservice.domain.models;

import com.emazon.stockservice.domain.exceptions.BrandValidationException;

public class Brand {
    private Long id;
    private String name;
    private String description;

    public Brand() {
    }

    public Brand(Long id, String name, String description) {
        validateName(name);
        validateDescription(description);
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Brand(String name, String description) {
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
        validateName(name);
        this.name = name;
    }

    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new BrandValidationException("The brand name cannot be empty");
        }
        if (name.length() > 50) {
            throw new BrandValidationException("The brand name cannot exceed 50 characters");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new BrandValidationException("The brand description cannot be empty");
        }
        if (description.length() > 120) {
            throw new BrandValidationException("The brand description cannot exceed 120 characters");
        }
    }
}
