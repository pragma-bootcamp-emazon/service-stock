package com.emazon.stockservice.application.mapper;

import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.application.dto.CategoryResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapperApplication {

    CategoryResponse toCategoryResponse(Category category);

    List<CategoryResponse> toCategoryResponseList(List<Category> categories);
}
