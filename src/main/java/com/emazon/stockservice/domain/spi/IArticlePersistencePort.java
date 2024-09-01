package com.emazon.stockservice.domain.spi;

import com.emazon.stockservice.domain.models.Article;


public interface IArticlePersistencePort {

    Article save(Article article);
    boolean existsByName(String name);
}
