package com.yourdentity.yourdentity.server.store.dto;

import java.util.List;

public record ProductReviewDto(
        Long reviewId,
        Long userId,
        Long rating,
        String content,
        List<String> imageUrls,
        List<String> fileUrls
) {
}
