package com.github.hqxqyly.remex.crude.activiti.rsp.task;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("工作流任务dto")
public class ActivitiTaskDto extends ActivitiTaskInfoDto {
	
	@ApiModelProperty("流程列表")
	protected List<ActivitiTaskFlowDto> flowList = new ArrayList<>();
	
	public void setFlowList(List<ActivitiTaskFlowDto> flowList) {
		this.flowList = flowList;
	}
	
	public List<ActivitiTaskFlowDto> getFlowList() {
		return flowList;
	}
}
