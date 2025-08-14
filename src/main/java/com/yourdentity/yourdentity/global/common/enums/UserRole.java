package com.yourdentity.yourdentity.global.common.enums;

import lombok.Getter;

/**
 * 사용자 역할 Enum
 */
@Getter
public enum UserRole {
    USER("일반 사용자"),
    ADMIN("관리자");

    private final String description;

    UserRole(String description) {
        this.description = description;
    }
}
