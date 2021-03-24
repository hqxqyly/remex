package com.github.hqxqyly.remex.fast.server.interfaces.assist;

import com.github.pagehelper.PageHelper;
import com.github.hqxqyly.remex.boot.interfaces.assist.IAssist;
import com.github.hqxqyly.remex.fast.common.structure.req.IPageReq;

/**
 * 辅助接口 - mybatis启动分页
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IAssistMybatisStartPage extends IAssist {
	
	/**
	 * 启动分页
	 * @param pageReq 分页请求头
	 */
	default void startPage(IPageReq pageReq) {
		startPage(pageReq.getPageNum(), pageReq.getPageSize());
	}

	/**
	 * 启动分页
	 * @param pageNum 当前页码
	 * @param pageSize 每页数量
	 */
	default void startPage(int pageNum, int pageSize) {
		startPage(pageNum, pageSize, null);
	}
	
	/**
	 * 启用分页
	 * @param pageNum 当前页码
	 * @param pageSize 每页数量
	 * @param totalCount 总数，如果设置，则直接使用此值作为总数
	 */
	default void startPage(int pageNum, int pageSize, Long totalCount) {
		if (pageNum < 1) pageNum = 1;
		if (pageSize < 1) pageSize = 10;
		
		PageHelper.startPage(pageNum, pageSize);
	}
}
