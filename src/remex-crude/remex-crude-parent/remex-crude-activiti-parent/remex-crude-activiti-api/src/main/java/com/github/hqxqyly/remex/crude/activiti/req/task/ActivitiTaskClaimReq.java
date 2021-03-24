package com.github.hqxqyly.remex.crude.activiti.req.task;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("工作流任务认领req")
public class ActivitiTaskClaimReq {

	@NotBlank
	@ApiModelProperty("用户ID")
	protected String userId;
	
	@NotBlank
	@ApiModelProperty("任务ID")
	protected String taskId;
	
	public ActivitiTaskClaimReq() {
		super();
	}

	public ActivitiTaskClaimReq(String userId, String taskId) {
		super();
		this.userId = userId;
		this.taskId = taskId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	public String getTaskId() {
		return taskId;
	}
}
