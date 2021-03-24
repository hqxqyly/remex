package com.github.hqxqyly.remex.crude.activiti.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("创建工作流用户req")
public class ActivitiUserCreateReq extends ActivitiUserSaveReq {

	@ApiModelProperty("用户组ID")
	protected String groupId;
	
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	public String getGroupId() {
		return groupId;
	}
}
