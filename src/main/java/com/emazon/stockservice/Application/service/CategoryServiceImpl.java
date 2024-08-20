package com.emazon.stockservice.Application.service;

import com.emazon.stockservice.Domain.model.Category;
import com.emazon.stockservice.Domain.port.ICategoryPersistencePort;
import com.emazon.stockservice.Infrastructure.Web.dto.CategoryRequest;
import com.emazon.stockservice.Infrastructure.Web.dto.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryPersistencePort categoryPersistencePort;

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());

        Category savedCategory = categoryPersistencePort.save(category);
        return new CategoryResponse(savedCategory.getId(), savedCategory.getName(), savedCategory.getDescription());
    }
}