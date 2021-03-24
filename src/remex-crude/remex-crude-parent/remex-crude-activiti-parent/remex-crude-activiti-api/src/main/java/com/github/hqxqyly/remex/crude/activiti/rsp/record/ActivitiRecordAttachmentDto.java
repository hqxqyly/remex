package com.github.hqxqyly.remex.crude.activiti.rsp.record;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("工作流附件流水dto")
public class ActivitiRecordAttachmentDto {

	@ApiModelProperty("ID")
	protected String id;
	
	@ApiModelProperty("名称")
	protected String name;
	
	@ApiModelProperty("描述")
	protected String description;
	
	@ApiModelProperty("类型")
	protected String type;
	
	@ApiModelProperty("任务ID")
	protected String taskId;
	
	@ApiModelProperty("流程实例ID")
	protected String processInstanceId;
	
	@ApiModelProperty("url")
	protected String url;
	
	@ApiModelProperty("用户ID")
	protected String userId;
	
	@ApiModelProperty("创建时间")
	protected String time;
	
	@ApiModelProperty("存储内容的字节数组实体的ID")
	protected String contentId;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
}
