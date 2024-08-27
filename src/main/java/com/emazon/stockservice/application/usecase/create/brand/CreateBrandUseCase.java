package com.emazon.stockservice.application.usecase.create.brand;

import com.emazon.stockservice.domain.models.Brand;
import com.emazon.stockservice.domain.spi.IBrandPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateBrandUseCase implements ICreateBrandUseCase {

    private final IBrandPersistencePort brandPersistencePort;

    @Override
    public Brand execute(String name, String description) {

//        if (brandPersistencePort.existsByName(name)) {
//            throw new BrandAlreadyExistsException("Brand name already exists");
//        }

        Brand brand = new Brand(name, description);
        return brandPersistencePort.save(brand);
    }
}

