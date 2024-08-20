package com.emazon.stockservice.application.service;

import com.emazon.stockservice.infrastructure.Web.dto.CategoryRequest;
import com.emazon.stockservice.infrastructure.Web.dto.CategoryResponse;

public interface ICategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);
}
