package com.yourdentity.global.exception.auth;

import com.yourdentity.global.exception.BusinessBaseException;
import com.yourdentity.global.exception.ErrorCode;

public class AuthenticationException extends BusinessBaseException {

	public AuthenticationException(ErrorCode errorCode) {
		super(errorCode);
	}

}
