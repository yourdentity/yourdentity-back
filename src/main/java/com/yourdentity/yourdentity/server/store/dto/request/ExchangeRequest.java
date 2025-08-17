package com.yourdentity.yourdentity.server.store.dto.request;

import java.util.List;

public record ExchangeRequest(
        List<ExchangeItemRequest> items
)
{
}
