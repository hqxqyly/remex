package com.github.hqxqyly.remex.crude.fileserver.api.msg;

import com.github.hqxqyly.remex.boot.msg.IMsgEnum;

/**
 * 消息枚举 - 文件服务器
 * 
 * @author Qiaoxin.Hong
 *
 */
public enum MsgFileServerEnum implements IMsgEnum {

	/** 文件枚举未找到 */
	CRUDE_FILE_ENUM_NOT_FOUND("文件枚举未找到"),
	
	;
	/** 消息编号 */
	private String code;
	
	/** 消息信息 */
	private String msg;
	
	private MsgFileServerEnum(String msg) {
		this.code = this.name();
		this.msg = msg;
	}
	
	private MsgFileServerEnum(String code, String msg) {
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
