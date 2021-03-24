package com.github.hqxqyly.remex.crude.org.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户安全entity")
@TableName("t_user")
public class UserSafeEntity extends UserEntity implements IUserSafeEntity {
	private static final long serialVersionUID = 1L;
    
    @ApiModelProperty("密码")
    protected String password;
    
    @ApiModelProperty("操作码")
	protected String opw;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOpw() {
		return opw;
	}

	public void setOpw(String opw) {
		this.opw = opw;
	}
}
