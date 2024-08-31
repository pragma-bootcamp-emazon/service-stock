package com.emazon.stockservice.infrastructure.output.jpa.mappers.category;
import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.infrastructure.output.jpa.entities.CategoryEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CategoryEntityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    public Category toDomain(CategoryEntity categoryEntity) {
        return new Category(categoryEntity.getId(), categoryEntity.getName(), categoryEntity.getDescription(), categoryEntity.getCreatedAt(), categoryEntity.getUpdatedAt());
    }

    public abstract CategoryEntity toEntity(Category category);

    public abstract List<Category> toDomainList(List<CategoryEntity> categoryEntities);
}
