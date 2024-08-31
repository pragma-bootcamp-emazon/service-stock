package com.emazon.stockservice.application.handler.brand;

import com.emazon.stockservice.application.mapper.brand.IBrandRequestMapper;
import com.emazon.stockservice.application.mapper.brand.IBrandResponseMapper;
import com.emazon.stockservice.application.usecase.create.brand.ICreateBrandUseCase;
import com.emazon.stockservice.application.usecase.retrieve.brand.IRetrieveBrands;
import com.emazon.stockservice.domain.models.Brand;
import com.emazon.stockservice.application.dto.brand.BrandRequest;
import com.emazon.stockservice.application.dto.brand.BrandResponse;
import com.emazon.stockservice.domain.models.Pagination;
import com.emazon.stockservice.domain.models.SortOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements IBrandService {

    private final ICreateBrandUseCase createBrandUseCase;
    private final IRetrieveBrands retrieveBrands;
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
    public Page<BrandResponse> getAllBrands(Pageable pageable, Sort.Direction sortDirection, String sortBy) {

        String sortField = (sortBy == null || sortBy.isEmpty()) ? "name" : sortBy;
        Pageable pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortDirection, sortField));

        List<Brand> brands = retrieveBrands.execute(
                new Pagination(pageRequest.getPageNumber(), pageRequest.getPageSize()),
                new SortOrder(sortField, SortOrder.Direction.valueOf(sortDirection.name()))
        );

        long totalElements = retrieveBrands.countTotalBrands();

        List<BrandResponse> responseList = brands.stream()
                .map(brandResponseMapper::toBrandResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(responseList, pageable, totalElements);
    }
}
