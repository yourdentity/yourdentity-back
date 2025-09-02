package com.yourdentity.global.exception.auth;

import com.yourdentity.global.exception.BusinessBaseException;
import com.yourdentity.global.exception.ErrorCode;

public class UserAlreadyExistsException extends BusinessBaseException {

	public UserAlreadyExistsException() {
		super(ErrorCode.USER_ALREADY_EXISTS);
	}

}
