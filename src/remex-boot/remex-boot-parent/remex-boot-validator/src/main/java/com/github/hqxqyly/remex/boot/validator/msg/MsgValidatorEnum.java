package com.github.hqxqyly.remex.boot.validator.msg;

import com.github.hqxqyly.remex.boot.msg.IMsgEnum;

/**
 * 消息枚举 - 验证
 * 
 * @author Qiaoxin.Hong
 *
 */
public enum MsgValidatorEnum implements IMsgEnum {
	
	/** 验证失败 */
	VALIDATOR_FAILED("验证失败，{} {}"),
	
	/** 验证失败，不能为空 */
	VALIDATOR_FAILED_REQUIRED("验证失败，{} 不能为空"),

	;
	
	/** 消息编号 */
	private String code;
	
	/** 消息信息 */
	private String msg;
	
	private MsgValidatorEnum(String msg) {
		this.code = this.name();
		this.msg = msg;
	}
	
	private MsgValidatorEnum(String code, String msg) {
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
