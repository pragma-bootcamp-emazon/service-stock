package com.emazon.stockservice.infrastructure.web.output.jpa.mapper;
import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.infrastructure.entities.CategoryEntity;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {
    CategoryEntity toEntity(Category category);
    Category toDomain(CategoryEntity categoryEntity);
    List<Category> toDomainList(List<CategoryEntity> categoryEntities);
}