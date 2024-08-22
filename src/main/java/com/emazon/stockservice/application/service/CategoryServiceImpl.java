package com.emazon.stockservice.application.service;

import com.emazon.stockservice.application.mapper.ICategoryRequestMapper;
import com.emazon.stockservice.application.mapper.ICategoryResponseMapperApplication;
import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.spi.ICategoryPersistencePort;
import com.emazon.stockservice.application.exceptions.CategoryAlreadyExistsException;
import com.emazon.stockservice.application.dto.CategoryRequest;
import com.emazon.stockservice.application.dto.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryPersistencePort categoryPersistencePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapperApplication categoryResponseMapper;

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        // Convertir DTO de solicitud a entidad de dominio
        Category category = categoryRequestMapper.toCategory(categoryRequest);

        // Verificar si la categoría ya existe
        if (categoryPersistencePort.existsByName(category.getName())) {
            throw new CategoryAlreadyExistsException("Category name already exists");
        }

        // Guardar la entidad de dominio en el puerto de persistencia
        Category savedCategory = categoryPersistencePort.save(category);

        // Convertir la entidad de dominio guardada en un DTO de respuesta
        return categoryResponseMapper.toCategoryResponse(savedCategory);
    }
}
