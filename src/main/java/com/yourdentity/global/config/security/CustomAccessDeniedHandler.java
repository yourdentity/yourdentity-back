package com.yourdentity.global.config.security;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.yourdentity.global.exception.ErrorCode;
import com.yourdentity.global.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 접근 거부(403) 예외 발생 시 처리를 담당하는 핸들러
 * 권한이 없는 리소스에 접근 시도 시 JSON 형식의 에러 응답을 반환
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 접근 거부 예외 발생 시 클라이언트에게 JSON 형식의 에러 응답을 반환
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException {

		ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.FORBIDDEN);

		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");

		objectMapper.writeValue(response.getWriter(), errorResponse);
	}

}
