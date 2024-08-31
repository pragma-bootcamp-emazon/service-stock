package com.emazon.stockservice.infrastructure.output.jpa.repository;
import com.emazon.stockservice.infrastructure.output.jpa.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsByName(String name);
}

