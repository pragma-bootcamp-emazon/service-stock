package com.emazon.stockservice.domain.usecases.brand.retrieve;

import com.emazon.stockservice.domain.models.Brand;
import com.emazon.stockservice.domain.utils.PaginatedResult;
import com.emazon.stockservice.domain.utils.Pagination;
import com.emazon.stockservice.domain.utils.SortCriteria;
import com.emazon.stockservice.domain.spi.IBrandPersistencePort;


public class RetrieveAllBrandsUseCase implements IRetrieveBrands {

    private final IBrandPersistencePort brandPersistencePort;

    public RetrieveAllBrandsUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }
    @Override
    public PaginatedResult<Brand> execute(Pagination pagination, SortCriteria sortOrder) {
        return brandPersistencePort.getAllBrands(pagination, sortOrder);
    }

    @Override
    public long countTotalBrands() {
        return brandPersistencePort.countTotalBrands();
    }
}
