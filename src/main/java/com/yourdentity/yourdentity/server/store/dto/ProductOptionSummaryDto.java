package com.yourdentity.yourdentity.server.store.dto;

public record ProductOptionSummaryDto(
        Long optionId,
        String optionName, //색상, 사이즈
        String optionValue, // 퓨어, 레드, 그린
        String optionImageUrl
) {
}
