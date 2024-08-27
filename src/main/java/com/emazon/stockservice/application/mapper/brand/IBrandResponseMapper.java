package com.emazon.stockservice.application.mapper.brand;

import com.emazon.stockservice.application.dto.brand.BrandResponse;
import com.emazon.stockservice.domain.models.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBrandResponseMapper {
    BrandResponse toBrandResponse(Brand brand);
}
