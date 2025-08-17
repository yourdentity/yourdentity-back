package com.yourdentity.yourdentity.server.store.dto.request;

public record ExchangeItemRequest(
        Long productId,
        Long optionId,
        Long quantity
) {
}
