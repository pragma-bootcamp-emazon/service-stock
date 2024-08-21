package com.emazon.stockservice.application.service;

import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.spi.ICategoryPersistencePort;
import com.emazon.stockservice.application.exceptions.CategoryAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryPersistencePort categoryPersistencePort;

    @Override
    public Category createCategory(Category category) {
        if (categoryPersistencePort.existsByName(category.getName())) {
            throw new CategoryAlreadyExistsException("Category name already exists");
        }

        return categoryPersistencePort.save(category);
    }
}
