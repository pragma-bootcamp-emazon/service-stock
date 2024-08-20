package com.emazon.stockservice.Infrastructure.configuration;

import com.emazon.stockservice.Application.service.CategoryServiceImpl;
import com.emazon.stockservice.Application.service.ICategoryService;
import com.emazon.stockservice.Domain.port.ICategoryPersistencePort;
import com.emazon.stockservice.Infrastructure.Web.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.stockservice.Infrastructure.adapter.CategoryRepository;

import com.emazon.stockservice.Infrastructure.adapter.persistence.CategoryJpaAdapter;
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
