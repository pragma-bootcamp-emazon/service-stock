package com.emazon.stockservice.infrastructure.web.output;

import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.application.dto.category.CategoryResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {

    CategoryResponse toCategoryResponse(Category category);

    List<CategoryResponse> toCategoryResponseList(List<Category> categories);
}
