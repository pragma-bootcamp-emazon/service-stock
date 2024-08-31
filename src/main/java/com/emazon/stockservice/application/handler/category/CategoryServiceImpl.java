package com.emazon.stockservice.application.handler.category;

import com.emazon.stockservice.application.dto.category.PaginatedResponse;
import com.emazon.stockservice.application.mapper.ICategoryRequestMapper;
import com.emazon.stockservice.application.mapper.ICategoryResponseMapperApplication;
import com.emazon.stockservice.domain.usecases.category.retrieve.IRetrieveCategories;
import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.utils.Pagination;
import com.emazon.stockservice.domain.utils.SortOrder;
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
    private final IRetrieveCategories retrieveCategoriesUseCase;
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
    public PaginatedResponse<CategoryResponse> getAllCategories(Pageable pageable) {
        Pagination pagination = new Pagination(pageable.getPageNumber(), pageable.getPageSize());
        Sort.Order order = pageable.getSort().stream().findFirst().orElse(Sort.Order.by("name")); // default order
        SortOrder.Direction direction = order.isAscending() ? SortOrder.Direction.ASC : SortOrder.Direction.DESC;
        SortOrder sortOrder = new SortOrder(order.getProperty(), direction);

        var paginatedResult = retrieveCategoriesUseCase.execute(pagination, sortOrder);

        List<CategoryResponse> content = paginatedResult.getContent().stream()
                .map(category -> new CategoryResponse(category.getId(), category.getName(), category.getDescription()))
                .collect(Collectors.toList());

        return new PaginatedResponse<>(
                content,
                paginatedResult.getPage(),
                paginatedResult.getSize(),
                paginatedResult.getTotalElements(),
                paginatedResult.getTotalPages()
        );
    }
}
