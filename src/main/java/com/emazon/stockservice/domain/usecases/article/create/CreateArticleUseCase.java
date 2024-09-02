package com.emazon.stockservice.domain.usecases.article.create;

import com.emazon.stockservice.domain.exceptions.DomainException;
import com.emazon.stockservice.domain.exceptions.ErrorCode;
import com.emazon.stockservice.domain.models.Article;
import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.spi.IArticlePersistencePort;
import com.emazon.stockservice.domain.spi.ICategoryPersistencePort;


import java.math.BigDecimal;
import java.util.List;

public class CreateArticleUseCase implements ICreateArticleUseCase {

    private final IArticlePersistencePort articlePersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;


    public CreateArticleUseCase(IArticlePersistencePort articlePersistencePort, ICategoryPersistencePort categoryPersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public Article executeWithIds(String name, String description, int quantity, BigDecimal price, List<Long> categoryIds, Long brandId) {

        List<Category> categories = categoryPersistencePort.findByIds(categoryIds);

        if(categories.size() != categoryIds.size()) {
            throw new DomainException(ErrorCode.CATEGORY_NOT_FOUND);
        }

        if (articlePersistencePort.existsByName(name)) {
            throw new DomainException(ErrorCode.ARTICLE_ALREADY_EXISTS);
        }

        Article article = Article.createWithoutId(name, description, quantity, price, categories, brandId);


        return articlePersistencePort.save(article);
    }
}