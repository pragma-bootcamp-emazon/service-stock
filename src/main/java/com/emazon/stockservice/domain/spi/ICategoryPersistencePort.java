package com.emazon.stockservice.domain.spi;

import com.emazon.stockservice.domain.models.Category;
import java.util.List;

public interface ICategoryPersistencePort {
    Category save(Category category);
    List<Category> findAll();
    boolean existsByName(String name);
}