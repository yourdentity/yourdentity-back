package com.yourdentity.yourdentity.server.store.dto;

import java.util.List;

public record ProductQuestionDto(
        Long questionId,
        Long userId,
        String content,
        String answer,
        List<String> imageUrls,
        List<String> fileUrls
) {
}
