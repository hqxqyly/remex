package com.github.hqxqyly.remex.crude.activiti.rsp.definition;

import java.util.List;

import com.github.hqxqyly.remex.crude.activiti.bean.group.ActivitiGroupBean;
import com.github.hqxqyly.remex.crude.activiti.bean.user.ActivitiUserBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("工作流定义的可申请人信息dto")
public class ActivitiDefCandidateDto {

	@ApiModelProperty("用户列表")
	protected List<ActivitiUserBean> userList;
	
	@ApiModelProperty("用户组列表")
	protected List<ActivitiGroupBean> groupList;

	public List<ActivitiUserBean> getUserList() {
		return userList;
	}

	public void setUserList(List<ActivitiUserBean> userList) {
		this.userList = userList;
	}

	public List<ActivitiGroupBean> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<ActivitiGroupBean> groupList) {
		this.groupList = groupList;
	}
}
