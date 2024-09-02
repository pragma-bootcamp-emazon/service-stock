package com.emazon.stockservice.infrastructure.output.jpa.adapters;

import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.utils.PaginatedResult;
import com.emazon.stockservice.domain.utils.Pagination;
import com.emazon.stockservice.domain.utils.SortCriteria;
import com.emazon.stockservice.domain.spi.ICategoryPersistencePort;
import com.emazon.stockservice.infrastructure.output.jpa.repository.CategoryRepository;
import com.emazon.stockservice.infrastructure.output.jpa.entities.CategoryEntity;
import com.emazon.stockservice.infrastructure.output.jpa.mappers.category.CategoryEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public Category save(Category category) {
        CategoryEntity categoryEntity = categoryEntityMapper.toEntity(category);
        CategoryEntity savedEntity = categoryRepository.save(categoryEntity);
        return categoryEntityMapper.toDomain(savedEntity);
    }

    @Override
    public PaginatedResult<Category> getAllCategories(Pagination pagination, SortCriteria sortOrder) {
        Pageable pageable = PageRequest.of(
                pagination.getPage(),
                pagination.getSize(),
                Sort.by(Sort.Direction.valueOf(sortOrder.getDirection().name()), sortOrder.getSortBy())
        );

        Page<CategoryEntity> categoryPage = categoryRepository.findAll(pageable);

        List<Category> categories = categoryPage.getContent()
                .stream()
                .map(categoryEntityMapper::toDomain)
                .toList();

        return new PaginatedResult<>(
                categories,
                pagination.getPage(),
                pagination.getSize(),
                categoryPage.getTotalElements(),
                categoryPage.getTotalPages()
        );
    }

    @Override
    public List<Category> findByIds(List<Long> ids) {
        return categoryRepository.findByIdIn(ids).stream()
                .map(categoryEntityMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    public long countTotalCategories() {
        return categoryRepository.count();
    }
}
