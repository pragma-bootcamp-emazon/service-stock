package com.emazon.stockservice.infrastructure.web.Input;

import com.emazon.stockservice.application.service.ICategoryService;
import com.emazon.stockservice.application.dto.CategoryRequest;
import com.emazon.stockservice.application.dto.CategoryResponse;
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

    @PostMapping("/categories")
    @Operation(summary = "Create a new category", description = "This endpoint creates a new category")
    public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        return categoryService.createCategory(categoryRequest);
    }
}