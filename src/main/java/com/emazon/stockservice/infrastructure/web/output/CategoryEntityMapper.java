package com.emazon.stockservice.infrastructure.web.output;
import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.infrastructure.entities.CategoryEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CategoryEntityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    public Category toDomain(CategoryEntity categoryEntity) {
        return new Category(categoryEntity.getId(), categoryEntity.getName(), categoryEntity.getDescription());
    }

    public abstract CategoryEntity toEntity(Category category);

    public abstract List<Category> toDomainList(List<CategoryEntity> categoryEntities);
}
