package com.github.hqxqyly.remex.fast.common.structure.rsp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.github.hqxqyly.remex.boot.utils.Assist;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("分页结果数据")
public class PageData<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("页码")
	protected long pageNum = 1;
	
	@ApiModelProperty("每页数量")
	protected long pageSize = 10;
	
	@ApiModelProperty("总数量")
	protected long total = 0;
    
	@ApiModelProperty("总页数")
	protected long pages = 0;
	
	@ApiModelProperty("数据列表")
	protected List<T> dataList = new ArrayList<>();
	
	/**
	 * 创建分页结果数据
	 * @return
	 */
	public static <T> PageData<T> newInstance(long pageNum, long pageSize, long total) {
		long pages = (long) Math.ceil((double) total / pageSize);
		return newInstance(pageNum, pageSize, total, pages);
	}
	
	/**
	 * 创建分页结果数据
	 * @return
	 */
	public static <T> PageData<T> newInstance(long pageNum, long pageSize, long total, long pages) {
		PageData<T> pageData = new PageData<>();
		pageData.setPageNum(pageNum);
		pageData.setPageSize(pageSize);
		pageData.setTotal(total);
		pageData.setPages((long) Math.ceil((double) total / pageSize));
		return pageData;
	}
	
	/**
	 * 创建分页结果数据
	 * @return
	 */
	public static <T> PageData<T> newInstance(Collection<T> dataList, long pageNum, long pageSize, long total) {
		PageData<T> pageData = newInstance(pageNum, pageSize, total);
		pageData.setDataList(Assist.toList(dataList));
		return pageData;
	}
	
	/**
	 * 创建分页结果数据
	 * @return
	 */
	public static <T> PageData<T> newInstance(Collection<T> dataList, long pageNum, long pageSize, long total, long pages) {
		PageData<T> pageData = newInstance(pageNum, pageSize, total, pages);
		pageData.setDataList(Assist.toList(dataList));
		return pageData;
	}

	public long getPageNum() {
		return pageNum;
	}

	public void setPageNum(long pageNum) {
		this.pageNum = pageNum;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getPages() {
		return pages;
	}

	public void setPages(long pages) {
		this.pages = pages;
	}
	
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	public List<T> getDataList() {
		return dataList;
	}
}
