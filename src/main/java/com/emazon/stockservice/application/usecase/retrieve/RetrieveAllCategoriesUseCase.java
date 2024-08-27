package com.emazon.stockservice.application.usecase.retrieve;

import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.models.Pagination;
import com.emazon.stockservice.domain.models.SortOrder;
import com.emazon.stockservice.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RetrieveAllCategoriesUseCase implements IRetrieveCategories {
    private final ICategoryPersistencePort categoryPersistencePort;

    @Override
    public List<Category> execute(Pagination pagination, SortOrder sortOrder) {
        return categoryPersistencePort.getAllCategories(pagination, sortOrder);
    }

    @Override
    public long countTotalCategories() {
        return categoryPersistencePort.countTotalCategories();
    }
}
