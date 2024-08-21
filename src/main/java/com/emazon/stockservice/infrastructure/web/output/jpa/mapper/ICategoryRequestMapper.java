package com.emazon.stockservice.infrastructure.web.output.jpa.mapper;

import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.infrastructure.web.dto.CategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICategoryRequestMapper {

    @Mapping(target = "id", ignore = true)
    Category toCategory(CategoryRequest categoryRequest);
}
