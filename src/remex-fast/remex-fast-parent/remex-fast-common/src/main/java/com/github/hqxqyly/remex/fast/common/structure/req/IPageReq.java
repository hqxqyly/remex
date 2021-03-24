package com.github.hqxqyly.remex.fast.common.structure.req;

/**
 * 分页请求头规范
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IPageReq {

	/**
	 * 取得页码
	 * @return
	 */
	int getPageNum();

	/**
	 * 设置页码
	 * @param pageNum
	 */
	void setPageNum(int pageNum);

	/**
	 * 取得每页数量
	 * @return
	 */
	int getPageSize();

	/**
	 * 设置每页数量
	 * @param pageSize
	 */
	void setPageSize(int pageSize);
}
