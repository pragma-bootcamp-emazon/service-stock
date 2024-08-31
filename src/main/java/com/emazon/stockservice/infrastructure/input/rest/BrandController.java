package com.emazon.stockservice.infrastructure.input.rest;

import com.emazon.stockservice.application.dto.brand.BrandRequest;
import com.emazon.stockservice.application.dto.brand.BrandResponse;
import com.emazon.stockservice.application.dto.category.PaginatedResponse;
import com.emazon.stockservice.application.handler.brand.IBrandHandler;
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
@Tag(name = "Brand", description = "Operations related to brand management")
public class BrandController {

    private final IBrandHandler brandService;

    @PostMapping("/brands")
    @Operation(summary = "Create a new brand", description = "This endpoint creates a new brand")
    public ResponseEntity<BrandResponse> createBrand(@Valid @RequestBody BrandRequest brandRequest) {
        BrandResponse response = brandService.createBrand(brandRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get all brands", description = "This endpoint returns all brands")
    @GetMapping("/brands")
    public ResponseEntity<PaginatedResponse<BrandResponse>> listBrands(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            @RequestParam(defaultValue = "name") String sortBy) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        PaginatedResponse<BrandResponse> response = brandService.getAllBrands(pageable);
        return ResponseEntity.ok(response);
    }
}
