package com.emazon.stockservice.application.handler.article;

import com.emazon.stockservice.application.dto.article.ArticleRequest;
import com.emazon.stockservice.application.dto.article.ArticleResponse;
import com.emazon.stockservice.domain.models.Article;

public interface IArticleHandler {
    ArticleResponse createArticle(ArticleRequest articleRequest);
}
