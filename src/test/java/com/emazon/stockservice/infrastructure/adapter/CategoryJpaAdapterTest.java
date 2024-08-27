package com.emazon.stockservice.infrastructure.adapter;

import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.models.Pagination;
import com.emazon.stockservice.domain.models.SortOrder;
import com.emazon.stockservice.infrastructure.adapter.persistence.CategoryJpaAdapter;
import com.emazon.stockservice.infrastructure.web.output.jpa.mapper.CategoryEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryJpaAdapterTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CategoryJpaAdapter categoryJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_Success() {
        Category category = new Category(null, "New Name", "Description");

        when(categoryEntityMapper.toEntity(category)).thenReturn(any());
        when(categoryRepository.save(any())).thenReturn(any());
        when(categoryEntityMapper.toDomain(any())).thenReturn(category);

        Category result = categoryJpaAdapter.save(category);

        assertEquals(category, result);
        verify(categoryRepository, times(1)).save(any());
    }

    @Test
    void getAllCategories_Success() {
        Pagination pagination = new Pagination(0, 10);
        SortOrder sortOrder = new SortOrder("name", SortOrder.Direction.ASC);

        Category category1 = new Category(1L, "Name 1", "Description 1");
        Category category2 = new Category(2L, "Name 2", "Description 2");



        when(categoryEntityMapper.toDomain(any())).thenReturn(category1, category2);

        List<Category> result = categoryJpaAdapter.getAllCategories(pagination, sortOrder);

        assertEquals(2, result.size());
        verify(categoryRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    void existsByName_ReturnsTrue_WhenCategoryExists() {
        String name = "Existing Name";

        when(categoryRepository.existsByName(name)).thenReturn(true);

        boolean exists = categoryJpaAdapter.existsByName(name);

        assertEquals(true, exists);
        verify(categoryRepository, times(1)).existsByName(name);
    }

    @Test
    void countTotalCategories_ReturnsCount() {
        long expectedCount = 5L;

        when(categoryRepository.count()).thenReturn(expectedCount);

        long result = categoryJpaAdapter.countTotalCategories();

        assertEquals(expectedCount, result);
        verify(categoryRepository, times(1)).count();
    }
}
