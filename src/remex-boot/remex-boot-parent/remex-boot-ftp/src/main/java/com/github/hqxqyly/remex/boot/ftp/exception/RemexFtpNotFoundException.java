package com.github.hqxqyly.remex.boot.ftp.exception;

import com.github.hqxqyly.remex.boot.msg.IMsgEnum;

/**
 * ftp异常 - 文件未找到
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RemexFtpNotFoundException extends RemexFtpException {
	private static final long serialVersionUID = 1L;

	public RemexFtpNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RemexFtpNotFoundException(IMsgEnum msgEnum, Object... msgArgs) {
		super(msgEnum, msgArgs);
		// TODO Auto-generated constructor stub
	}

	public RemexFtpNotFoundException(IMsgEnum msgEnum, Throwable cause, Object... msgArgs) {
		super(msgEnum, cause, msgArgs);
		// TODO Auto-generated constructor stub
	}

	public RemexFtpNotFoundException(String message, Object... args) {
		super(message, args);
		// TODO Auto-generated constructor stub
	}

	public RemexFtpNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public RemexFtpNotFoundException(String message, Throwable cause, Object... args) {
		super(message, cause, args);
		// TODO Auto-generated constructor stub
	}

	public RemexFtpNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RemexFtpNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RemexFtpNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
