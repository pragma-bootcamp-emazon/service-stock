package com.emazon.stockservice.application.service;

import com.emazon.stockservice.application.dto.category.CategoryRequest;
import com.emazon.stockservice.application.dto.category.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface ICategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);

    Page<CategoryResponse> getAllCategories(Pageable pageable, Sort.Direction sortDirection, String sortBy);

}
