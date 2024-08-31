package com.emazon.stockservice.domain.usecases.category.retrieve;

import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.spi.ICategoryPersistencePort;
import com.emazon.stockservice.domain.utils.PaginatedResult;
import com.emazon.stockservice.domain.utils.Pagination;
import com.emazon.stockservice.domain.utils.SortOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RetrieveAllCategoriesUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    private RetrieveAllCategoriesUseCase retrieveAllCategoriesUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        retrieveAllCategoriesUseCase = new RetrieveAllCategoriesUseCase(categoryPersistencePort);
    }

    @Test
    void retrieveAllCategories_Success() {
        Pagination pagination = new Pagination(0, 10);
        SortOrder sortOrder = new SortOrder("name", SortOrder.Direction.ASC);

        Category category1 = new Category(1L, "Category 1", "Description 1");
        Category category2 = new Category(2L, "Category 2", "Description 2");
        List<Category> categories = Arrays.asList(category1, category2);

        PaginatedResult<Category> paginatedResult = new PaginatedResult<>(categories, 0, 10, 2, 1);

        when(categoryPersistencePort.getAllCategories(pagination, sortOrder)).thenReturn(paginatedResult);

        PaginatedResult<Category> result = retrieveAllCategoriesUseCase.execute(pagination, sortOrder);

        assertEquals(paginatedResult, result);
        verify(categoryPersistencePort, times(1)).getAllCategories(pagination, sortOrder);
    }

    @Test
    void countTotalCategories_Success() {
        when(categoryPersistencePort.countTotalCategories()).thenReturn(5L);

        long totalCategories = retrieveAllCategoriesUseCase.countTotalCategories();

        assertEquals(5L, totalCategories);
        verify(categoryPersistencePort, times(1)).countTotalCategories();
    }
}
