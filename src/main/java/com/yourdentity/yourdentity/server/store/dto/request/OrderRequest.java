package com.yourdentity.yourdentity.server.store.dto.request;

import java.util.List;

public record OrderRequest(
        String userName,
        String userEmail,
        String phoneNumber,
        String address,
        String couponCode,
        List<OrderItemRequest> items
) {
}
