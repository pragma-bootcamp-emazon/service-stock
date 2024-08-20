package com.emazon.stockservice.Infrastructure.adapter;
import com.emazon.stockservice.Domain.model.Category;
import com.emazon.stockservice.Infrastructure.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsByName(String name);
}

