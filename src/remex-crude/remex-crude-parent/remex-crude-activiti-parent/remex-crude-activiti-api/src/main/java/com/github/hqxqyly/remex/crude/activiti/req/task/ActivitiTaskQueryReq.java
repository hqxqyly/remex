package com.github.hqxqyly.remex.crude.activiti.req.task;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("查询工作流任务列表req")
public class ActivitiTaskQueryReq {

	@ApiModelProperty("处理人ID")
	protected String candidateUserId;
	
	@ApiModelProperty("受理人ID")
	protected String assigneeUserId;
	
	@ApiModelProperty("受理人或处理人ID")
	protected String userId;
	
	public void setCandidateUserId(String candidateUserId) {
		this.candidateUserId = candidateUserId;
	}
	
	public String getCandidateUserId() {
		return candidateUserId;
	}
	
	public void setAssigneeUserId(String assigneeUserId) {
		this.assigneeUserId = assigneeUserId;
	}
	
	public String getAssigneeUserId() {
		return assigneeUserId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
}
