package com.emazon.stockservice.domain.spi;

import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.utils.PaginatedResult;
import com.emazon.stockservice.domain.utils.Pagination;
import com.emazon.stockservice.domain.utils.SortOrder;

import java.util.List;

public interface ICategoryPersistencePort {
    Category save(Category category);
    PaginatedResult<Category> getAllCategories(Pagination pagination, SortOrder sortOrder);
    boolean existsByName(String name);
    long countTotalCategories();
}
