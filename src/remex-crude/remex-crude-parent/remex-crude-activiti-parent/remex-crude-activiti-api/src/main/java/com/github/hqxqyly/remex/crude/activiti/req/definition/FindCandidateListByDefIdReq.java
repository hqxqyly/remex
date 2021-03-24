package com.github.hqxqyly.remex.crude.activiti.req.definition;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("查询某工作流定义的可申请人信息列表req")
public class FindCandidateListByDefIdReq {

	@NotBlank
	@ApiModelProperty("流程定义ID")
	protected String id;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}
