package com.emazon.stockservice.Domain.model;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class Category {
    private Long id;

    private String name;
    private String description;
}
