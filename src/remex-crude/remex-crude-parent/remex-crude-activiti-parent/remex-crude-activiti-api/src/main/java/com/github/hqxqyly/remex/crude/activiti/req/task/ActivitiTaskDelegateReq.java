package com.github.hqxqyly.remex.crude.activiti.req.task;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("工作流任务委托req")
public class ActivitiTaskDelegateReq {

	@NotBlank
	@ApiModelProperty("任务ID")
	protected String taskId;
	
	@NotBlank
	@ApiModelProperty("原用户ID")
	protected String oldUserId;
	
	@NotBlank
	@ApiModelProperty("新用户ID")
	protected String newUserId;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getOldUserId() {
		return oldUserId;
	}

	public void setOldUserId(String oldUserId) {
		this.oldUserId = oldUserId;
	}

	public String getNewUserId() {
		return newUserId;
	}

	public void setNewUserId(String newUserId) {
		this.newUserId = newUserId;
	}
}
