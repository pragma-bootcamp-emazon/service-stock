package com.emazon.stockservice.application.handler.category;

import com.emazon.stockservice.application.dto.category.CategoryRequest;
import com.emazon.stockservice.application.dto.category.CategoryResponse;
import com.emazon.stockservice.application.dto.category.PaginatedResponse;
import org.springframework.data.domain.Pageable;


public interface ICategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);

    PaginatedResponse<CategoryResponse> getAllCategories(Pageable pageable);
}
