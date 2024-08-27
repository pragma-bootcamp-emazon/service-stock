package com.emazon.stockservice.application.service.brand;

import com.emazon.stockservice.application.dto.brand.BrandRequest;
import com.emazon.stockservice.application.dto.brand.BrandResponse;
import com.emazon.stockservice.application.mapper.brand.IBrandRequestMapper;
import com.emazon.stockservice.application.mapper.brand.IBrandResponseMapper;
import com.emazon.stockservice.application.usecase.create.brand.ICreateBrandUseCase;
import com.emazon.stockservice.domain.models.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BrandServiceImplTest {

    @Mock
    private ICreateBrandUseCase createBrandUseCase;

    @Mock
    private IBrandRequestMapper brandRequestMapper;

    @Mock
    private IBrandResponseMapper brandResponseMapper;

    @InjectMocks
    private BrandServiceImpl brandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBrand_Success() {
        BrandRequest request = new BrandRequest("New Brand", "Description");
        Brand brand = new Brand(null, "New Brand", "Description");
        Brand savedBrand = new Brand(1L, "New Brand", "Description");
        BrandResponse expectedResponse = new BrandResponse(1L, "New Brand", "Description");

        when(brandRequestMapper.toBrand(request)).thenReturn(brand);
        when(createBrandUseCase.execute(brand.getName(), brand.getDescription())).thenReturn(savedBrand);
        when(brandResponseMapper.toBrandResponse(savedBrand)).thenReturn(expectedResponse);

        BrandResponse result = brandService.createBrand(request);

        assertEquals(expectedResponse, result);
        verify(createBrandUseCase, times(1)).execute(brand.getName(), brand.getDescription());
        verify(brandRequestMapper, times(1)).toBrand(request);
        verify(brandResponseMapper, times(1)).toBrandResponse(savedBrand);
    }
}
