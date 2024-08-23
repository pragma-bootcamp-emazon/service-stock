package com.emazon.stockservice.application.service;

import com.emazon.stockservice.application.mapper.ICategoryRequestMapper;
import com.emazon.stockservice.application.mapper.ICategoryResponseMapperApplication;
import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.application.dto.CategoryRequest;
import com.emazon.stockservice.application.dto.CategoryResponse;
import com.emazon.stockservice.application.usecase.ICreateCategoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final ICreateCategoryUseCase createCategoryUseCase;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapperApplication categoryResponseMapper;

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = categoryRequestMapper.toCategory(categoryRequest);
        Category createdCategory = createCategoryUseCase.execute(
                category.getName(),
                category.getDescription()
        );
             return categoryResponseMapper.toCategoryResponse(createdCategory);
    }
}
