package com.emazon.stockservice.application.usecase.retrieve.brand;

import com.emazon.stockservice.domain.models.Brand;
import com.emazon.stockservice.domain.utils.Pagination;
import com.emazon.stockservice.domain.utils.SortOrder;
import com.emazon.stockservice.domain.spi.IBrandPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RetrieveAllBrandsUseCase implements IRetrieveBrands {

    private final IBrandPersistencePort brandPersistencePort;

    @Override
    public List<Brand> execute(Pagination pagination, SortOrder sortOrder) {
        return brandPersistencePort.getAllBrands(pagination, sortOrder);
    }

    @Override
    public long countTotalBrands() {
        return brandPersistencePort.countTotalBrands();
    }
}
