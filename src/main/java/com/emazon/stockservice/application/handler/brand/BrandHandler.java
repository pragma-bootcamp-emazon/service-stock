package com.emazon.stockservice.application.handler.brand;

import com.emazon.stockservice.application.dto.brand.BrandRequest;
import com.emazon.stockservice.application.dto.brand.BrandResponse;
import com.emazon.stockservice.application.dto.category.PaginatedResponse;
import com.emazon.stockservice.application.mapper.brand.IBrandRequestMapper;
import com.emazon.stockservice.application.mapper.brand.IBrandResponseMapper;
import com.emazon.stockservice.domain.models.Brand;
import com.emazon.stockservice.domain.usecases.brand.create.ICreateBrandUseCase;
import com.emazon.stockservice.domain.usecases.brand.retrieve.IRetrieveBrands;
import com.emazon.stockservice.domain.utils.Pagination;
import com.emazon.stockservice.domain.utils.SortCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandHandler implements IBrandHandler {

    private final ICreateBrandUseCase createBrandUseCase;
    private final IRetrieveBrands retrieveBrandsUseCase;
    private final IBrandRequestMapper brandRequestMapper;
    private final IBrandResponseMapper brandResponseMapper;

    @Override
    public BrandResponse createBrand(BrandRequest brandRequest) {
        Brand brand = brandRequestMapper.toBrand(brandRequest);
        Brand createdBrand = createBrandUseCase.execute(
                brand.getName(),
                brand.getDescription()
        );
        return brandResponseMapper.toBrandResponse(createdBrand);
    }

    @Override
    public PaginatedResponse<BrandResponse> getAllBrands(Pageable pageable) {

        Pagination pagination = new Pagination(pageable.getPageNumber(), pageable.getPageSize());
        Sort.Order order = pageable.getSort().stream().findFirst().orElse(Sort.Order.by("name"));
        SortCriteria.Direction direction = order.isAscending() ? SortCriteria.Direction.ASC : SortCriteria.Direction.DESC;
        SortCriteria sortOrder = new SortCriteria(order.getProperty(), direction);

        var paginatedResult = retrieveBrandsUseCase.execute(pagination, sortOrder);

        List<BrandResponse> content = paginatedResult.getContent().stream()
                .map(brand -> new BrandResponse(
                        brand.getId(),
                        brand.getName(),
                        brand.getDescription(),
                        brand.getCreatedAt(),
                        brand.getUpdatedAt()))
                .collect(Collectors.toList());

        return new PaginatedResponse<>(
                content,
                paginatedResult.getPage(),
                paginatedResult.getSize(),
                paginatedResult.getTotalElements(),
                paginatedResult.getTotalPages()
        );
    }
}
