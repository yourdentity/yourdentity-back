package com.yourdentity.global.common.enums;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode{

    // 공통 응답
    _OK(HttpStatus.OK, "COMMON_200", "성공입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}