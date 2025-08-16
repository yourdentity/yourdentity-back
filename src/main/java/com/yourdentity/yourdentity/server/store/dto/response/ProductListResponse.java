package com.yourdentity.yourdentity.server.store.dto.response;


import lombok.Builder;

@Builder
public record ProductListResponse(
        Long productId,
        String productName,
        String imageUrl,
        Long price,
        Long viewCount,
        Long applyCount,
        String tagName
)
{
}
