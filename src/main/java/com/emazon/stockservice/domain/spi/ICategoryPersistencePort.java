package com.emazon.stockservice.domain.spi;

import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.models.Pagination;
import com.emazon.stockservice.domain.models.SortOrder;

import java.util.List;

public interface ICategoryPersistencePort {
    Category save(Category category);
    List<Category> getAllCategories(Pagination pagination, SortOrder sortOrder);
    boolean existsByName(String name);
    long countTotalCategories();
}
