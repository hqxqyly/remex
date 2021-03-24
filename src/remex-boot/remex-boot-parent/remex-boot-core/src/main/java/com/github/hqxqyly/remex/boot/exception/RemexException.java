package com.github.hqxqyly.remex.boot.exception;

import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * remex运行异常类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RemexException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RemexException() {
		super();
	}

	public RemexException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RemexException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public RemexException(String message, Throwable cause, Object...args) {
		super(Assist.replaceBrace(message, args), cause);
	}

	public RemexException(String message) {
		super(message);
	}
	
	public RemexException(String message, Object...args) {
		super(Assist.replaceBrace(message, args));
	}

	public RemexException(Throwable cause) {
		super(cause);
	}
}
