package com.github.hqxqyly.remex.crude.activiti.req.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("工作流任务完成req")
public class ActivitiTaskCompleteReq {

	@NotBlank
	@ApiModelProperty("用户ID")
	protected String userId;
	
	@NotBlank
	@ApiModelProperty("任务ID")
	protected String taskId;
	
	@ApiModelProperty("流程ID，有多个流程时，指定下一个流程")
	protected String flowId;
	
	@ApiModelProperty("流程名称，有多个流程时，指定下一个流程")
	protected String flowName;
	
	@ApiModelProperty("变量参数")
	protected Map<String, Object> variableMap = new HashMap<>();
	
	@ApiModelProperty("附件列表")
	protected List<ActivitiTaskAttachmentCreateReq> attachmentList;
	
	public ActivitiTaskCompleteReq() {
		super();
	}

	public ActivitiTaskCompleteReq(String userId, String taskId) {
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

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public Map<String, Object> getVariableMap() {
		return variableMap;
	}

	public void setVariableMap(Map<String, Object> variableMap) {
		this.variableMap = variableMap;
	}
	
	public void setAttachmentList(List<ActivitiTaskAttachmentCreateReq> attachmentList) {
		this.attachmentList = attachmentList;
	}
	
	public List<ActivitiTaskAttachmentCreateReq> getAttachmentList() {
		return attachmentList;
	}
}
