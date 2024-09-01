package com.emazon.stockservice.domain.usecases.article.create;

import com.emazon.stockservice.domain.exceptions.DomainException;
import com.emazon.stockservice.domain.exceptions.ErrorCode;
import com.emazon.stockservice.domain.models.Article;
import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.spi.IArticlePersistencePort;

import java.math.BigDecimal;
import java.util.List;

public class CreateArticleUseCase implements ICreateArticleUseCase {

    private final IArticlePersistencePort articlePersistencePort;

    public CreateArticleUseCase(IArticlePersistencePort articlePersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
    }

    @Override
    public Article executeWithIds(String name, String description, int quantity, BigDecimal price, List<Long> categoryIds) {
        List<Category> categories = categoryIds.stream()
                .map(Category::new)
                .toList();

        Article article = Article.createWithoutId(name, description, quantity, price, categories);

        if (articlePersistencePort.existsByName(name)) {
            throw new DomainException(ErrorCode.ARTICLE_ALREADY_EXISTS);
        }

        return articlePersistencePort.save(article);
    }
}