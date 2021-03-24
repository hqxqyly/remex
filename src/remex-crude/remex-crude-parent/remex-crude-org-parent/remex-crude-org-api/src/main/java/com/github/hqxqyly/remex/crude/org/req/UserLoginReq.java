package com.github.hqxqyly.remex.crude.org.req;

import javax.validation.constraints.NotBlank;

import com.github.hqxqyly.remex.boot.constant.groups.GroupCreate;
import com.github.hqxqyly.remex.boot.swagger.annotations.ApiModelPropertyHidden;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("登录查询用户req")
public class UserLoginReq {
	
	@ApiModelPropertyHidden(groups = {GroupCreate.class})
	@NotBlank
	@ApiModelProperty("用户名")
	private String username;
	
	public UserLoginReq() {
		super();
	}

	public UserLoginReq(String username) {
		super();
		this.username = username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
}
