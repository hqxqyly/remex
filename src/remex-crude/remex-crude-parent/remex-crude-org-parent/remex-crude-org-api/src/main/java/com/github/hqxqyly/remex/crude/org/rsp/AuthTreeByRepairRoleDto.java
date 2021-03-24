package com.github.hqxqyly.remex.crude.org.rsp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("某角色维护的权限树dto")
public class AuthTreeByRepairRoleDto<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("当前数据")
	protected T cur;

	@ApiModelProperty("是否有权限，0：否；1：是；")
	protected Integer isAuth;
	
	@ApiModelProperty("是否有二次确认码权限，0：否；1：是；")
	protected Integer isAuthSecondOpw;
	
	@ApiModelProperty("子权限列表")
	protected List<AuthTreeByRepairRoleDto<T>> childList = new ArrayList<>();

	public AuthTreeByRepairRoleDto() {
		super();
	}

	public AuthTreeByRepairRoleDto(T cur) {
		super();
		this.cur = cur;
	}

	public AuthTreeByRepairRoleDto(T cur, Integer isAuth, Integer isAuthSecondOpw) {
		super();
		this.cur = cur;
		this.isAuth = isAuth;
		this.isAuthSecondOpw = isAuthSecondOpw;
	}
	
	/**
	 * 添加子节点
	 * @param dto
	 */
	public void addChild(AuthTreeByRepairRoleDto<T> dto) {
		this.childList.add(dto);
	}

	public Integer getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(Integer isAuth) {
		this.isAuth = isAuth;
	}

	public Integer getIsAuthSecondOpw() {
		return isAuthSecondOpw;
	}

	public void setIsAuthSecondOpw(Integer isAuthSecondOpw) {
		this.isAuthSecondOpw = isAuthSecondOpw;
	}

	public T getCur() {
		return cur;
	}

	public void setCur(T cur) {
		this.cur = cur;
	}

	public List<AuthTreeByRepairRoleDto<T>> getChildList() {
		return childList;
	}

	public void setChildList(List<AuthTreeByRepairRoleDto<T>> childList) {
		this.childList = childList;
	}
}
