package com.emazon.stockservice.application.mapper;

import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.application.dto.category.CategoryRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ICategoryRequestMapper {

    public Category toCategory(CategoryRequest categoryRequest) {
        return new Category(categoryRequest.getName(), categoryRequest.getDescription());
    }
}
