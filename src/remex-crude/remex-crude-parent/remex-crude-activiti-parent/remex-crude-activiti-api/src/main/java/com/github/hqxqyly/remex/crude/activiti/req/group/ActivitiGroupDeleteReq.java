package com.github.hqxqyly.remex.crude.activiti.req.group;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("删除工作流用户组req")
public class ActivitiGroupDeleteReq {

	@NotBlank
	@ApiModelProperty("用户组ID")
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
