package com.emazon.stockservice.infrastructure.web.input;

import com.emazon.stockservice.application.service.ICategoryService;
import com.emazon.stockservice.application.dto.category.CategoryRequest;
import com.emazon.stockservice.application.dto.category.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse response = categoryService.createCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/categories")
    public ResponseEntity<Page<CategoryResponse>> listCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortDirection,
            @RequestParam(defaultValue = "name") String sortBy) {

        Pageable pageable = PageRequest.of(page, size);
        Page<CategoryResponse> response = categoryService.getAllCategories(pageable, sortDirection, sortBy);
        return ResponseEntity.ok(response);
    }
}
