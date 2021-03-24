package com.github.hqxqyly.remex.fast.common.interfaces.assist;

import java.util.Collection;
import java.util.List;

import com.github.hqxqyly.remex.boot.interfaces.assist.IAssist;
import com.github.hqxqyly.remex.boot.msg.IMsgEnum;
import com.github.hqxqyly.remex.fast.common.structure.req.IPageReq;
import com.github.hqxqyly.remex.fast.common.structure.rsp.PageData;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;

/**
 * 辅助接口 - 结果集
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IAssistResult extends IAssist {

	/**
	 * 创建默认成功的结果集
	 * @return
	 */
	default <T> Result<T> newResult() {
		return Result.newResult();
	}
	
	/**
	 * 创建默认成功的结果集
	 * @return
	 */
	default <T> Result<T> newResult(T data) {
		return Result.newResult(data);
	}
	
	/**
	 * 创建结果集
	 * @return
	 */
	default <T> Result<T> newResult(IMsgEnum msgEnum, Object...msgArgs) {
		return Result.newResult(msgEnum, msgArgs);
	}
	
	/**
	 * 对象转换，创建结果集
	 * @return
	 */
	default <T> Result<T> newResult(Object data, Class<T> dtoClass) {
		notNull(dtoClass, "dtoClass cannot be null");
		return newResult(toBean(data, dtoClass));
	}
	
	/**
	 * 对象转换，创建结果集
	 * @return
	 */
	default <T> Result<List<T>> newResult(Collection<?> dataList, Class<T> dtoClass) {
		notNull(dtoClass, "dtoClass cannot be null");
		return newResult(toBeanList(dataList, dtoClass));
	}
	
	
	
	
	/**
	 * 创建分页结果数据
	 * @return
	 */
	default <T> PageData<T> newPageData(Collection<T> dataList, long pageNum, long pageSize, long total, long pages) {
		return PageData.newInstance(dataList, pageNum, pageSize, total, pages);
	}
	
	/**
	 * 创建分页结果数据
	 * @return
	 */
	default <T> PageData<T> newPageData(Collection<T> dataList, long pageNum, long pageSize, long total) {
		return PageData.newInstance(dataList, pageNum, pageSize, total);
	}
	
	/**
	 * 创建分页结果数据
	 * @return
	 */
	default <T> PageData<T> newPageData(long pageNum, long pageSize, long total, long pages) {
		return PageData.newInstance(pageNum, pageSize, total, pages);
	}
	
	/**
	 * 创建分页结果数据
	 * @return
	 */
	default <T> PageData<T> newPageData(long pageNum, long pageSize, long total) {
		return PageData.newInstance(pageNum, pageSize, total);
	}


	
	
	/**
	 * 创建分页结果集
	 * @return
	 */
	default <T> Result<PageData<T>> newPageResult() {
		return newResult();
	}
	
	/**
	 * 创建分页结果集
	 * @return
	 */
	default <T> Result<PageData<T>> newPageResult(Collection<T> dataList, long pageNum, long pageSize, long total) {
		return newResult(newPageData(dataList, pageNum, pageSize, total));
	}
	
	/**
	 * 创建分页结果集
	 * @return
	 */
	default <T> Result<PageData<T>> newPageResult(Collection<?> dataList, Class<T> dtoClass, long pageNum, long pageSize, long total) {
		return newPageResult(toBeanList(dataList, dtoClass), pageNum, pageSize, total);
	}
	
	/**
	 * 创建分页结果集
	 * @return
	 */
	default <T> Result<PageData<T>> newPageResult(Collection<T> dataList, IPageReq pageReq, long total) {
		return newPageResult(dataList, pageReq.getPageNum(), pageReq.getPageSize(), total);
	}
	
	/**
	 * 创建分页结果集
	 * @return
	 */
	default <T> Result<PageData<T>> newPageResult(Collection<?> dataList, Class<T> dtoClass, IPageReq pageReq, long total) {
		return newPageResult(dataList, dtoClass, pageReq.getPageNum(), pageReq.getPageSize(), total);
	}
}
