package com.emazon.stockservice.infrastructure.config;


import com.emazon.stockservice.application.mapper.brand.IBrandRequestMapper;
import com.emazon.stockservice.application.mapper.brand.IBrandResponseMapper;
import com.emazon.stockservice.application.service.ICategoryService;

import com.emazon.stockservice.application.service.brand.BrandServiceImpl;
import com.emazon.stockservice.application.usecase.create.brand.ICreateBrandUseCase;

import com.emazon.stockservice.application.service.IBrandService;
import com.emazon.stockservice.application.service.CategoryServiceImpl;
import com.emazon.stockservice.application.mapper.ICategoryRequestMapper;
import com.emazon.stockservice.application.mapper.ICategoryResponseMapperApplication;
import com.emazon.stockservice.application.usecase.create.ICreateCategoryUseCase;
import com.emazon.stockservice.application.usecase.retrieve.IRetrieveCategories;
import com.emazon.stockservice.domain.spi.IBrandPersistencePort;
import com.emazon.stockservice.domain.spi.ICategoryPersistencePort;
import com.emazon.stockservice.infrastructure.mappers.brand.BrandEntityMapper;
import com.emazon.stockservice.infrastructure.web.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.stockservice.infrastructure.repository.BrandRepository;
import com.emazon.stockservice.infrastructure.repository.CategoryRepository;
import com.emazon.stockservice.infrastructure.adapter.persistence.BrandJpaAdapter;
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
    public ICategoryService categoryService(ICreateCategoryUseCase createCategoryUseCase, IRetrieveCategories retrieveCategories, ICategoryRequestMapper categoryRequestMapper, ICategoryResponseMapperApplication categoryResponseMapper) {
        return new CategoryServiceImpl(createCategoryUseCase, retrieveCategories, categoryRequestMapper, categoryResponseMapper);
    }

    @Bean
    public IBrandPersistencePort brandPersistencePort(BrandRepository brandRepository, BrandEntityMapper brandEntityMapper) {
        return new BrandJpaAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    public IBrandService brandService(ICreateBrandUseCase createBrandUseCase,  IBrandRequestMapper brandRequestMapper, IBrandResponseMapper brandResponseMapper) {
        return new BrandServiceImpl(createBrandUseCase, brandRequestMapper, brandResponseMapper);
    }
}
