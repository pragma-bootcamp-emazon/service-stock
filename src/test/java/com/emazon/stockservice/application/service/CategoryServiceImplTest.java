package com.emazon.stockservice.application.service;

import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.spi.ICategoryPersistencePort;
import com.emazon.stockservice.application.exceptions.CategoryAlreadyExistsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    private final ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
    private final CategoryServiceImpl categoryService = new CategoryServiceImpl(categoryPersistencePort);

    @Test
    void createCategory_ThrowsException_WhenNameAlreadyExists() {
        Category category = new Category(null, "Existing Name", "Description");
        when(categoryPersistencePort.existsByName(category.getName())).thenReturn(true);

        assertThrows(CategoryAlreadyExistsException.class, () -> {
            categoryService.createCategory(category);
        });

        verify(categoryPersistencePort, never()).save(any(Category.class));
    }

    @Test
    void createCategory_SavesCategory_WhenDataIsValid() {
        Category category = new Category(null, "New Name", "Description");
        when(categoryPersistencePort.existsByName(category.getName())).thenReturn(false);
        Category savedCategory = new Category(1L, "New Name", "Description");
        when(categoryPersistencePort.save(any(Category.class))).thenReturn(savedCategory);

        Category result = categoryService.createCategory(category);

        assertEquals(savedCategory.getId(), result.getId());
        assertEquals(savedCategory.getName(), result.getName());
        assertEquals(savedCategory.getDescription(), result.getDescription());
        verify(categoryPersistencePort, times(1)).save(any(Category.class));
    }

}
