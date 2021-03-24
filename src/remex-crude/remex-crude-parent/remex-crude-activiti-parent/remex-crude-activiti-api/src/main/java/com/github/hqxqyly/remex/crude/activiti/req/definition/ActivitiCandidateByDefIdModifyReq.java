package com.github.hqxqyly.remex.crude.activiti.req.definition;

import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("修改某工作流定义的可申请人信息req")
public class ActivitiCandidateByDefIdModifyReq {

	@NotBlank
	@ApiModelProperty("流程定义ID")
	protected String id;
	
	@ApiModelProperty("用户ID列表")
	protected List<String> userIdList;
	
	@ApiModelProperty("用户组ID列表")
	protected List<String> groupIdList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}

	public List<String> getGroupIdList() {
		return groupIdList;
	}

	public void setGroupIdList(List<String> groupIdList) {
		this.groupIdList = groupIdList;
	}
}
