package com.emazon.stockservice.infrastructure.adapter.persistence;

import com.emazon.stockservice.domain.models.Brand;
import com.emazon.stockservice.domain.spi.IBrandPersistencePort;
import com.emazon.stockservice.infrastructure.entities.BrandEntity;
import com.emazon.stockservice.infrastructure.mappers.brand.BrandEntityMapper;
import com.emazon.stockservice.infrastructure.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BrandJpaAdapter implements IBrandPersistencePort {

    private final BrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;

    @Override
    public Brand save(Brand brand) {
        BrandEntity brandEntity = brandEntityMapper.toEntity(brand);
        BrandEntity savedEntity = brandRepository.save(brandEntity);
        return brandEntityMapper.toDomain(savedEntity);
    }

}
