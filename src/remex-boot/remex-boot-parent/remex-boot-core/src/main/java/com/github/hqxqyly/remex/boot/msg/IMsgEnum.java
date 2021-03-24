package com.github.hqxqyly.remex.boot.msg;

/**
 * 消息枚举统一规范
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IMsgEnum {
	
	/** 消息状态码 - 成功 */
	public final static int STATUS_SUCCESS = 200;
	
	/** 消息状态码 - 失败 */
	public final static int STATUS_FAILED = 500;
	
	/**
	 * 取得消息编号
	 * @return
	 */
	public String getCode();
	
	/**
	 * 取得消息信息
	 * @return
	 */
	public String getMsg();
	
	/**
	 * 取得消息状态码
	 * @return
	 */
	public default Integer getStatus() {
		return STATUS_FAILED;
	}
}
