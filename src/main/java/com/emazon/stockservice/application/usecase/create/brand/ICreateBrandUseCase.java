package com.emazon.stockservice.application.usecase.create.brand;

import com.emazon.stockservice.domain.models.Brand;

public interface ICreateBrandUseCase {
    Brand execute(String name, String description);
}
