package com.emazon.stockservice.application.dto.brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandRequest {

    @NotBlank(message = "The name cannot be blank")
    @Size(max = 50, message = "The name cannot exceed 50 characters")
    private String name;

    @NotBlank(message = "The description cannot be blank")
    @Size(max = 120, message = "The description cannot exceed 120 characters")
    private String description;
}
