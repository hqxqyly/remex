package com.github.hqxqyly.remex.fast.common.structure.rsp.preinstall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("树结构dto")
public class TreeDto<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("当前数据")
	protected T cur;
	
	@ApiModelProperty("子数据列表")
	protected List<TreeDto<T>> childList = new ArrayList<>();
	
	public TreeDto() {
		super();
	}

	public TreeDto(T cur) {
		super();
		this.cur = cur;
	}

	/**
	 * 添加子节点
	 * @param dto
	 */
	public void addChild(TreeDto<T> dto) {
		this.childList.add(dto);
	}

	public T getCur() {
		return cur;
	}

	public void setCur(T cur) {
		this.cur = cur;
	}

	public List<TreeDto<T>> getChildList() {
		return childList;
	}

	public void setChildList(List<TreeDto<T>> childList) {
		this.childList = childList;
	}
}
