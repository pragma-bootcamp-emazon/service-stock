package com.emazon.stockservice.domain.usecases.brand.create;

import com.emazon.stockservice.domain.exceptions.DomainException;
import com.emazon.stockservice.domain.exceptions.ErrorCode;
import com.emazon.stockservice.domain.models.Brand;
import com.emazon.stockservice.domain.spi.IBrandPersistencePort;


public class CreateBrandUseCase implements ICreateBrandUseCase {

    private final IBrandPersistencePort brandPersistencePort;

    public CreateBrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }
    @Override
    public Brand execute(String name, String description) {

        if (brandPersistencePort.existsByName(name)) {
            throw new DomainException(ErrorCode.BRAND_ALREADY_EXISTS);
        }

        Brand brand = new Brand(name, description);
        return brandPersistencePort.save(brand);
    }
}

