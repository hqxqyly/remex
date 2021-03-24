package com.github.hqxqyly.remex.boot.exception;

import com.github.hqxqyly.remex.boot.msg.IMsgEnum;
import com.github.hqxqyly.remex.boot.msg.MsgBasicEnum;
import com.github.hqxqyly.remex.boot.utils.MsgEnumUtils;

/**
 * remex服务异常类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RemexServerException extends RemexException {
	private static final long serialVersionUID = 1L;

	/** 消息枚举 */
	protected IMsgEnum msgEnum = MsgBasicEnum.DEFAULT_MSG_ENUM_FAILED;
	
	/** 消息参数 */
	protected Object[] msgArgs;
	
	public RemexServerException() {
		super();
	}

	public RemexServerException(String message, Object... args) {
		super(message, args);
	}

	public RemexServerException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RemexServerException(String message, Throwable cause, Object... args) {
		super(message, cause, args);
	}

	public RemexServerException(String message, Throwable cause) {
		super(message, cause);
	}

	public RemexServerException(String message) {
		super(message);
	}

	public RemexServerException(Throwable cause) {
		super(cause);
	}
	
	public RemexServerException(IMsgEnum msgEnum, Object...msgArgs) {
		super(resolvePlaceholders(msgEnum, msgArgs));
		this.msgEnum = msgEnum;
		this.msgArgs = msgArgs;
	}
	
	public RemexServerException(IMsgEnum msgEnum, Throwable cause, Object...msgArgs) {
		super(resolvePlaceholders(msgEnum, msgArgs), cause);
		this.msgEnum = msgEnum;
		this.msgArgs = msgArgs;
	}
	
	

	/**
	 * 消息占位符替换
	 */
	protected static String resolvePlaceholders(IMsgEnum msgEnum, Object...msgArgs) {
		return MsgEnumUtils.resolveToLog(msgEnum, msgArgs);
	}
	
	
	
	public IMsgEnum getMsgEnum() {
		return msgEnum;
	}

	public void setMsgEnum(IMsgEnum msgEnum) {
		this.msgEnum = msgEnum;
	}

	public Object[] getMsgArgs() {
		return msgArgs;
	}

	public void setMsgArgs(Object[] msgArgs) {
		this.msgArgs = msgArgs;
	}
}
