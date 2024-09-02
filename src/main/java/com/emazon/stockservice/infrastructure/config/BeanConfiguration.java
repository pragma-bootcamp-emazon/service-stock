package com.emazon.stockservice.infrastructure.config;

import com.emazon.stockservice.application.mapper.brand.IBrandRequestMapper;
import com.emazon.stockservice.application.mapper.brand.IBrandResponseMapper;
import com.emazon.stockservice.application.handler.category.ICategoryHandler;
import com.emazon.stockservice.application.handler.brand.BrandHandler;
import com.emazon.stockservice.domain.spi.IArticlePersistencePort;
import com.emazon.stockservice.domain.usecases.article.create.CreateArticleUseCase;
import com.emazon.stockservice.domain.usecases.article.create.ICreateArticleUseCase;
import com.emazon.stockservice.domain.usecases.brand.create.CreateBrandUseCase;
import com.emazon.stockservice.domain.usecases.brand.create.ICreateBrandUseCase;
import com.emazon.stockservice.application.handler.brand.IBrandHandler;
import com.emazon.stockservice.application.handler.category.CategoryHandler;
import com.emazon.stockservice.application.mapper.ICategoryRequestMapper;
import com.emazon.stockservice.application.mapper.ICategoryResponseMapperApplication;
import com.emazon.stockservice.domain.usecases.brand.retrieve.RetrieveAllBrandsUseCase;
import com.emazon.stockservice.domain.usecases.category.create.CreateCategoryUseCase;
import com.emazon.stockservice.domain.usecases.category.create.ICreateCategoryUseCase;
import com.emazon.stockservice.domain.usecases.category.retrieve.IRetrieveCategories;
import com.emazon.stockservice.domain.usecases.brand.retrieve.IRetrieveBrands;
import com.emazon.stockservice.domain.spi.IBrandPersistencePort;
import com.emazon.stockservice.domain.spi.ICategoryPersistencePort;
import com.emazon.stockservice.domain.usecases.category.retrieve.RetrieveAllCategoriesUseCase;
import com.emazon.stockservice.infrastructure.output.jpa.mappers.brand.BrandEntityMapper;
import com.emazon.stockservice.infrastructure.output.jpa.mappers.category.CategoryEntityMapper;
import com.emazon.stockservice.infrastructure.output.jpa.repository.BrandRepository;
import com.emazon.stockservice.infrastructure.output.jpa.repository.CategoryRepository;
import com.emazon.stockservice.infrastructure.output.jpa.adapters.BrandJpaAdapter;
import com.emazon.stockservice.infrastructure.output.jpa.adapters.CategoryJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(CategoryRepository categoryRepository, CategoryEntityMapper categoryEntityMapper) {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICreateCategoryUseCase createCategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        return new CreateCategoryUseCase(categoryPersistencePort);
    }

    @Bean
    public ICreateBrandUseCase createBrandUseCase(IBrandPersistencePort brandPersistencePort) {
        return new CreateBrandUseCase(brandPersistencePort);
    }

    @Bean
    public ICreateArticleUseCase createArticleUseCase(IArticlePersistencePort articlePersistencePort, ICategoryPersistencePort categoryPersistencePort) {
        return new CreateArticleUseCase(articlePersistencePort, categoryPersistencePort);
    }

    @Bean
    public IRetrieveCategories retrieveCategories(ICategoryPersistencePort categoryPersistencePort) {
        return new RetrieveAllCategoriesUseCase(categoryPersistencePort);
    }

    @Bean
    public IRetrieveBrands retrieveBrands(IBrandPersistencePort brandPersistencePort) {
        return new RetrieveAllBrandsUseCase(brandPersistencePort);
    }

    @Bean
    public ICategoryHandler categoryService(ICreateCategoryUseCase createCategoryUseCase, IRetrieveCategories retrieveCategories, ICategoryRequestMapper categoryRequestMapper, ICategoryResponseMapperApplication categoryResponseMapper) {
        return new CategoryHandler(createCategoryUseCase, retrieveCategories, categoryRequestMapper, categoryResponseMapper);
    }

    @Bean
    public IBrandPersistencePort brandPersistencePort(BrandRepository brandRepository, BrandEntityMapper brandEntityMapper) {
        return new BrandJpaAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    public IBrandHandler brandService(ICreateBrandUseCase createBrandUseCase, IRetrieveBrands retrieveBrands, IBrandRequestMapper brandRequestMapper, IBrandResponseMapper brandResponseMapper) {
        return new BrandHandler(createBrandUseCase, retrieveBrands, brandRequestMapper, brandResponseMapper);
    }
}
