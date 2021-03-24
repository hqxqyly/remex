package com.github.hqxqyly.remex.crude.activiti.req.group;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("保存工作流用户组req")
public class ActivitiGroupSaveReq {

	@ApiModelProperty("用户组ID")
	protected String id;
	
	@NotBlank
	@ApiModelProperty("用户组名称")
	protected String name;
	
	@ApiModelProperty("用户组类型")
	protected String type;

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
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
