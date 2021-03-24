package com.github.hqxqyly.remex.fast.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.hqxqyly.remex.boot.mybatis.plus.mapper.RemexBaseMapper;
import com.github.hqxqyly.remex.fast.common.structure.req.IPageReq;

/**
 * 扩展BaseMethod，提供一些新的方法
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface FastBaseMapper<T> extends RemexBaseMapper<T> {

	/**
	 * 根据条件分页查询数据列表
	 * @return
	 */
	default IPage<T> selectPage(IPageReq pageReq) {
		return selectPage(pageReq, null);
	}
	
	/**
	 * 根据条件分页查询数据列表
	 * @return
	 */
	default IPage<T> selectPage(IPageReq pageReq, Wrapper<T> queryWrapper) {
    	return selectPage(pageReq.getPageNum(), pageReq.getPageSize(), queryWrapper);
    }
}
