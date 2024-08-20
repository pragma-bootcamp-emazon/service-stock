package com.emazon.stockservice.Domain.port;

import com.emazon.stockservice.Domain.model.Category;
import java.util.List;

public interface ICategoryPersistencePort {
    Category save(Category category);
    List<Category> findAll();
    boolean existsByName(String name);
}