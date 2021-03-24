package com.github.hqxqyly.remex.crude.activiti.req.task;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("查询工作流需处理的任务列表req")
public class ActivitiTaskHandleQueryReq {
	
	@NotBlank
	@ApiModelProperty("用户ID")
	protected String userId;
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
}
