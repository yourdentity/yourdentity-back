package com.yourdentity.yourdentity.server.store.mapper;


import com.yourdentity.yourdentity.server.store.dto.ProductOptionDto;
import com.yourdentity.yourdentity.server.store.dto.response.ProductListResponse;
import com.yourdentity.yourdentity.server.store.dto.response.ProductResponse;
import com.yourdentity.yourdentity.server.store.entity.Product;
import com.yourdentity.yourdentity.server.store.entity.ProductOption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductDetailMapper
{
    @Mapping(target = "productId", source = "id")
    @Mapping(target = "tagName", source = "tag.tagName")
    @Mapping(target = "viewCount", source = "stats.viewCount")
    @Mapping(target = "applyCount", source = "stats.applyCount")
    @Mapping(target = "detailedTitle", source = "detail.title")
    @Mapping(target = "detailedContent", source = "detail.content")
    @Mapping(target = "productOptionDto", source = "options") // List 매핑
    ProductResponse toResponse(Product product);

    @Mapping(target = "optionName", source = "optionType.name")
    @Mapping(target = "optionValue", source = "value")
    @Mapping(target = "optionImageUrl", source = "imageUrl")
    @Mapping(target = "stock", source = "stock")
    ProductOptionDto toOptionDto(ProductOption productOption);

    List<ProductOptionDto> toOptionDtos(List<ProductOption> options);

}
