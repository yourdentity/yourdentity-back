package com.yourdentity.yourdentity.server.store.dto.response;

import com.yourdentity.yourdentity.server.store.dto.OrderDto;
import com.yourdentity.yourdentity.server.store.entity.OrderStatus;
import lombok.Builder;

import java.util.List;

@Builder
public record OrderResponse(
        Long orderId,
        String orderNumber,
        Long totalPrice,
        Long totalAmount,
        Long totalDiscount,
        Long finalPrice,
        List<OrderDto> orders,
        OrderStatus orderStatus
) {

}
