package com.github.hqxqyly.remex.boot.auth.msg;

import com.github.hqxqyly.remex.boot.msg.IMsgEnum;

/**
 * 消息枚举 - 权限
 * 
 * @author Qiaoxin.Hong
 *
 */
public enum MsgAuthEnum implements IMsgEnum {

	/** 未登录 */
	UNAUTH("请先登录"),
	
	/** 无权限访问此功能 */
	UNPERMIT("无权限访问此功能"),
	
	/** 用户不存在或获取失败 */
	LOGIN_ACQUISITION_ACCOUNT_ERROR("用户不存在或获取失败"),
	
	/** 用户名或密码错误 */
	LOGIN_PASSWORD_ERROR("用户名或密码错误"),
	
	/** 登录异常 */
	LOGIN_ERROR("登录异常"),
	
	;

	/** 消息编号 */
	private String code;
	
	/** 消息信息 */
	private String msg;
	
	private MsgAuthEnum(String msg) {
		this.code = this.name();
		this.msg = msg;
	}
	
	private MsgAuthEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
