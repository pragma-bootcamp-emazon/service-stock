package com.emazon.stockservice.domain.spi;

import com.emazon.stockservice.domain.models.Brand;
import com.emazon.stockservice.domain.utils.PaginatedResult;
import com.emazon.stockservice.domain.utils.Pagination;
import com.emazon.stockservice.domain.utils.SortCriteria;

public interface IBrandPersistencePort {
    Brand save(Brand brand);
    PaginatedResult<Brand> getAllBrands(Pagination pagination, SortCriteria sortOrder);
    boolean existsByName(String name);
    long countTotalBrands();
    Brand findById(Long id);
}
