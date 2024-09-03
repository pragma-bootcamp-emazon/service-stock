package com.emazon.stockservice.application.mapper.article;

import com.emazon.stockservice.application.dto.article.ArticleRequest;
import com.emazon.stockservice.domain.models.Article;
import com.emazon.stockservice.domain.models.Brand;
import com.emazon.stockservice.domain.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class IArticleRequestMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public Article toArticle(ArticleRequest articleRequest) {
        List<Category> categories = articleRequest.getCategories().stream()
                .map(Category::new)
                .toList();

        return Article.createWithoutId(
                articleRequest.getName(),
                articleRequest.getDescription(),
                articleRequest.getQuantity(),
                articleRequest.getPrice(),
                categories,
                Brand.createWithId(articleRequest.getBrandId())
        );
    }
}
