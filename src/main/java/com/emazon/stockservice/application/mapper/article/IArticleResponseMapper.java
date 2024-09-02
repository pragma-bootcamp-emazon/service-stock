package com.emazon.stockservice.application.mapper.article;

import com.emazon.stockservice.application.dto.article.ArticleResponse;
import com.emazon.stockservice.domain.models.Article;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IArticleResponseMapper {
    ArticleResponse toArticleResponse(Article brand);
}
