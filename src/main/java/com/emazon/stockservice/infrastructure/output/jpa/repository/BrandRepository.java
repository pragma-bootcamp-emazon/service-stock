package com.emazon.stockservice.infrastructure.output.jpa.repository;

import com.emazon.stockservice.infrastructure.output.jpa.entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    boolean existsByName(String name);
}
