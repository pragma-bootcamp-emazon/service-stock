package com.emazon.stockservice.Infrastructure.Web.output.jpa.mapper;
import com.emazon.stockservice.Domain.model.Category;
import com.emazon.stockservice.Infrastructure.entity.CategoryEntity;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {
    CategoryEntity toEntity(Category category);
    Category toDomain(CategoryEntity categoryEntity);
    List<Category> toDomainList(List<CategoryEntity> categoryEntities);
}