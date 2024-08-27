package com.emazon.stockservice.application.service;

import com.emazon.stockservice.application.dto.CategoryRequest;
import com.emazon.stockservice.application.dto.CategoryResponse;
import com.emazon.stockservice.application.mapper.ICategoryRequestMapper;
import com.emazon.stockservice.application.mapper.ICategoryResponseMapperApplication;
import com.emazon.stockservice.application.usecase.create.ICreateCategoryUseCase;
import com.emazon.stockservice.application.usecase.retrieve.IRetrieveCategories;
import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.models.Pagination;
import com.emazon.stockservice.domain.models.SortOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    @Mock
    private ICreateCategoryUseCase createCategoryUseCase;

    @Mock
    private IRetrieveCategories retrieveCategories;

    @Mock
    private ICategoryRequestMapper categoryRequestMapper;

    @Mock
    private ICategoryResponseMapperApplication categoryResponseMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCategory_Success() {
        CategoryRequest request = new CategoryRequest("New Name", "Description");
        Category category = new Category(null, "New Name", "Description");
        Category savedCategory = new Category(1L, "New Name", "Description");
        CategoryResponse expectedResponse = new CategoryResponse(1L, "New Name", "Description");

        when(categoryRequestMapper.toCategory(request)).thenReturn(category);
        when(createCategoryUseCase.execute(category.getName(), category.getDescription())).thenReturn(savedCategory);
        when(categoryResponseMapper.toCategoryResponse(savedCategory)).thenReturn(expectedResponse);

        CategoryResponse result = categoryService.createCategory(request);

        assertEquals(expectedResponse, result);
        verify(createCategoryUseCase, times(1)).execute(category.getName(), category.getDescription());
    }

    @Test
    void getAllCategories_Success() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "name"));
        List<Category> categories = Arrays.asList(
                new Category(1L, "Name 1", "Description 1"),
                new Category(2L, "Name 2", "Description 2")
        );
        List<CategoryResponse> responses = Arrays.asList(
                new CategoryResponse(1L, "Name 1", "Description 1"),
                new CategoryResponse(2L, "Name 2", "Description 2")
        );
        long totalElements = 2L;

        when(retrieveCategories.execute(any(Pagination.class), any(SortOrder.class))).thenReturn(categories);
        when(retrieveCategories.countTotalCategories()).thenReturn(totalElements);
        when(categoryResponseMapper.toCategoryResponse(any(Category.class))).thenReturn(responses.get(0), responses.get(1));

        Page<CategoryResponse> result = categoryService.getAllCategories(pageable, Sort.Direction.ASC, "name");

        assertEquals(totalElements, result.getTotalElements());
        assertEquals(responses.size(), result.getContent().size());
        verify(retrieveCategories, times(1)).execute(any(Pagination.class), any(SortOrder.class));
    }
}
