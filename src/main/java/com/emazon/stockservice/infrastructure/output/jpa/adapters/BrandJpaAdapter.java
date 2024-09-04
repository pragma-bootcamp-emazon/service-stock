package com.emazon.stockservice.infrastructure.output.jpa.adapters;

import com.emazon.stockservice.domain.models.Brand;
import com.emazon.stockservice.domain.utils.PaginatedResult;
import com.emazon.stockservice.domain.utils.Pagination;
import com.emazon.stockservice.domain.utils.SortCriteria;
import com.emazon.stockservice.domain.spi.IBrandPersistencePort;
import com.emazon.stockservice.infrastructure.output.jpa.entities.BrandEntity;
import com.emazon.stockservice.infrastructure.output.jpa.mappers.brand.BrandEntityMapper;
import com.emazon.stockservice.infrastructure.output.jpa.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public PaginatedResult<Brand> getAllBrands(Pagination pagination, SortCriteria sortOrder) {
        Pageable pageable = PageRequest.of(
                pagination.getPage(),
                pagination.getSize(),
                Sort.by(Sort.Direction.valueOf(sortOrder.getDirection().name()), sortOrder.getSortBy())
        );

        Page<BrandEntity> brandPage = brandRepository.findAll(pageable);

        List<Brand> brands = brandPage.getContent()
                .stream()
                .map(brandEntityMapper::toDomain)
                .collect(Collectors.toList());

        return new PaginatedResult<>(
                brands,
                pagination.getPage(),
                pagination.getSize(),
                brandPage.getTotalElements(),
                brandPage.getTotalPages()
        );
    }

    @Override
    public boolean existsByName(String name) {
        return brandRepository.existsByName(name);
    }

    @Override
    public Brand findById(Long id) {
        return brandRepository.findById(id)
                .map(brandEntityMapper::toDomain)
                .orElse(null);
    }

    @Override
    public long countTotalBrands() {
        return brandRepository.count();
    }
}
