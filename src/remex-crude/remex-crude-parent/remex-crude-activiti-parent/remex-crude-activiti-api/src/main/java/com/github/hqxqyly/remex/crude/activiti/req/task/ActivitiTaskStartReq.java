package com.github.hqxqyly.remex.crude.activiti.req.task;

import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("工作流任务启动req")
public class ActivitiTaskStartReq {
	
	@NotBlank
	@ApiModelProperty("用户ID")
	protected String userId;

	@NotBlank
	@ApiModelProperty("工作流定义ID")
	protected String processDefinitionId;
	
	@ApiModelProperty("附件列表")
	protected List<ActivitiTaskAttachmentCreateReq> attachmentList;
	
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setAttachmentList(List<ActivitiTaskAttachmentCreateReq> attachmentList) {
		this.attachmentList = attachmentList;
	}
	
	public List<ActivitiTaskAttachmentCreateReq> getAttachmentList() {
		return attachmentList;
	}
}
