package com.github.hqxqyly.remex.crude.activiti.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("查询工作流用户列表req")
public class ActivitiUserQueryReq {

	@ApiModelProperty("ID")
	protected String id;
	
	@ApiModelProperty("名")
	protected String lastName;
	
	@ApiModelProperty("姓")
	protected String firstName;
	
	@ApiModelProperty("用户组ID")
	protected String groupId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	public String getGroupId() {
		return groupId;
	}
}
