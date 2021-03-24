package com.github.hqxqyly.remex.crude.activiti.rsp.record;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("工作流流程流水dto")
public class ActivitiRecordProcessDto {
	
	@ApiModelProperty("流程实例ID")
	protected String id;
	
	@ApiModelProperty("业务key")
	protected String businessKey;
	
	@ApiModelProperty("流程定义ID")
	protected String processDefinitionId;
	
	@ApiModelProperty("流程定义名称")
	protected String processDefinitionName;
	
	@ApiModelProperty("流程定义key")
	protected String processDefinitionKey;
	
	@ApiModelProperty("流程定义版本")
	protected String processDefinitionVersion;
	
	@ApiModelProperty("部署ID")
	protected String deploymentId;
	
	@ApiModelProperty("任务开始的时间")
	protected String startTime;
	
	@ApiModelProperty("删除或完成任务的时间")
	protected String endTime;
	
	@ApiModelProperty("endTime - startTime")
	protected String durationInMillis;
	
	@ApiModelProperty("流程实例结束ID")
	protected String endActivityId;
	
	@ApiModelProperty("发起人")
	protected String startUserId;
	
	@ApiModelProperty("开始流程实例ID")
	protected String startActivityId;
	
	@ApiModelProperty("删除此任务的原因")
	protected String deleteReason;
	
	@ApiModelProperty("上级流程实例ID")
	protected String superProcessInstanceId;
	
	@ApiModelProperty("")
	protected String tenantId;
	
	@ApiModelProperty("流程实例名称")
	protected String name;
	
	@ApiModelProperty("流程实例描述")
	protected String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getProcessDefinitionName() {
		return processDefinitionName;
	}

	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public String getProcessDefinitionVersion() {
		return processDefinitionVersion;
	}

	public void setProcessDefinitionVersion(String processDefinitionVersion) {
		this.processDefinitionVersion = processDefinitionVersion;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDurationInMillis() {
		return durationInMillis;
	}

	public void setDurationInMillis(String durationInMillis) {
		this.durationInMillis = durationInMillis;
	}

	public String getEndActivityId() {
		return endActivityId;
	}

	public void setEndActivityId(String endActivityId) {
		this.endActivityId = endActivityId;
	}

	public String getStartUserId() {
		return startUserId;
	}

	public void setStartUserId(String startUserId) {
		this.startUserId = startUserId;
	}

	public String getStartActivityId() {
		return startActivityId;
	}

	public void setStartActivityId(String startActivityId) {
		this.startActivityId = startActivityId;
	}

	public String getDeleteReason() {
		return deleteReason;
	}

	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}

	public String getSuperProcessInstanceId() {
		return superProcessInstanceId;
	}

	public void setSuperProcessInstanceId(String superProcessInstanceId) {
		this.superProcessInstanceId = superProcessInstanceId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
