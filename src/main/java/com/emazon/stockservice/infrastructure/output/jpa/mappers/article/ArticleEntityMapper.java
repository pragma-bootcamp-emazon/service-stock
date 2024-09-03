package com.emazon.stockservice.infrastructure.output.jpa.mappers.article;

import com.emazon.stockservice.domain.models.Article;
import com.emazon.stockservice.domain.models.Brand;
import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.infrastructure.output.jpa.entities.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArticleEntityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "brand", source = "brand")
    default Article toDomain(ArticleEntity articleEntity) {
        return Article.createWithId(
                articleEntity.getId(),
                articleEntity.getName(),
                articleEntity.getDescription(),
                articleEntity.getQuantity(),
                articleEntity.getPrice(),
                // Deja las categorías como están en tu implementación original
                articleEntity.getCategories().stream().map(categoryEntity -> new Category(categoryEntity.getId(), categoryEntity.getName(), categoryEntity.getDescription(), categoryEntity.getCreatedAt(), categoryEntity.getUpdatedAt())).toList(),
                articleEntity.getBrand() != null ? new Brand(articleEntity.getBrand().getName(), articleEntity.getDescription()) : null,
                articleEntity.getCreatedAt()
        );
    }

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "brand", source = "brand")
    ArticleEntity toEntity(Article article);
}
