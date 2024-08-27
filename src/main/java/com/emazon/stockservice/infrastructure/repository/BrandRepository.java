package com.emazon.stockservice.infrastructure.repository;

import com.emazon.stockservice.infrastructure.entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    boolean existsByName(String name);
}
