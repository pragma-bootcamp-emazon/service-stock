package com.emazon.stockservice.infrastructure.Web.Input;

import com.emazon.stockservice.application.service.ICategoryService;
import com.emazon.stockservice.infrastructure.Web.dto.CategoryRequest;
import com.emazon.stockservice.infrastructure.Web.dto.CategoryResponse;
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
    @GetMapping()
    public String Stock(){
        return "From Test controller";
    }

    @PostMapping()
    @Operation(summary = "Create a new category", description = "This endpoint creates a new category")
    public CategoryResponse StockPost(@Valid @RequestBody CategoryRequest categoryRequest){
        System.out.println(categoryRequest);
        return categoryService.createCategory(categoryRequest);
    }
}
