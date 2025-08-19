package com.yourdentity.yourdentity.server.store.dto;

public record OrderDto(
        String companyName,
        Long productId,
        String productName,
        ProductOptionSummaryDto productOptionSummaryDto,
        Long price,
        Long quantity


) {
}
