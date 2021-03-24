package com.github.hqxqyly.remex.fast.common.structure.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("分页请求参数")
public class PageReq<T> implements IPageReq {

	@ApiModelProperty("页码")
	protected int pageNum = 1;
	
	@ApiModelProperty("每页数量")
	protected int pageSize = 10;
	
	@ApiModelProperty("参数数据")
	protected T req;
	
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setReq(T req) {
		this.req = req;
	}
	
	public T getReq() {
		return req;
	}
}
