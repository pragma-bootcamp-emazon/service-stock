package com.emazon.stockservice.application.handler.article;

import com.emazon.stockservice.application.dto.article.ArticleRequest;

import com.emazon.stockservice.application.mapper.article.IArticleRequestMapper;
import com.emazon.stockservice.domain.models.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleHandler {

    private final IArticleRequestMapper articleRequestMapper;

    public void handleArticleCreation(ArticleRequest articleRequest) {

        Article article = articleRequestMapper.toArticle(articleRequest);

    }
}
