package com.github.hqxqyly.remex.fast.server.interfaces.assist;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * 辅助接口 - mybatis plus条件相关
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IAssistMybatisPlusWrapper {
	
	/**
	 * 创建查询条件
	 * @return
	 */
	default <T> QueryWrapper<T> newQueryWrapper() {
		return new QueryWrapper<>();
	}
	
	/**
	 * 创建查询条件
	 * @return
	 */
	default <T> QueryWrapper<T> newQueryWrapper(String column, Object val) {
		QueryWrapper<T> wrapper = newQueryWrapper();
		return wrapper.eq(column, val);
	}
	
	/**
	 * 创建查询条件
	 * @return
	 */
	default <T> QueryWrapper<T> newQueryWrapper(String column1, Object val1, String column2, Object val2) {
		QueryWrapper<T> wrapper = newQueryWrapper();
		return wrapper.eq(column1, val1).eq(column2, val2);
	}
	
	/**
	 * 创建查询条件
	 * @return
	 */
	default <T> QueryWrapper<T> newQueryWrapper(String column1, Object val1, String column2, Object val2, String column3, Object val3) {
		QueryWrapper<T> wrapper = newQueryWrapper();
		return wrapper.eq(column1, val1).eq(column2, val2).eq(column3, val3);
	}
	
	/**
	 * 创建entity查询条件
	 * @param entity 实体
	 * @param columns 指定查询字段
	 * @return
	 */
	default <T> QueryWrapper<T> newQueryWrapper(T entity, String...columns) {
		return new QueryWrapper<>(entity, columns);
	}
}
