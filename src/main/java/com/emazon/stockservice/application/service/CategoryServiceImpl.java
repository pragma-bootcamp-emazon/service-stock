package com.emazon.stockservice.application.service;

import com.emazon.stockservice.application.mapper.ICategoryRequestMapper;
import com.emazon.stockservice.application.mapper.ICategoryResponseMapperApplication;
import com.emazon.stockservice.application.usecase.retrieve.IRetrieveCategories;
import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.models.Pagination;
import com.emazon.stockservice.domain.models.SortOrder;
import com.emazon.stockservice.application.dto.CategoryRequest;
import com.emazon.stockservice.application.dto.CategoryResponse;
import com.emazon.stockservice.application.usecase.create.ICreateCategoryUseCase;
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
        // Determinar el campo de ordenación
        String sortField = (sortBy == null || sortBy.isEmpty()) ? "name" : sortBy;
        Pageable pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortDirection, sortField));

        // Llamada al caso de uso para obtener categorías paginadas
        List<Category> categories = retrieveCategories.execute(
                new Pagination(pageRequest.getPageNumber(), pageRequest.getPageSize()),
                new SortOrder(sortField, SortOrder.Direction.valueOf(sortDirection.name()))
        );

        // Obtener el número total de elementos en la base de datos
        long totalElements = retrieveCategories.countTotalCategories(); // Debes implementar este método

        // Convertir la lista de categorías a DTOs
        List<CategoryResponse> responseList = categories.stream()
                .map(categoryResponseMapper::toCategoryResponse)
                .collect(Collectors.toList());

        // Crear el objeto Page con el total de elementos correcto
        return new PageImpl<>(responseList, pageable, totalElements);
    }

}
