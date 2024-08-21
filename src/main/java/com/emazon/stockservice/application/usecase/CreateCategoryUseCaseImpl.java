package com.emazon.stockservice.application.usecase;

import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.spi.ICategoryPersistencePort;
import com.emazon.stockservice.application.exceptions.CategoryAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CreateCategoryUseCaseImpl implements ICreateCategoryUseCase {

    private final ICategoryPersistencePort categoryPersistencePort;

    @Override
    public Category execute(String name, String description) {
        if (categoryPersistencePort.existsByName(name)) {
            throw new CategoryAlreadyExistsException("Category name already exists");
        }

        Category category = new Category();
        category.setName(name);
        category.setDescription(description);

        return categoryPersistencePort.save(category);
    }
}
