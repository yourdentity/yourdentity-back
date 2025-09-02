package com.yourdentity.global.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.yourdentity.global.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * 애플리케이션 전역의 예외 처리를 담당하는 핸들러
 * 발생하는 모든 예외를 적절한 형식의 응답으로 변환하여 클라이언트에게 반환
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 비즈니스 예외 처리
	 * BusinessBaseException과 그 하위 예외들을 처리하여 적절한 에러 응답을 생성
	 */
	@ExceptionHandler(BusinessBaseException.class)
	protected ResponseEntity<ApiResponse<?>> handleBusinessException(BusinessBaseException e) {
		log.error("Business exception occurred: {}", e.getMessage(), e);
		return ResponseEntity.status(e.getErrorCode().getStatus())
			.body(ApiResponse.onFailure(
				e.getErrorCode().getCode(),
				e.getErrorCode().getMessage(),
				null
			));
	}

	/**
	 * 입력값 검증 실패 예외 처리
	 * @Valid 어노테이션으로 검증 실패 시 발생하는 예외를 처리
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		List<ValidationError> validationErrors = ex.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(error -> ValidationError.of(
				error.getField(),
				error.getDefaultMessage()))
			.collect(Collectors.toList());

		log.warn("Validation failed: {}", validationErrors);
		return ResponseEntity.status(ErrorCode.INVALID_INPUT.getStatus())
			.body(ApiResponse.onFailure(
				ErrorCode.INVALID_INPUT.getCode(),
				ErrorCode.INVALID_INPUT.getMessage(),
				validationErrors
			));
	}

	/**
	 * 요청한 리소스를 찾을 수 없을 때 발생하는 예외 처리
	 */
	@ExceptionHandler(NoResourceFoundException.class)
	protected ResponseEntity<ApiResponse<?>> handleNoResourceFoundException(NoResourceFoundException e) {
		log.warn("Resource not found: {}", e.getMessage());
		return ResponseEntity.status(ErrorCode.NOT_FOUND.getStatus())
			.body(ApiResponse.onFailure(
				ErrorCode.NOT_FOUND.getCode(),
				ErrorCode.NOT_FOUND.getMessage(),
				null
			));
	}

	/**
	 * 지원하지 않는 HTTP 메서드 호출 시 발생하는 예외 처리
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<ApiResponse<?>> handleHttpRequestMethodNotSupported(
		HttpRequestMethodNotSupportedException e) {
		log.warn("Method not supported: {}", e.getMessage());
		return ResponseEntity.status(ErrorCode.BAD_REQUEST.getStatus())
			.body(ApiResponse.onFailure(
				ErrorCode.BAD_REQUEST.getCode(),
				ErrorCode.BAD_REQUEST.getMessage(),
				null
			));
	}

	/**
	 * HTTP 요청 메시지를 읽을 수 없을 때 발생하는 예외 처리
	 * 주로 잘못된 JSON 형식이나 타입 불일치로 인해 발생
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<ApiResponse<?>> handleHttpMessageNotReadable(
		HttpMessageNotReadableException e) {
		log.warn("Message not readable: {}", e.getMessage());
		return ResponseEntity.status(ErrorCode.INVALID_INPUT.getStatus())
			.body(ApiResponse.onFailure(
				ErrorCode.INVALID_INPUT.getCode(),
				ErrorCode.INVALID_INPUT.getMessage(),
				null
			));
	}

	/**
	 * 필수 요청 파라미터가 누락되었을 때 발생하는 예외 처리
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	protected ResponseEntity<ApiResponse<?>> handleMissingServletRequestParameter(
		MissingServletRequestParameterException e) {
		log.warn("Missing parameter: {}", e.getMessage());
		return ResponseEntity.status(ErrorCode.INVALID_INPUT.getStatus())
			.body(ApiResponse.onFailure(
				ErrorCode.INVALID_INPUT.getCode(),
				ErrorCode.INVALID_INPUT.getMessage(),
				null
			));
	}

	/**
	 * 위의 핸들러들에서 처리되지 않은 모든 예외를 처리하는 fallback 핸들러
	 * 예상치 못한 서버 오류를 처리
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ApiResponse<?>> handleException(Exception e) {
		log.error("Unhandled exception occurred", e);
		return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus())
			.body(ApiResponse.onFailure(
				ErrorCode.INTERNAL_SERVER_ERROR.getCode(),
				ErrorCode.INTERNAL_SERVER_ERROR.getMessage(),
				null
			));
	}

}
