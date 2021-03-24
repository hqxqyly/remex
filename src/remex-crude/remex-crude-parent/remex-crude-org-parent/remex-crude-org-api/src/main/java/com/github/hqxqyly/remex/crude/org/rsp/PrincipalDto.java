package com.github.hqxqyly.remex.crude.org.rsp;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("鉴权缓存授权信息")
public class PrincipalDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("ID")
	protected String id;

	@ApiModelProperty("用户名")
    protected String username;
    
	@ApiModelProperty("姓名")
    protected String name;
    
	@ApiModelProperty("员工编号")
    protected String userNo;
    
	@ApiModelProperty("角色ID")
    protected String roleId;
    
	@ApiModelProperty("确认码")
    protected String opw;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getOpw() {
		return opw;
	}

	public void setOpw(String opw) {
		this.opw = opw;
	}
}
