package com.emazon.stockservice.domain.usecases.category.create;

import com.emazon.stockservice.domain.exceptions.DomainException;
import com.emazon.stockservice.domain.exceptions.ErrorCode;
import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.spi.ICategoryPersistencePort;

public class CreateCategoryUseCase implements ICreateCategoryUseCase {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CreateCategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public Category execute(String name, String description) {

        if (categoryPersistencePort.existsByName(name)) {
            throw new DomainException(ErrorCode.CATEGORY_ALREADY_EXISTS);
        }

        Category category = new Category(name, description);
        return categoryPersistencePort.save(category);
    }
}
