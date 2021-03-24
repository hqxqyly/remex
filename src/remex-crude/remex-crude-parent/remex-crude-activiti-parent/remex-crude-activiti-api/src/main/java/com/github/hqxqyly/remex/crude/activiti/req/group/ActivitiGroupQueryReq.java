package com.github.hqxqyly.remex.crude.activiti.req.group;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("查询工作流用户组列表req")
public class ActivitiGroupQueryReq {

	@ApiModelProperty("用户组ID")
	protected String id;
	
	@ApiModelProperty("用户组名称")
	protected String name;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
