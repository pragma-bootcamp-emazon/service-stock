package com.emazon.stockservice.infrastructure.Web.output.jpa.mapper;
import com.emazon.stockservice.domain.model.Category;
import com.emazon.stockservice.infrastructure.entity.CategoryEntity;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {
    CategoryEntity toEntity(Category category);
    Category toDomain(CategoryEntity categoryEntity);
    List<Category> toDomainList(List<CategoryEntity> categoryEntities);
}