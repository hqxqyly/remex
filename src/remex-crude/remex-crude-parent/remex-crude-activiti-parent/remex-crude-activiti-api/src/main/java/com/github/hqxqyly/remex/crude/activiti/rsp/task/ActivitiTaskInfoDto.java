package com.github.hqxqyly.remex.crude.activiti.rsp.task;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("工作流任务信息dto")
public class ActivitiTaskInfoDto {

	@ApiModelProperty("任务ID")
	protected String id;
	
	@ApiModelProperty("任务名称")
	protected String name;
	
	@ApiModelProperty("任务描述")
	protected String description;
	
	@ApiModelProperty("任务优先级")
	protected Integer priority;
	
	@ApiModelProperty("委托人")
	protected String owner;
	
	@ApiModelProperty("受理人")
	protected String assignee;
	
	@ApiModelProperty("流程实例ID")
	protected String processInstanceId;
	
	@ApiModelProperty("执行流程ID")
	protected String executionId;
	
	@ApiModelProperty("流程定义ID")
	protected String processDefinitionId;
	
	@ApiModelProperty("创建时间")
	protected Timestamp createTime;
	
	@ApiModelProperty("任务的流程中活动的id")
	protected String taskDefinitionKey;
	
	@ApiModelProperty("截止日期")
	protected Timestamp dueDate;
	
	@ApiModelProperty("任务的类别")
	protected String category;
	
	@ApiModelProperty("父ID")
	protected String parentTaskId;
	
	@ApiModelProperty("")
	protected String tenantId;
	
	@ApiModelProperty("")
	protected String formKey;
	
	@ApiModelProperty("请求任务的时间")
	protected Timestamp claimTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	public Timestamp getDueDate() {
		return dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getFormKey() {
		return formKey;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	public Timestamp getClaimTime() {
		return claimTime;
	}

	public void setClaimTime(Timestamp claimTime) {
		this.claimTime = claimTime;
	}
}
