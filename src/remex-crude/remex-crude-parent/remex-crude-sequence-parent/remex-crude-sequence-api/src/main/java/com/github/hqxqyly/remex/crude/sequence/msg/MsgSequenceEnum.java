package com.github.hqxqyly.remex.crude.sequence.msg;

import com.github.hqxqyly.remex.boot.msg.IMsgEnum;

/**
 * 消息枚举 - 序列
 * 
 * @author Qiaoxin.Hong
 *
 */
public enum MsgSequenceEnum implements IMsgEnum {

	/** 序列不存在 */
	SEQUENCE_NOT_EXISTS("序列不存在"),
	
	;
	
	/** 消息编号 */
	private String code;
	
	/** 消息信息 */
	private String msg;
	
	MsgSequenceEnum(String msg) {
		this.code = this.name();
		this.msg = msg;
	}
	
	MsgSequenceEnum(String code, String msg) {
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
