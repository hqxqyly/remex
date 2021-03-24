package com.github.hqxqyly.remex.fast.server.interfaces.assist;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.github.hqxqyly.remex.fast.common.interfaces.assist.IAssistResult;
import com.github.hqxqyly.remex.fast.common.structure.rsp.PageData;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;

/**
 * 辅助接口 - mybatis结果集
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IAssistMybatisResult extends IAssistResult {
	
	/**
	 * 创建分页结果数据，PageHelper
	 * @return
	 */
	default <T> PageData<T> newPageData(List<T> dataList, PageInfo<?> pageInfo) {
		return newPageData(dataList, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages());
	}
	
	/**
	 * 创建分页结果数据，PageHelper
	 * @param dataList 数据列表
	 * @param pageInfo 分页信息的数据列表，正常为dao分页查询出来的列表
	 * @return
	 */
	default <T> PageData<T> newPageData(List<T> dataList, List<?> pageInfo) {
		return newPageData(dataList, new PageInfo<>(pageInfo));
	}
	
	/**
	 * 创建分页结果数据，PageHelper
	 * @return
	 */
	default <T> PageData<T> newPageData(PageInfo<?> pageInfo) {
		return newPageData(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages());
	}
	
	/**
	 * 创建分页结果数据，PageHelper
	 * @param pageInfo 分页信息的数据列表，正常为dao分页查询出来的列表
	 * @return
	 */
	default <T> PageData<T> newPageData(List<?> pageInfo) {
		return newPageData(new PageInfo<>(pageInfo));
	}
	
	
	
	/**
	 * 创建分页结果集，PageHelper
	 * @return
	 */
	default <T> Result<PageData<T>> newPageResult(List<T> dataList, PageInfo<?> pageInfo) {
		return newResult(newPageData(dataList, pageInfo));
	}
	
	/**
	 * 创建分页结果集，PageHelper
	 * @param dataList 数据列表
	 * @param pageInfo 分页信息的数据列表，正常为dao分页查询出来的列表
	 * @return
	 */
	default <T> Result<PageData<T>> newPageResult(List<T> dataList, List<?> pageInfo) {
		return newResult(newPageData(dataList, pageInfo));
	}

	/**
	 * 创建分页结果集，PageHelper
	 * @return
	 */
	default <T> Result<PageData<T>> newPageResult(List<T> dataList) {
		return newPageResult(dataList, dataList);
	}
	
	/**
	 * 创建分页结果集，PageHelper
	 * @return
	 */
	default <T> Result<PageData<T>> newPageResult(List<?> dataList, Class<T> dtoClass) {
		return newPageResult(toBeanList(dataList, dtoClass), dataList);
	}
	
	

	
	
	
	/**
	 * 创建分页结果数据，mybatis-plus
	 * @return
	 */
	default <T> PageData<T> newPageData(IPage<T> page) {
		return newPageData(page.getRecords(), page.getCurrent(), page.getSize(), page.getTotal(), page.getPages());
	}
	
	/**
	 * 创建分页结果数据，mybatis-plus
	 * @return
	 */
	default <T> PageData<T> newPageData(List<T> dataList, IPage<?> page) {
		return newPageData(dataList, page.getCurrent(), page.getSize(), page.getTotal(), page.getPages());
	}
	
	
	
	/**
	 * 创建分页结果集，mybatis-plus
	 * @return
	 */
	default <T> Result<PageData<T>> newPageResult(IPage<T> page) {
		return newResult(newPageData(page));
	}
	
	/**
	 * 创建分页结果集，mybatis-plus
	 * @return
	 */
	default <T> Result<PageData<T>> newPageResult(IPage<?> page, Class<T> dtoClass) {
		return newResult(newPageData(toBeanList(page.getRecords(), dtoClass), page));
	}
}
