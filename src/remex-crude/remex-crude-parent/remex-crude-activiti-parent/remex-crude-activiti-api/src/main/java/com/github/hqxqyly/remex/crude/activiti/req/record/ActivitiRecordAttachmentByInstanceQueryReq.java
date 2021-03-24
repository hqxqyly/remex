package com.github.hqxqyly.remex.crude.activiti.req.record;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("根据流程实例ID查询工作流附件流水列表req")
public class ActivitiRecordAttachmentByInstanceQueryReq {

	@ApiModelProperty("流程实例ID")
	protected String instanceId;
	
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	
	public String getInstanceId() {
		return instanceId;
	}
}
