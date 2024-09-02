package com.emazon.stockservice.infrastructure.output.jpa.mappers.article;

import com.emazon.stockservice.domain.models.Article;
import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.infrastructure.output.jpa.entities.ArticleEntity;
import com.emazon.stockservice.infrastructure.output.jpa.entities.CategoryEntity;
import com.emazon.stockservice.infrastructure.output.jpa.mappers.category.CategoryEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = CategoryEntityMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ArticleEntityMapper {

    @Mapping(target = "categories", source = "categories")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    default Article toDomain(ArticleEntity articleEntity) {
        return Article.createWithId(
                articleEntity.getId(),
                articleEntity.getName(),
                articleEntity.getDescription(),
                articleEntity.getQuantity(),
                articleEntity.getPrice(),
                mapCategories(articleEntity.getCategories()),
                articleEntity.getBrandId(),
                articleEntity.getCreatedAt()
        );
    }

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ArticleEntity toEntity(Article article);

    default List<Category> mapCategories(List<CategoryEntity> categoryEntities) {
        return categoryEntities.stream()
                .map(categoryEntity -> new Category(categoryEntity.getId()))
                .toList();
    }
}

