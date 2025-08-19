package com.yourdentity.yourdentity.server.store.dto.request;

public record OrderItemRequest(
        Long productId,
        Long optionId,
        Long quantity
) {
}
