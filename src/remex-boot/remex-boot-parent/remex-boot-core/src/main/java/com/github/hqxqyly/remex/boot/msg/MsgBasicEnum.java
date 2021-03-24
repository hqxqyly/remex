package com.github.hqxqyly.remex.boot.msg;

/**
 * 默认消息枚举
 * 
 * @author Qiaoxin.Hong
 *
 */
public enum MsgBasicEnum implements IMsgEnum {
	
	/** 操作成功 */
	SUCCESS("操作成功", IMsgEnum.STATUS_SUCCESS),
	
	/** 操作失败 */
	FAILED("操作失败"),
	
	/** 参数不能为空 */
	REQUIRED("参数不能为空"),
	
	/** 记录不存在 */
	DATA_NOT_EXISTS("记录不存在"),
	
	/** 记录已存在 */
	DATA_ALREADY_EXISTS("记录已存在"),
	
	/** 功能暂未开通 */
	FUNCTION_CANNOT_OPEN("功能暂未开通"),
	;
	
	/** 默认消息枚举 - 操作成功 */
	public static IMsgEnum DEFAULT_MSG_ENUM_SUCCESS = SUCCESS;
	
	/** 默认消息枚举 - 操作失败 */
	public static IMsgEnum DEFAULT_MSG_ENUM_FAILED = FAILED;
	
	/** 消息编号 */
	private String code;
	
	/** 消息信息 */
	private String msg;
	
	/** 消息状态码 */
	private Integer status;
	
	private MsgBasicEnum(String msg) {
		this.code = this.name();
		this.msg = msg;
	}
	
	private MsgBasicEnum(String msg, Integer status) {
		this(msg);
		this.status = status;
	}
	
	private MsgBasicEnum(String code, String msg) {
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
