package com.emazon.stockservice.application.handler.brand;

import com.emazon.stockservice.application.dto.brand.BrandRequest;
import com.emazon.stockservice.application.dto.brand.BrandResponse;
import com.emazon.stockservice.application.dto.category.PaginatedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface IBrandHandler {
    BrandResponse createBrand(BrandRequest brandRequest);
    PaginatedResponse<BrandResponse> getAllBrands(Pageable pageable);
}
