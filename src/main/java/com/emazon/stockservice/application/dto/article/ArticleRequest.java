package com.emazon.stockservice.application.dto.article;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequest {

    @NotBlank(message = "The name cannot be blank")
    @Size(max = 50, message = "The name cannot exceed 50 characters")
    private String name;

    @NotBlank(message = "The description cannot be blank")
    @Size(max = 255, message = "The description cannot exceed 255 characters")
    private String description;

    @Min(value = 1, message = "The quantity must be at least 1")
    private int quantity;

    @DecimalMin(value = "0.01", message = "The price must be greater than 0")
    private BigDecimal price;

    @NotEmpty(message = "At least one category must be associated")
    @Size(min = 1, max = 3, message = "You must associate between 1 and 3 categories")
    private List<Long> categories;

    @NotNull(message = "The brand ID must be provided")
    private Long brandId;
}
