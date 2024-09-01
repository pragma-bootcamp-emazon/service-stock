package com.emazon.stockservice.application.handler.article;

import com.emazon.stockservice.application.dto.article.ArticleRequest;

public interface IArticleHandler {
    void createArticle(ArticleRequest articleRequest);

}
