package com.emazon.stockservice.infrastructure.adapters;

import com.emazon.stockservice.domain.models.Brand;
import com.emazon.stockservice.infrastructure.output.jpa.entities.BrandEntity;
import com.emazon.stockservice.infrastructure.output.jpa.mappers.brand.BrandEntityMapper;
import com.emazon.stockservice.infrastructure.output.jpa.adapters.BrandJpaAdapter;
import com.emazon.stockservice.infrastructure.output.jpa.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BrandJpaAdapterTest {

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private BrandEntityMapper brandEntityMapper;

    @InjectMocks
    private BrandJpaAdapter brandJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnSavedBrand_WhenBrandIsSavedSuccessfully() {

        Brand brand = new Brand(null, "Brand Name", "Brand Description", null, null);
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("Brand Name");
        brandEntity.setDescription("Brand Description");

        BrandEntity savedEntity = new BrandEntity();
        savedEntity.setId(1L);
        savedEntity.setName("Brand Name");
        savedEntity.setDescription("Brand Description");

        Brand expectedBrand = new Brand(1L, "Brand Name", "Brand Description", null, null);

        when(brandEntityMapper.toEntity(brand)).thenReturn(brandEntity);
        when(brandRepository.save(brandEntity)).thenReturn(savedEntity);
        when(brandEntityMapper.toDomain(savedEntity)).thenReturn(expectedBrand);

        Brand result = brandJpaAdapter.save(brand);

        assertEquals(expectedBrand, result);
        verify(brandEntityMapper, times(1)).toEntity(brand);
        verify(brandRepository, times(1)).save(brandEntity);
        verify(brandEntityMapper, times(1)).toDomain(savedEntity);
    }
}
