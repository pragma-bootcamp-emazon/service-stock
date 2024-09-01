package com.emazon.stockservice.infrastructure.output.jpa.adapters;

import com.emazon.stockservice.domain.models.Article;
import com.emazon.stockservice.domain.spi.IArticlePersistencePort;
import com.emazon.stockservice.infrastructure.output.jpa.entities.ArticleEntity;
import com.emazon.stockservice.infrastructure.output.jpa.mappers.article.ArticleEntityMapper;
import com.emazon.stockservice.infrastructure.output.jpa.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {

    private final ArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Override
    public Article save(Article article) {
        ArticleEntity articleEntity = articleEntityMapper.toEntity(article);

        ArticleEntity savedEntity = articleRepository.save(articleEntity);

        return article;
    }

    @Override
    public boolean existsByName(String name) {
        return articleRepository.existsByName(name);
    }
}
