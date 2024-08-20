package com.emazon.stockservice.Infrastructure.Web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

    @NotBlank(message = "Arguments not should be empty")
    @Size(max = 10, message = "Property name not greater than 50")
    private String name;

    @NotBlank(message = "Argument description not must empty")
    @Size(max = 90, message = "La descripción no puede tener más de 90 caracteres")
    private String description;
}