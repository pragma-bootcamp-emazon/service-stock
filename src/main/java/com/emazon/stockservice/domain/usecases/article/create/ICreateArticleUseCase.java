package com.emazon.stockservice.domain.usecases.article.create;

import com.emazon.stockservice.domain.models.Article;
import java.math.BigDecimal;
import java.util.List;

public interface ICreateArticleUseCase {
    Article executeWithIds(String name, String description, int quantity, BigDecimal price, List<Long> categoryIds);
}
