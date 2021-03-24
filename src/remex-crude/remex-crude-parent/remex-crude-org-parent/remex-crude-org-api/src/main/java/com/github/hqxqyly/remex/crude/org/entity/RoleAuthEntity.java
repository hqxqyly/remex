package com.github.hqxqyly.remex.crude.org.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("角色权限关系entity")
@TableName("t_role_auth")
public class RoleAuthEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("角色ID")
    protected String roleId;
	
	@ApiModelProperty("权限ID")
    protected String authId;
	
	@ApiModelProperty("是否有二次确认码权限，0：否；1：是；")
	protected Integer isAuthSecondOpw;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public Integer getIsAuthSecondOpw() {
		return isAuthSecondOpw;
	}

	public void setIsAuthSecondOpw(Integer isAuthSecondOpw) {
		this.isAuthSecondOpw = isAuthSecondOpw;
	}
}
