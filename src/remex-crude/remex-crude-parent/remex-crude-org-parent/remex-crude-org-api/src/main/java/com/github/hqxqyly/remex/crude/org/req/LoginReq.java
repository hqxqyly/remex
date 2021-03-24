package com.github.hqxqyly.remex.crude.org.req;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("登录req")
public class LoginReq implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank
	@ApiModelProperty("用户名")
	private String username;
	
	@JSONField(serialize = false)
	@NotBlank
	@ApiModelProperty("密码")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
