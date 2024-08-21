package com.emazon.stockservice.infrastructure.config;

import com.emazon.stockservice.application.service.CategoryServiceImpl;
import com.emazon.stockservice.application.service.ICategoryService;
import com.emazon.stockservice.domain.spi.ICategoryPersistencePort;
import com.emazon.stockservice.infrastructure.web.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.stockservice.infrastructure.adapter.CategoryRepository;

import com.emazon.stockservice.infrastructure.adapter.persistence.CategoryJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(CategoryRepository categoryRepository, CategoryEntityMapper categoryEntityMapper) {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryService categoryService(ICategoryPersistencePort categoryPersistencePort) {
        return new CategoryServiceImpl(categoryPersistencePort);
    }
}
