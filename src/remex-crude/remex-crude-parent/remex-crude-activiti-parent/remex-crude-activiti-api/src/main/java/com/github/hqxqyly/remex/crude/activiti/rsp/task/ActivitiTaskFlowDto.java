package com.github.hqxqyly.remex.crude.activiti.rsp.task;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("工作流任务流程dto")
public class ActivitiTaskFlowDto {

	@ApiModelProperty("流程ID")
	protected String flowId;
	
	@ApiModelProperty("流程名称")
	protected String flowName;

	public ActivitiTaskFlowDto() {
		super();
	}

	public ActivitiTaskFlowDto(String flowId, String flowName) {
		super();
		this.flowId = flowId;
		this.flowName = flowName;
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
}
