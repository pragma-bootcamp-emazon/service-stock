package com.emazon.stockservice.application.mapper.article;

import com.emazon.stockservice.application.dto.article.ArticleRequest;
import com.emazon.stockservice.domain.models.Article;
import com.emazon.stockservice.domain.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class IArticleRequestMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public Article toArticle(ArticleRequest articleRequest) {
        // Convierte los IDs de categorías a objetos Category
        List<Category> categories = articleRequest.getCategories().stream()
                .map(Category::new) // Constructor de solo ID
                .collect(Collectors.toList());

        // Usa el método de fábrica para crear el Article
        return Article.createWithoutId(
                articleRequest.getName(),
                articleRequest.getDescription(),
                articleRequest.getQuantity(),
                articleRequest.getPrice(),
                categories
        );
    }
}
