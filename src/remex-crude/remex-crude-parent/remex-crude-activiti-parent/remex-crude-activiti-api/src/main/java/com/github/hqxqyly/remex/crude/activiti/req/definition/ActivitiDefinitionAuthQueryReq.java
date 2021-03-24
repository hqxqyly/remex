package com.github.hqxqyly.remex.crude.activiti.req.definition;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("查询可发起的工作流定义列表req")
public class ActivitiDefinitionAuthQueryReq {

	@NotBlank
	@ApiModelProperty("用户ID")
	protected String userId;
	
	@ApiModelProperty("实例key")
	protected String instanceKey;
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setInstanceKey(String instanceKey) {
		this.instanceKey = instanceKey;
	}
	
	public String getInstanceKey() {
		return instanceKey;
	}
}
