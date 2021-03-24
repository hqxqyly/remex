package com.github.hqxqyly.remex.crude.activiti.msg;

import com.github.hqxqyly.remex.boot.msg.IMsgEnum;

/**
 * 消息枚举 - 工作流
 * 
 * @author Qiaoxin.Hong
 *
 */
public enum MsgCrudeActivitiEnum implements IMsgEnum {

	/** 用户组不存在 */
	ACTIVITI_GROUP_NOT_EXISTS("用户组不存在"),
	
	/** 用户不存在 */
	ACTIVITI_USER_NOT_EXISTS("用户不存在"),

	/** 工作流流程定义不存在 */
	ACTIVITI_PROCESS_DEFINITION_NOT_EXISTS("工作流流程定义不存在"),

	/** 工作流流程定义不存在或无权限操作 */
	ACTIVITI_PROCESS_DEFINITION_NOT_EXISTS_UNAUTH("工作流流程定义不存在或无权限操作"),

	/** 工作流流程定义已中止 */
	ACTIVITI_PROCESS_DEFINITION_SUSPENDED("工作流流程定义已中止"),

	/** 工作流任务不存在 */
	ACTIVITI_TASK_NOT_EXISTS("工作流任务不存在"),

	/** 工作流任务已被人认领 */
	ACTIVITI_TASK_ALREADY_CLAIMED("工作流任务已被人认领"),

	/** 工作流任务未被认领 */
	ACTIVITI_TASK_UN_CLAIMED("工作流任务未被认领"),

	/** 工作流任务受理人未匹配上，无法进行操作 */
	ACTIVITI_TASK_ASSIGNEE_NOT_MATCH("工作流任务受理人未匹配上，无法进行操作"),

	/** 工作流任务候选处理人为空 */
	ACTIVITI_TASK_CANDIDATE_EMPTY("工作流任务候选处理人为空"),

	/** 工作流任务无权限操作 */
	ACTIVITI_TASK_CANDIDATE_NOT_MATCH("工作流任务无权限操作"),
	
	/** 工作流任务委托时用户不能相同 */
	ACTIVITI_TASK_DELEGATE_USER_NOT_SAME("工作流任务委托时用户不能相同"),
	;
	
	/** 消息编号 */
	private String code;
	
	/** 消息信息 */
	private String msg;
	
	private MsgCrudeActivitiEnum(String msg) {
		this.code = this.name();
		this.msg = msg;
	}
	
	private MsgCrudeActivitiEnum(String code, String msg) {
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
