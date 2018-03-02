package com.liqun.web;

import org.apache.shiro.authc.AccountException;

/**
 * 当前登录的用户与之前rememberMe的用户不一致。
 * @author Michelangelo
 *
 */
public class ConflictedAccountException extends AccountException {

	private static final long serialVersionUID = 1L;

	public ConflictedAccountException() {
		super();
	}

	public ConflictedAccountException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConflictedAccountException(String message) {
		super(message);
	}

	public ConflictedAccountException(Throwable cause) {
		super(cause);
	}
	

}
