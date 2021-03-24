package com.github.hqxqyly.remex.boot.mybatis.plus.utils;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.hqxqyly.remex.boot.mybatis.plus.constant.MybatisPlusContext;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * mybatis plus工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class MybatisPlusUtils {

	/**
	 * 转换page对象
	 * @param <T>
	 * @param resultPage
	 * @param clazz
	 * @return
	 */
	public static <T> IPage<T> toPage(IPage<?> resultPage, Class<T> clazz) {
		Page<T> newResultPage = new Page<T>(resultPage.getCurrent(), resultPage.getSize(), resultPage.getTotal(), resultPage.isSearchCount());
		List<T> dtoList = Assist.toBeanList(resultPage.getRecords(), clazz);
		newResultPage.setRecords(dtoList);
		return newResultPage;
	}
	
	/**
	 * 根据实体class取得dao
	 * @param modelClass
	 * @return
	 */
	public static <E, T extends BaseMapper<E>> T getDao(Class<E> modelClass) {
		return MybatisPlusContext.getDao(modelClass);
	}
}
