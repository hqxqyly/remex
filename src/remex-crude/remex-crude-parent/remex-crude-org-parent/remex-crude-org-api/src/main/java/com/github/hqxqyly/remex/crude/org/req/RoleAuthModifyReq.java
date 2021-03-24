package com.github.hqxqyly.remex.crude.org.req;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("修改角色权限req")
public class RoleAuthModifyReq implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank
	@ApiModelProperty("角色ID")
	protected String roleId;
	
	@ApiModelProperty("权限ID列表")
	protected List<String> authIdList = new ArrayList<>();
	
	@ApiModelProperty("二次确认码权限ID列表")
	protected List<String> secondOpwAuthIdList = new ArrayList<>();

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public List<String> getAuthIdList() {
		return authIdList;
	}

	public void setAuthIdList(List<String> authIdList) {
		this.authIdList = authIdList;
	}

	public List<String> getSecondOpwAuthIdList() {
		return secondOpwAuthIdList;
	}

	public void setSecondOpwAuthIdList(List<String> secondOpwAuthIdList) {
		this.secondOpwAuthIdList = secondOpwAuthIdList;
	}
}
