package com.emazon.stockservice.application.service;

import com.emazon.stockservice.domain.model.Category;
import com.emazon.stockservice.domain.port.ICategoryPersistencePort;
import com.emazon.stockservice.infrastructure.Web.dto.CategoryRequest;
import com.emazon.stockservice.infrastructure.Web.dto.CategoryResponse;
import com.emazon.stockservice.infrastructure.exception.CategoryAlreadyExistsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    private final ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
    private final CategoryServiceImpl categoryService = new CategoryServiceImpl(categoryPersistencePort);

    @Test
    void createCategory_ThrowsException_WhenNameAlreadyExists() {
        CategoryRequest request = new CategoryRequest("Existing Name", "Description");
        when(categoryPersistencePort.existsByName(request.getName())).thenReturn(true);

        assertThrows(CategoryAlreadyExistsException.class, () -> {
            categoryService.createCategory(request);
        });

        verify(categoryPersistencePort, never()).save(any(Category.class));
    }

    @Test
    void createCategory_SavesCategory_WhenDataIsValid() {
        CategoryRequest request = new CategoryRequest("New Name", "Description");
        when(categoryPersistencePort.existsByName(request.getName())).thenReturn(false);
        Category savedCategory = new Category(1L, "New Name", "Description");
        when(categoryPersistencePort.save(any(Category.class))).thenReturn(savedCategory);

        CategoryResponse response = categoryService.createCategory(request);

        assertEquals(savedCategory.getId(), response.getId());
        assertEquals(savedCategory.getName(), response.getName());
        assertEquals(savedCategory.getDescription(), response.getDescription());
        verify(categoryPersistencePort, times(1)).save(any(Category.class));
    }
}
