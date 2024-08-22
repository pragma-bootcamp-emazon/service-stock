package com.emazon.stockservice.application.service;

import com.emazon.stockservice.application.dto.CategoryRequest;
import com.emazon.stockservice.application.dto.CategoryResponse;

public interface ICategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);
}
