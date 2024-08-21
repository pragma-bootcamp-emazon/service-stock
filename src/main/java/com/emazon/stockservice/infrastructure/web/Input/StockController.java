package com.emazon.stockservice.infrastructure.web.Input;

import com.emazon.stockservice.application.service.ICategoryService;
import com.emazon.stockservice.infrastructure.web.dto.CategoryRequest;
import com.emazon.stockservice.infrastructure.web.dto.CategoryResponse;
import com.emazon.stockservice.infrastructure.web.output.jpa.mapper.ICategoryResponseMapper;
import com.emazon.stockservice.infrastructure.web.output.jpa.mapper.ICategoryRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
@Tag(name = "Stock", description = "Operations related to stock management")
public class StockController {

    private final ICategoryService categoryService;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    @PostMapping("/categories")
    @Operation(summary = "Create a new category", description = "This endpoint creates a new category")
    public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        var category = categoryRequestMapper.toCategory(categoryRequest);
        var savedCategory = categoryService.createCategory(category);
        return categoryResponseMapper.toCategoryResponse(savedCategory);
    }
}
