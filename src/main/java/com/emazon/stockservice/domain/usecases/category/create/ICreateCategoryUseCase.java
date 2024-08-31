package com.emazon.stockservice.domain.usecases.category.create;

import com.emazon.stockservice.domain.models.Category;

public interface ICreateCategoryUseCase {
    Category execute(String name, String description);
}