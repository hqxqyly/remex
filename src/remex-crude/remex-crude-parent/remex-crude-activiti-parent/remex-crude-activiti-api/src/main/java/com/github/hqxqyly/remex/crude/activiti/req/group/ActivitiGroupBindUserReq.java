package com.github.hqxqyly.remex.crude.activiti.req.group;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("绑定工作流用户组的用户req")
public class ActivitiGroupBindUserReq {
	
	@NotBlank
	@ApiModelProperty("用户组ID")
	protected String groupId;

	@ApiModelProperty("用户ID列表")
	protected List<String> userIdList = new ArrayList<>();
	
	@ApiModelProperty("移除的用户ID列表")
	protected List<String> removeUserIdList = new ArrayList<>();

	public List<String> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}

	public List<String> getRemoveUserIdList() {
		return removeUserIdList;
	}

	public void setRemoveUserIdList(List<String> removeUserIdList) {
		this.removeUserIdList = removeUserIdList;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
}
