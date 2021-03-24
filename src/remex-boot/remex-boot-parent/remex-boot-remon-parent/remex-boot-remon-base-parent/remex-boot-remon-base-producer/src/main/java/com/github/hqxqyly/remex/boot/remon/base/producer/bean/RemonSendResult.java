package com.github.hqxqyly.remex.boot.remon.base.producer.bean;

import java.io.Serializable;

/**
 * 消息中间件发送结果集
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RemonSendResult implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 发送结果 */
	protected boolean result = true;
	
	/** 结果消息 */
	protected String msg;
	
	public RemonSendResult() {
		super();
	}

	public RemonSendResult(boolean result) {
		super();
		this.result = result;
	}

	public RemonSendResult(boolean result, String msg) {
		super();
		this.result = result;
		this.msg = msg;
	}
	
	public RemonSendResult(String msg) {
		super();
		this.result = false;
		this.msg = msg;
	}

	/**
	 * 创建失败结果集
	 * @param msg
	 */
	public static RemonSendResult createError(String msg) {
		return new RemonSendResult(false, msg);
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
