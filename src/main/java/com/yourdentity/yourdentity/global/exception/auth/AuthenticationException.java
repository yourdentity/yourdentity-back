package com.yourdentity.yourdentity.global.exception.auth;

import com.yourdentity.yourdentity.global.exception.BusinessBaseException;
import com.yourdentity.yourdentity.global.exception.ErrorCode;

public class AuthenticationException extends BusinessBaseException {

	public AuthenticationException(ErrorCode errorCode) {
		super(errorCode);
	}

}
