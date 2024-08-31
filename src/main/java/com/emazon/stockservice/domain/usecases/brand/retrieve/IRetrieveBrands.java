package com.emazon.stockservice.domain.usecases.brand.retrieve;

import com.emazon.stockservice.domain.models.Brand;
import com.emazon.stockservice.domain.utils.PaginatedResult;
import com.emazon.stockservice.domain.utils.Pagination;
import com.emazon.stockservice.domain.utils.SortCriteria;

public interface IRetrieveBrands {
    PaginatedResult<Brand> execute(Pagination pagination, SortCriteria sortOrder);
    long countTotalBrands();
}
