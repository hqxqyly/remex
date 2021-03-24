package com.github.hqxqyly.remex.crude.activiti.req.user;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("删除工作流用户req")
public class ActivitiUserDeleteReq {

	@NotBlank
	@ApiModelProperty("用户ID")
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
