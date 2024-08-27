package com.emazon.stockservice.application.usecase.retrieve;

import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.models.Pagination;
import com.emazon.stockservice.domain.models.SortOrder;

import java.util.List;

public interface IRetrieveCategories {
    List<Category> execute(Pagination pagination, SortOrder sortOrder);

    long countTotalCategories();
}
