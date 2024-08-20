package com.emazon.stockservice.infrastructure.adapter;
import com.emazon.stockservice.infrastructure.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsByName(String name);
}

