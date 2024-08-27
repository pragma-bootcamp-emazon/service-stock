package com.emazon.stockservice.infrastructure.mappers.brand;

import com.emazon.stockservice.domain.models.Brand;
import com.emazon.stockservice.infrastructure.entities.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BrandEntityMapper {

    BrandEntity toEntity(Brand brand);

    Brand toDomain(BrandEntity brandEntity);

    List<Brand> toDomainList(List<BrandEntity> brandEntityList);

    List<BrandEntity> toEntityList(List<Brand> brandList);
}
