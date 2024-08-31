package com.emazon.stockservice.application.handler.brand;

import com.emazon.stockservice.application.dto.brand.BrandRequest;
import com.emazon.stockservice.application.dto.brand.BrandResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface IBrandService {
    BrandResponse createBrand(BrandRequest brandRequest);
    Page<BrandResponse> getAllBrands(Pageable pageable, Sort.Direction sortDirection, String sortBy);
}
