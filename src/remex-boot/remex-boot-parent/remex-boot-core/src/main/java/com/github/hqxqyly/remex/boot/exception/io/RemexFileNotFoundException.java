package com.github.hqxqyly.remex.boot.exception.io;

import com.github.hqxqyly.remex.boot.exception.RemexServerException;
import com.github.hqxqyly.remex.boot.msg.IMsgEnum;

/**
 * 文件未找到异常
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RemexFileNotFoundException extends RemexServerException {
	private static final long serialVersionUID = 1L;

	public RemexFileNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RemexFileNotFoundException(IMsgEnum msgEnum, Object... msgArgs) {
		super(msgEnum, msgArgs);
		// TODO Auto-generated constructor stub
	}

	public RemexFileNotFoundException(IMsgEnum msgEnum, Throwable cause, Object... msgArgs) {
		super(msgEnum, cause, msgArgs);
		// TODO Auto-generated constructor stub
	}

	public RemexFileNotFoundException(String message, Object... args) {
		super(message, args);
		// TODO Auto-generated constructor stub
	}

	public RemexFileNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public RemexFileNotFoundException(String message, Throwable cause, Object... args) {
		super(message, cause, args);
		// TODO Auto-generated constructor stub
	}

	public RemexFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RemexFileNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RemexFileNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
