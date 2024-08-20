package com.emazon.stockservice.application.service;

import com.emazon.stockservice.domain.model.Category;
import com.emazon.stockservice.domain.port.ICategoryPersistencePort;
import com.emazon.stockservice.infrastructure.Web.dto.CategoryRequest;
import com.emazon.stockservice.infrastructure.Web.dto.CategoryResponse;
import com.emazon.stockservice.infrastructure.exception.CategoryAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryPersistencePort categoryPersistencePort;

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        if (categoryPersistencePort.existsByName(categoryRequest.getName())) {
            throw new CategoryAlreadyExistsException("Category name already exists");
        }

        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());

        Category savedCategory = categoryPersistencePort.save(category);

        return new CategoryResponse(savedCategory.getId(), savedCategory.getName(), savedCategory.getDescription());
    }
}