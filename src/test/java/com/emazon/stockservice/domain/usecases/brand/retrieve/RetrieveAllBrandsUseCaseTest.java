package com.emazon.stockservice.domain.usecases.brand.retrieve;

import com.emazon.stockservice.domain.models.Brand;
import com.emazon.stockservice.domain.spi.IBrandPersistencePort;
import com.emazon.stockservice.domain.utils.PaginatedResult;
import com.emazon.stockservice.domain.utils.Pagination;
import com.emazon.stockservice.domain.utils.SortCriteria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RetrieveAllBrandsUseCaseTest {

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    private RetrieveAllBrandsUseCase retrieveAllBrandsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        retrieveAllBrandsUseCase = new RetrieveAllBrandsUseCase(brandPersistencePort);
    }

    @Test
    void retrieveAllBrands_Success() {
        Pagination pagination = new Pagination(0, 10);
        SortCriteria sortOrder = new SortCriteria("name", SortCriteria.Direction.ASC);

        Brand brand1 = new Brand(1L, "Brand 1", "Description 1", null, null);
        Brand brand2 = new Brand(2L, "Brand 2", "Description 2", null, null);
        List<Brand> brands = Arrays.asList(brand1, brand2);

        PaginatedResult<Brand> paginatedResult = new PaginatedResult<>(brands, 0, 10, 2, 1);

        when(brandPersistencePort.getAllBrands(pagination, sortOrder)).thenReturn(paginatedResult);

        PaginatedResult<Brand> result = retrieveAllBrandsUseCase.execute(pagination, sortOrder);

        assertEquals(paginatedResult, result);
        verify(brandPersistencePort, times(1)).getAllBrands(pagination, sortOrder);
    }

    @Test
    void countTotalBrands_Success() {
        when(brandPersistencePort.countTotalBrands()).thenReturn(5L);

        long totalBrands = retrieveAllBrandsUseCase.countTotalBrands();

        assertEquals(5L, totalBrands);
        verify(brandPersistencePort, times(1)).countTotalBrands();
    }
}
