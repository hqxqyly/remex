package com.github.hqxqyly.remex.crude.activiti.req.record;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("根据任务ID查询工作流附件流水列表req")
public class ActivitiRecordAttachmentByTaskQueryReq {

	@ApiModelProperty("任务ID")
	protected String taskId;
	
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	public String getTaskId() {
		return taskId;
	}
}
