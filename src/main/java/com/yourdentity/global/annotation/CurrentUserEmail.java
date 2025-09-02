package com.yourdentity.global.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// TODO: 카카오클라우드 인증 방식 결정 후 적절한 어노테이션으로 변경
// import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
// @AuthenticationPrincipal(expression = "claims['email']") // 임시 주석 처리
public @interface CurrentUserEmail {

}
