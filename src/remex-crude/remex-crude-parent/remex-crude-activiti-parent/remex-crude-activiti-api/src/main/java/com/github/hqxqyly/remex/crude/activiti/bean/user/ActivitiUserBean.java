package com.github.hqxqyly.remex.crude.activiti.bean.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("工作流用户bean")
public class ActivitiUserBean {
	
	@ApiModelProperty("ID")
	protected String id;
	
	@ApiModelProperty("名")
	protected String lastName;
	
	@ApiModelProperty("姓")
	protected String firstName;
	
	@ApiModelProperty("email")
	protected String email;
	
	@ApiModelProperty("密码")
	protected String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
