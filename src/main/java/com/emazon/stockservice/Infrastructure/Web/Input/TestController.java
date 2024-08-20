package com.emazon.stockservice.Infrastructure.Web.Input;

import com.emazon.stockservice.Application.service.ICategoryService;
import com.emazon.stockservice.Infrastructure.Web.dto.CategoryRequest;
import com.emazon.stockservice.Infrastructure.Web.dto.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class TestController {

    private final ICategoryService categoryService;
    @GetMapping()
    public String Stock(){
        return "From Test controller";
    }

    @PostMapping()
    public CategoryResponse StockPost(@Valid @RequestBody CategoryRequest categoryRequest){
        System.out.println(categoryRequest);
        return categoryService.createCategory(categoryRequest);
    }
}
