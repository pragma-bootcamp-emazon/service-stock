package com.emazon.stockservice.application.usecase;

import com.emazon.stockservice.domain.models.Category;

public interface ICreateCategoryUseCase {
    Category execute(String name, String description);
}