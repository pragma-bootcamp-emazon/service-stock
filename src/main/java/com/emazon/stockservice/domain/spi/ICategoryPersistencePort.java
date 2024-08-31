package com.emazon.stockservice.domain.spi;

import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.utils.PaginatedResult;
import com.emazon.stockservice.domain.utils.Pagination;
import com.emazon.stockservice.domain.utils.SortCriteria;

public interface ICategoryPersistencePort {
    Category save(Category category);
    PaginatedResult<Category> getAllCategories(Pagination pagination, SortCriteria sortOrder);
    boolean existsByName(String name);
    long countTotalCategories();
}
