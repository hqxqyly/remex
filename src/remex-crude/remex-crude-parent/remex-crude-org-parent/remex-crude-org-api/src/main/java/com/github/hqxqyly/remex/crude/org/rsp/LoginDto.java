package com.github.hqxqyly.remex.crude.org.rsp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("登录结果信息dto")
public class LoginDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("id")
    private String id;

	@ApiModelProperty("用户名")
    private String username;
    
    @ApiModelProperty("姓名")
    private String name;
    
    @ApiModelProperty("员工编号")
    private String userNo;
    
    @ApiModelProperty("token")
    private String token;
    
    @ApiModelProperty("角色ID")
    private String roleId;
    
    @ApiModelProperty("权限代号列表")
    private List<String> authCodeList = new ArrayList<>();
    
    @ApiModelProperty("需要确认码的权限url列表")
    private List<String> opwAuthUrlList = new ArrayList<>();
    
    @ApiModelProperty("需要二次确认码的权限url列表")
    private List<String> secondOpwAuthUrlList = new ArrayList<>();

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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public List<String> getAuthCodeList() {
		return authCodeList;
	}

	public void setAuthCodeList(List<String> authCodeList) {
		this.authCodeList = authCodeList;
	}

	public List<String> getOpwAuthUrlList() {
		return opwAuthUrlList;
	}

	public void setOpwAuthUrlList(List<String> opwAuthUrlList) {
		this.opwAuthUrlList = opwAuthUrlList;
	}

	public List<String> getSecondOpwAuthUrlList() {
		return secondOpwAuthUrlList;
	}

	public void setSecondOpwAuthUrlList(List<String> secondOpwAuthUrlList) {
		this.secondOpwAuthUrlList = secondOpwAuthUrlList;
	}
}
