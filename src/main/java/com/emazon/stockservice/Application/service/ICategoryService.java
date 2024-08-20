package com.emazon.stockservice.Application.service;

import com.emazon.stockservice.Infrastructure.Web.dto.CategoryRequest;
import com.emazon.stockservice.Infrastructure.Web.dto.CategoryResponse;

public interface ICategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);
}
