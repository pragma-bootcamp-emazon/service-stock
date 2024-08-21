package com.emazon.stockservice.infrastructure.web.output.jpa.mapper;

import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.infrastructure.web.dto.CategoryResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {

    CategoryResponse toCategoryResponse(Category category);

    List<CategoryResponse> toCategoryResponseList(List<Category> categories);
}
