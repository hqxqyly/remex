package com.github.hqxqyly.remex.crude.activiti.rsp.definition;

import java.util.ArrayList;
import java.util.List;

import com.github.hqxqyly.remex.crude.activiti.bean.definition.ActivitiDefinitionBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("工作流定义dto")
public class ActivitiDefinitionDto extends ActivitiDefinitionBean {

	@ApiModelProperty("用户ID列表")
	protected List<String> userIdList = new ArrayList<>();
	
	@ApiModelProperty("用户名称列表")
	protected List<String> userNameList = new ArrayList<>();
	
	@ApiModelProperty("用户组ID列表")
	protected List<String> groupIdList = new ArrayList<>();
	
	@ApiModelProperty("用户组名称列表")
	protected List<String> groupNameList = new ArrayList<>();

	public List<String> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}

	public List<String> getUserNameList() {
		return userNameList;
	}

	public void setUserNameList(List<String> userNameList) {
		this.userNameList = userNameList;
	}

	public List<String> getGroupIdList() {
		return groupIdList;
	}

	public void setGroupIdList(List<String> groupIdList) {
		this.groupIdList = groupIdList;
	}

	public List<String> getGroupNameList() {
		return groupNameList;
	}

	public void setGroupNameList(List<String> groupNameList) {
		this.groupNameList = groupNameList;
	}
}
