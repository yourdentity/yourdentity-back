package com.yourdentity.yourdentity.global.exception.auth;

import com.yourdentity.yourdentity.global.exception.BusinessBaseException;
import com.yourdentity.yourdentity.global.exception.ErrorCode;

public class AuthUserNotFoundException extends BusinessBaseException {

	public AuthUserNotFoundException() {
		super(ErrorCode.USER_NOT_FOUND);
	}

}
