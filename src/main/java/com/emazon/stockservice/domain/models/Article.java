package com.emazon.stockservice.domain.models;

import com.emazon.stockservice.domain.exceptions.DomainException;
import com.emazon.stockservice.domain.exceptions.ErrorCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Article {
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
    private List<Category> categories;
    private final Long brandId;
    private LocalDateTime createdAt;

    private Article(Long id, String name, String description, int quantity, BigDecimal price, List<Category> categories, Long brandId, LocalDateTime createdAt) {
        validateName(name);
        validateCategories(categories);
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.categories = categories;
        this.brandId = brandId;
        this.createdAt = createdAt;
    }

    public static Article createWithoutId(String name, String description, int quantity, BigDecimal price, List<Category> categories, Long brandId) {
        return new Article(null, name, description, quantity, price, categories, brandId, null);
    }

    public static Article createWithId(Long id, String name, String description, int quantity, BigDecimal price, List<Category> categories, Long brandId, LocalDateTime createdAt) {
        return new Article(id, name, description, quantity, price, categories, brandId, createdAt);
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

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Long getBrandId() {
        return brandId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCategories(List<Category> categories) {
        validateCategories(categories);
        this.categories = categories;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank() || name.length() < 3 || name.length() > 50) {
            throw new DomainException(ErrorCode.INVALID_ARTICLE_NAME);
        }
    }

    private void validateCategories(List<Category> categories) {
        if (categories == null || categories.isEmpty() || categories.size() > 3) {
            throw new DomainException(ErrorCode.INVALID_ARTICLE_CATEGORIES);
        }

        for (int i = 0; i < categories.size(); i++) {
            for (int j = i + 1; j < categories.size(); j++) {
                if (categories.get(i).getId().equals(categories.get(j).getId())) {
                    throw new DomainException(ErrorCode.INVALID_ARTICLE_CATEGORIES);
                }
            }
        }
    }
}
