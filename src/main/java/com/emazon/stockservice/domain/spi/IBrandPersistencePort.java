package com.emazon.stockservice.domain.spi;

import com.emazon.stockservice.domain.models.Brand;
import com.emazon.stockservice.domain.utils.Pagination;
import com.emazon.stockservice.domain.utils.SortOrder;

import java.util.List;

public interface IBrandPersistencePort {
    Brand save(Brand brand);
    List<Brand> getAllBrands(Pagination pagination, SortOrder sortOrder);
    long countTotalBrands();
}
