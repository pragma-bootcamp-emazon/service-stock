package com.emazon.stockservice.infrastructure.output.jpa.repository;
import com.emazon.stockservice.infrastructure.output.jpa.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsByName(String name);
    List<CategoryEntity> findByIdIn(List<Long> ids);
}

