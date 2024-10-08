package com.emazon.stockservice.application.handler.article;

import com.emazon.stockservice.application.dto.article.ArticleRequest;

import com.emazon.stockservice.application.dto.article.ArticleResponse;
import com.emazon.stockservice.application.mapper.article.IArticleRequestMapper;
import com.emazon.stockservice.application.mapper.article.IArticleResponseMapper;
import com.emazon.stockservice.domain.models.Article;
import com.emazon.stockservice.domain.usecases.article.create.ICreateArticleUseCase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ArticleHandler implements IArticleHandler {

    private final ICreateArticleUseCase createArticleUseCase;
    private final IArticleRequestMapper articleRequestMapper;
    private final IArticleResponseMapper articleResponseMapper;

    @Override
    public ArticleResponse createArticle(ArticleRequest articleRequest) {
        Article article = articleRequestMapper.toArticle(articleRequest);

        Article articleSaved = createArticleUseCase.executeWithIds(
                article.getName(),
                article.getDescription(),
                article.getQuantity(),
                article.getPrice(),
                articleRequest.getCategories(),
                article.getBrandId()
                );
        return articleResponseMapper.toArticleResponse(articleSaved);
    }
}

