package com.github.hqxqyly.remex.crude.org.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.hqxqyly.remex.fast.api.structure.entity.AgileEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Valid
@ApiModel("用户entity")
@TableName("t_user")
public class UserEntity extends AgileEntity {
	private static final long serialVersionUID = 1L;

	@NotBlank
	@ApiModelProperty("用户名")
	protected String username;
	
	@NotBlank
	@ApiModelProperty("姓名")
    protected String name;

    @ApiModelProperty("员工编号")
    protected String userNo;

    @NotBlank
    @ApiModelProperty("角色ID")
    protected String roleId;
    
    @ApiModelProperty("是否启用，0：否；1：是；")
    protected Integer isEnabled;
    
    @ApiModelProperty("部门ID")
    protected String departmentId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Integer getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
}
