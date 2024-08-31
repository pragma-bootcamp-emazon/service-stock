package com.emazon.stockservice.domain.usecases.category.retrieve;

import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.utils.PaginatedResult;
import com.emazon.stockservice.domain.utils.Pagination;
import com.emazon.stockservice.domain.utils.SortOrder;
import com.emazon.stockservice.domain.spi.ICategoryPersistencePort;


public class RetrieveAllCategoriesUseCase implements IRetrieveCategories {

    private final ICategoryPersistencePort categoryPersistencePort;

    public RetrieveAllCategoriesUseCase(ICategoryPersistencePort categoryPersistencePort){
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public PaginatedResult<Category> execute(Pagination pagination, SortOrder sortOrder) {
        return categoryPersistencePort.getAllCategories(pagination, sortOrder);
    }

    @Override
    public long countTotalCategories() {
        return categoryPersistencePort.countTotalCategories();
    }
}
