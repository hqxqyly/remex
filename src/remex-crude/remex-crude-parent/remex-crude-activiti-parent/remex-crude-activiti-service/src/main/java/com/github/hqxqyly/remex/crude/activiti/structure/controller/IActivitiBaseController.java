package com.github.hqxqyly.remex.crude.activiti.structure.controller;

import java.util.List;
import java.util.function.Function;

import org.activiti.engine.query.Query;

import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.fast.common.structure.req.PageReq;
import com.github.hqxqyly.remex.fast.common.structure.rsp.PageData;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;
import com.github.hqxqyly.remex.fast.server.structure.controller.IBaseController;

/**
 * 工作流基础controller
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IActivitiBaseController extends IBaseController {

	/**
	 * 分页查询
	 * @param <T>
	 * @param pageReq
	 * @param query
	 * @param dtoClass
	 * @return
	 */
	default <T, R> Result<PageData<R>> queryPage(PageReq<?> pageReq, Query<?,T> query, Function<T, R> action) {
		//查询总数量
		int total = (int) query.count();

		int firstResult = (pageReq.getPageNum() - 1) * pageReq.getPageSize();
		int maxResults = pageReq.getPageNum() * pageReq.getPageSize();

		//查询数据
		List<T> list = query.listPage(firstResult, maxResults);
		//转换数据结构
		List<R> dtoList = Assist.forEachToList(list, action);
		return newPageResult(dtoList, pageReq, total);
	}
	
	/**
	 * 分页查询
	 * @param <T>
	 * @param pageReq
	 * @param query
	 * @param dtoClass
	 * @return
	 */
	default <T, R> Result<PageData<R>> queryPage(PageReq<?> pageReq, Query<?,T> query, Class<R> dtoClass) {
		return queryPage(pageReq, query, data -> toBean(data, dtoClass));
	}
}
