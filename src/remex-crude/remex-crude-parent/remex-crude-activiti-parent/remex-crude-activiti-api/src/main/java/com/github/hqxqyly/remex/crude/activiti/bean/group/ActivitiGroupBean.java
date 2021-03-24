package com.github.hqxqyly.remex.crude.activiti.bean.group;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("工作流用户组bean")
public class ActivitiGroupBean {

	@ApiModelProperty("用户组ID")
	protected String id;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
