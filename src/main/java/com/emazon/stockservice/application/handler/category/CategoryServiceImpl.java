package com.emazon.stockservice.application.handler.category;

import com.emazon.stockservice.application.mapper.ICategoryRequestMapper;
import com.emazon.stockservice.application.mapper.ICategoryResponseMapperApplication;
import com.emazon.stockservice.application.usecase.retrieve.IRetrieveCategories;
import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.models.Pagination;
import com.emazon.stockservice.domain.models.SortOrder;
import com.emazon.stockservice.application.dto.category.CategoryRequest;
import com.emazon.stockservice.application.dto.category.CategoryResponse;
import com.emazon.stockservice.domain.usecases.category.create.ICreateCategoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final ICreateCategoryUseCase createCategoryUseCase;
    private final IRetrieveCategories retrieveCategories;
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

    @Override
    public Page<CategoryResponse> getAllCategories(Pageable pageable, Sort.Direction sortDirection, String sortBy) {

        String sortField = (sortBy == null || sortBy.isEmpty()) ? "name" : sortBy;
        Pageable pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortDirection, sortField));


        List<Category> categories = retrieveCategories.execute(
                new Pagination(pageRequest.getPageNumber(), pageRequest.getPageSize()),
                new SortOrder(sortField, SortOrder.Direction.valueOf(sortDirection.name()))
        );

        long totalElements = retrieveCategories.countTotalCategories();

        List<CategoryResponse> responseList = categories.stream()
                .map(categoryResponseMapper::toCategoryResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(responseList, pageable, totalElements);
    }

}
