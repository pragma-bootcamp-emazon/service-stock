package com.emazon.stockservice.domain.usecases.article.create;

import com.emazon.stockservice.domain.exceptions.DomainException;
import com.emazon.stockservice.domain.exceptions.ErrorCode;
import com.emazon.stockservice.domain.models.Article;
import com.emazon.stockservice.domain.models.Brand;
import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.spi.IArticlePersistencePort;
import com.emazon.stockservice.domain.spi.IBrandPersistencePort;
import com.emazon.stockservice.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreateArticleUseCaseTest {

    @Mock
    private IArticlePersistencePort articlePersistencePort;

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    private CreateArticleUseCase createArticleUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createArticleUseCase = new CreateArticleUseCase(articlePersistencePort, categoryPersistencePort, brandPersistencePort);
    }

    @Test
    void createArticle_Success() {
        String name = "New Article";
        String description = "Description";
        int quantity = 10;
        BigDecimal price = new BigDecimal("19.99");
        List<Long> categoryIds = Arrays.asList(1L, 2L);
        List<Category> categories = Arrays.asList(new Category(1L), new Category(2L));
        Brand brand = new Brand(1L);

        when(brandPersistencePort.findById(1L)).thenReturn(brand);
        when(categoryPersistencePort.findByIds(categoryIds)).thenReturn(categories);
        when(articlePersistencePort.existsByName(name)).thenReturn(false);


        createArticleUseCase.executeWithIds(name, description, quantity, price, categoryIds, brand);

        verify(articlePersistencePort, times(1)).save(any(Article.class));
    }

    @Test
    void createArticle_CategoryNotFound() {

        String name = "New Article";
        String description = "Description";
        int quantity = 10;
        BigDecimal price = new BigDecimal("19.99");
        List<Long> categoryIds = Arrays.asList(1L, 2L);
        Brand brand = new Brand(1L);

        when(brandPersistencePort.findById(1L)).thenReturn(brand);

        when(categoryPersistencePort.findByIds(categoryIds)).thenReturn(Arrays.asList(new Category(1L)));

        DomainException exception = assertThrows(DomainException.class, () ->
                createArticleUseCase.executeWithIds(name, description, quantity, price, categoryIds, brand)
        );

        assertEquals(ErrorCode.CATEGORY_NOT_FOUND, exception.getErrorCode());
        verify(categoryPersistencePort, times(1)).findByIds(categoryIds);
        verify(articlePersistencePort, never()).existsByName(anyString());
        verify(articlePersistencePort, never()).save(any(Article.class));
    }

    @Test
    void createArticle_ArticleAlreadyExists() {
        String name = "Existing Article";
        String description = "Description";
        int quantity = 10;
        BigDecimal price = new BigDecimal("19.99");
        List<Long> categoryIds = Arrays.asList(1L, 2L);
        List<Category> categories = Arrays.asList(new Category(1L), new Category(2L));
        Brand brand = new Brand(1L);

        when(brandPersistencePort.findById(1L)).thenReturn(brand);

        when(categoryPersistencePort.findByIds(categoryIds)).thenReturn(categories);
        when(articlePersistencePort.existsByName(name)).thenReturn(true);

        DomainException exception = assertThrows(DomainException.class, () ->
                createArticleUseCase.executeWithIds(name, description, quantity, price, categoryIds, brand)
        );

        assertEquals(ErrorCode.ARTICLE_ALREADY_EXISTS, exception.getErrorCode());
        verify(categoryPersistencePort, times(1)).findByIds(categoryIds);
        verify(articlePersistencePort, times(1)).existsByName(name);
        verify(articlePersistencePort, never()).save(any(Article.class));
    }
}
