package com.emazon.stockservice.domain.usecases.category.retrieve;

import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.utils.PaginatedResult;
import com.emazon.stockservice.domain.utils.Pagination;
import com.emazon.stockservice.domain.utils.SortCriteria;


public interface IRetrieveCategories {
    PaginatedResult<Category> execute(Pagination pagination, SortCriteria sortOrder);

    long countTotalCategories();
}
