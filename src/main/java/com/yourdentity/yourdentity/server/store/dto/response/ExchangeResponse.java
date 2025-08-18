package com.yourdentity.yourdentity.server.store.dto.response;

import com.yourdentity.yourdentity.server.store.entity.Exchange;
import com.yourdentity.yourdentity.server.store.entity.ExchangeStatus;
import lombok.Builder;

@Builder
public record ExchangeResponse(
        Long exchangeId,
        Long productId,
        Long optionId,
        Long quantity,
        ExchangeStatus exchangeStatus
) {
    public static ExchangeResponse from(Exchange exchange) {
        return ExchangeResponse.builder()
                .exchangeId(exchange.getId())
                .productId(exchange.getProduct().getId())
                .quantity(exchange.getQuantity())
                .optionId(exchange.getOption().getId())
                .exchangeStatus(exchange.getStatus())
                .build();
    }

}
