package com.github.hqxqyly.remex.crude.activiti.rsp.user;

import com.github.hqxqyly.remex.crude.activiti.bean.user.ActivitiUserBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("工作流用户dto")
public class ActivitiUserDto extends ActivitiUserBean {
	
	@ApiModelProperty("用户组ID")
	protected String groupId;
	
	@ApiModelProperty("用户组名称")
	protected String groupName;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
