package com.github.hqxqyly.remex.crude.org.msg;

import com.github.hqxqyly.remex.boot.msg.IMsgEnum;

/**
 * 消息枚举 - 公共消息
 * 
 * @author Qiaoxin.Hong
 *
 */
public enum MsgCrudeOrgEnum implements IMsgEnum {
	
	/** 用户不存在 */
	CRUDE_ORG_USER_NOT_EXIST("用户不存在"),
	
	/** 用户名已存在 */
	CRUDE_ORG_USERNAME_ALREADY_EXIST("用户名已存在"),
	
	/** 员工编号已存在 */
	CRUDE_ORG_USER_NO_ALREADY_EXIST("员工编号已存在"),
	
	/** 存在用户，无法删除角色 */
	CRUDE_ORG_USER_EXIST_FOR_DEL_ROLE("存在用户，无法删除角色"),
	
	/** 权限编号已存在 */
	CRUDE_ORG_AUTH_CODE_ALREADY_EXIST("权限编号已存在"),
	
	/** 权限不存在 */
	CRUDE_ORG_AUTH_NOT_EXIST("权限不存在"),
	
	/** 存在子权限，无法删除权限 */
	CRUDE_ORG_AUTH_CHILD_EXIST_FOR_DEL("存在子权限，无法删除权限"),
	
	/** 请输入操作码 */
	CRUDE_ORG_OPW_NOT_EXIST("請輸入操作碼"),

	/** 操作码错误 */
	CRUDE_ORG_OPW_ERROR("操作碼錯誤"),

	/** 请输入二次操作码 */
	CRUDE_ORG_SECOND_OPW_NOT_EXIST("請輸入二次操作碼"),

	/** 请输入二次经手人 */
	CRUDE_ORG_SECOND_OPW_USERNAME_NOT_EXIST("請輸入二次經手人"),

	/** 二次操作码错误 */
	CRUDE_ORG_SECOND_OPW_ERROR("二次操作碼錯誤"),

	/** 无二次操作权限 */
	CRUDE_ORG_SECOND_UNPERMIT("無二次操作權限"), 
	
	/** 二次經手人不能與當前用戶相同 */
	CRUDE_ORG_SECOND_OPW_USERNAME_NOT_SAME("二次經手人不能與當前用戶相同"),
	
	/** 存在角色，无法删除部门 */
	CRUDE_ORG_ROLE_EXIST_FOR_DEL_DEPARTMENT("存在角色，无法删除部门"),
	;
	
	/** 消息编号 */
	private String code;
	
	/** 消息信息 */
	private String msg;
	
	private MsgCrudeOrgEnum(String msg) {
		this.code = this.name();
		this.msg = msg;
	}
	
	private MsgCrudeOrgEnum(String code, String msg) {
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
