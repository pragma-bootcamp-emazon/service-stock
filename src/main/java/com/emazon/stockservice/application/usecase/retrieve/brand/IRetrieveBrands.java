package com.emazon.stockservice.application.usecase.retrieve.brand;

import com.emazon.stockservice.domain.models.Brand;
import com.emazon.stockservice.domain.models.Pagination;
import com.emazon.stockservice.domain.models.SortOrder;

import java.util.List;

public interface IRetrieveBrands {
    List<Brand> execute(Pagination pagination, SortOrder sortOrder);
    long countTotalBrands();
}
