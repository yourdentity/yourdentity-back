package com.yourdentity.global.exception.auth;

import com.yourdentity.global.exception.BusinessBaseException;
import com.yourdentity.global.exception.ErrorCode;

public class AuthUserNotFoundException extends BusinessBaseException {

	public AuthUserNotFoundException() {
		super(ErrorCode.USER_NOT_FOUND);
	}

}
