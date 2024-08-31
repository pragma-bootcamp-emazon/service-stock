package com.emazon.stockservice.infrastructure.output.jpa.mappers.brand;

import com.emazon.stockservice.domain.models.Brand;
import com.emazon.stockservice.infrastructure.output.jpa.entities.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BrandEntityMapper {

    BrandEntity toEntity(Brand brand);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    public default Brand toDomain(BrandEntity brandEntity){
        return new Brand(brandEntity.getId(), brandEntity.getName(), brandEntity.getDescription(), brandEntity.getCreatedAt(), brandEntity.getUpdatedAt());
    }


    List<Brand> toDomainList(List<BrandEntity> brandEntityList);

    List<BrandEntity> toEntityList(List<Brand> brandList);
}
