package com.emazon.stockservice.application.usecase.create;

import com.emazon.stockservice.application.exceptions.CategoryAlreadyExistsException;
import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCategoryUseCase implements ICreateCategoryUseCase {

    private final ICategoryPersistencePort categoryPersistencePort;

    @Override
    public Category execute(String name, String description) {

        if (categoryPersistencePort.existsByName(name)) {
            throw new CategoryAlreadyExistsException("Category name already exists");
        }

        Category category = new Category(name, description);
        return categoryPersistencePort.save(category);
    }
}
