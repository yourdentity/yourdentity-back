package com.yourdentity.yourdentity.server.store.mapper;


import com.yourdentity.yourdentity.server.store.dto.response.ProductListResponse;
import com.yourdentity.yourdentity.server.store.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductListMapper {
    @Mapping(target = "productId", source = "id")
    @Mapping(target = "viewCount", source = "stats.viewCount")
    @Mapping(target = "applyCount", source = "stats.applyCount")
    @Mapping(target = "tagName", source = "tag.tagName")
    ProductListResponse toListResponse(Product product);

    List<ProductListResponse> toListResponses(List<Product> products);
}
