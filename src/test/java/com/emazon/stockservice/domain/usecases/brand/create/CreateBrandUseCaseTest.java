package com.emazon.stockservice.domain.usecases.brand.create;

import com.emazon.stockservice.domain.exceptions.DomainException;
import com.emazon.stockservice.domain.exceptions.ErrorCode;
import com.emazon.stockservice.domain.models.Brand;
import com.emazon.stockservice.domain.spi.IBrandPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateBrandUseCaseTest {

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    private CreateBrandUseCase createBrandUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createBrandUseCase = new CreateBrandUseCase(brandPersistencePort);
    }

    @Test
    void createBrand_Success() {
        String validDescription = "This is a valid description with more than 90 characters";
        Brand brand = new Brand("New Brand", validDescription);
        Brand savedBrand = new Brand(1L, "New Brand", validDescription, null, null);

        when(brandPersistencePort.existsByName(anyString())).thenReturn(false);
        when(brandPersistencePort.save(any(Brand.class))).thenReturn(savedBrand);

        Brand result = createBrandUseCase.execute(brand.getName(), brand.getDescription());

        assertEquals(savedBrand, result);
        verify(brandPersistencePort, times(1)).existsByName(brand.getName());
        verify(brandPersistencePort, times(1)).save(any(Brand.class));
    }

    @Test
    void createBrand_AlreadyExists() {
        when(brandPersistencePort.existsByName("Existing Brand")).thenReturn(true);

        DomainException exception = assertThrows(DomainException.class, () ->
                executeCreateBrand("Existing Brand", "Description")
        );

        assertEquals(ErrorCode.BRAND_ALREADY_EXISTS, exception.getErrorCode());
        verify(brandPersistencePort, times(1)).existsByName("Existing Brand");
        verify(brandPersistencePort, never()).save(any(Brand.class));
    }

    @Test
    void createBrand_NameTooLong() {
        String longName = "This is a very long brand name that exceeds fifty characters";

        DomainException exception = assertThrows(DomainException.class, () ->
                new Brand(longName, "Description")
        );

        assertEquals(ErrorCode.INVALID_BRAND_NAME, exception.getErrorCode());
    }

    @Test
    void createBrand_DescriptionTooLong() {
        String longDescription = "This is a valid description, this reacted for fields greater than 120 characters, this is very long description, adding more characters to exceed the limit of 120 characters"; // <= 120 characters

        DomainException exception = assertThrows(DomainException.class, () ->
                new Brand("Valid Name", longDescription)
        );

        assertEquals(ErrorCode.INVALID_BRAND_DESCRIPTION, exception.getErrorCode());
    }

    private void executeCreateBrand(String name, String description) {
        createBrandUseCase.execute(name, description);
    }
}
