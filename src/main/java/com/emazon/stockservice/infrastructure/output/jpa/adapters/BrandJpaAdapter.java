package com.emazon.stockservice.infrastructure.output.jpa.adapters;

import com.emazon.stockservice.domain.models.Brand;
import com.emazon.stockservice.domain.utils.Pagination;
import com.emazon.stockservice.domain.utils.SortOrder;
import com.emazon.stockservice.domain.spi.IBrandPersistencePort;
import com.emazon.stockservice.infrastructure.output.jpa.entities.BrandEntity;
import com.emazon.stockservice.infrastructure.output.jpa.mappers.brand.BrandEntityMapper;
import com.emazon.stockservice.infrastructure.output.jpa.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public List<Brand> getAllBrands(Pagination pagination, SortOrder sortOrder) {
        Pageable pageable = PageRequest.of(
                pagination.getPage(),
                pagination.getSize(),
                Sort.by(Sort.Direction.valueOf(sortOrder.getDirection().name()), sortOrder.getSortBy())
        );

        return brandRepository.findAll(pageable)
                .getContent()
                .stream()
                .map(brandEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public long countTotalBrands() {
        return brandRepository.count();
    }
}
