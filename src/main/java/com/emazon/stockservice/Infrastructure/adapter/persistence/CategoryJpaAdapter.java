package com.emazon.stockservice.Infrastructure.adapter.persistence;

import com.emazon.stockservice.Domain.model.Category;
import com.emazon.stockservice.Domain.port.ICategoryPersistencePort;
import com.emazon.stockservice.Infrastructure.Web.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.stockservice.Infrastructure.adapter.CategoryRepository;
import com.emazon.stockservice.Infrastructure.entity.CategoryEntity;
import lombok.RequiredArgsConstructor;
import java.util.List;

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
    public List<Category> findAll() {
        List<CategoryEntity> entities = categoryRepository.findAll();
        return categoryEntityMapper.toDomainList(entities);
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }
}
