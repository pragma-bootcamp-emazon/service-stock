package com.emazon.stockservice.domain.usecases.brand.create;

import com.emazon.stockservice.domain.models.Brand;

public interface ICreateBrandUseCase {
    Brand execute(String name, String description);
}
