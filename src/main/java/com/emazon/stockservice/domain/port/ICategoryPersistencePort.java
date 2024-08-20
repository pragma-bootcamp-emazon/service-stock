package com.emazon.stockservice.domain.port;

import com.emazon.stockservice.domain.model.Category;
import java.util.List;

public interface ICategoryPersistencePort {
    Category save(Category category);
    List<Category> findAll();
    boolean existsByName(String name);
}