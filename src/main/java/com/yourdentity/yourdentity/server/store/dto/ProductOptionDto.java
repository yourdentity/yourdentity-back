package com.yourdentity.yourdentity.server.store.dto;

public record ProductOptionDto(
    String optionName, //색상, 사이즈
    String optionValue, // 퓨어, 레드, 그린
    String optionImageUrl,
    Long stock
) {
}
