package com.emazon.stockservice.domain.usecases.category.create;

import com.emazon.stockservice.domain.exceptions.DomainException;
import com.emazon.stockservice.domain.exceptions.ErrorCode;
import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateCategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    private CreateCategoryUseCase createCategoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createCategoryUseCase = new CreateCategoryUseCase(categoryPersistencePort);
    }

    @Test
    void createCategory_Success() {
        String validDescription = "This is a valid description with more than 90";
        Category category = new Category("New Category", validDescription);
        Category savedCategory = new Category(1L, "New Category", validDescription);

        when(categoryPersistencePort.existsByName(anyString())).thenReturn(false);
        when(categoryPersistencePort.save(any(Category.class))).thenReturn(savedCategory);

        Category result = createCategoryUseCase.execute(category.getName(), category.getDescription());

        assertEquals(savedCategory, result);
        verify(categoryPersistencePort, times(1)).existsByName(category.getName());
        verify(categoryPersistencePort, times(1)).save(any(Category.class));
    }

    @Test
    void createCategory_AlreadyExists() {
        when(categoryPersistencePort.existsByName("Existing Category")).thenReturn(true);

        DomainException exception = assertThrows(DomainException.class, () ->
                executeCreateCategory("Existing Category", "Description")
        );

        assertEquals(ErrorCode.CATEGORY_ALREADY_EXISTS, exception.getErrorCode());
        verify(categoryPersistencePort, times(1)).existsByName("Existing Category");
        verify(categoryPersistencePort, never()).save(any(Category.class));
    }

    @Test
    void createCategory_NameTooLong() {
        String longName = "This is a very long category name that exceeds fifty characters";

        DomainException exception = assertThrows(DomainException.class, () ->
                new Category(longName, "Description")
        );

        assertEquals(ErrorCode.INVALID_CATEGORY_NAME, exception.getErrorCode());
    }

    @Test
    void createCategory_DescriptionTooLong() {
        String shortDescription = "This is a valid description, this reacted for fields greater than 90 characters, this is very long description "; // <= 90 characters

        DomainException exception = assertThrows(DomainException.class, () ->
                new Category("Valid Name", shortDescription)
        );

        assertEquals(ErrorCode.INVALID_CATEGORY_DESCRIPTION, exception.getErrorCode());
    }

    private void executeCreateCategory(String name, String description) {
        createCategoryUseCase.execute(name, description);
    }
}
