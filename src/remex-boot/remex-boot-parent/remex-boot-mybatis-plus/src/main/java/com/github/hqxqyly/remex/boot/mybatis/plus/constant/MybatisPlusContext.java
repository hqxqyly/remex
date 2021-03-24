package com.github.hqxqyly.remex.boot.mybatis.plus.constant;

import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.hqxqyly.remex.boot.utils.ApplicationContextUtils;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * mybatis plus上下文
 * 
 * @author Qiaoxin.Hong
 *
 */
public class MybatisPlusContext {

	/** entity与dao映射列表 */
	protected static Map<Class<?>, Class<? extends BaseMapper<?>>> entityAndDaoMap = new HashMap<>();
	
	/**
	 * 添加entity与dao映射
	 * @param modelClass
	 * @param mapperClass
	 */
	public static void addMapper(Class<?> modelClass, Class<? extends BaseMapper<?>> mapperClass) {
		Assist.notNull(modelClass, "modelClass cannot be null");
		Assist.notNull(mapperClass, "mapperClass cannot be null");
		
		entityAndDaoMap.put(modelClass, mapperClass);
	}
	
	/**
	 * 根据实体class取得dao class
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <E, T extends BaseMapper<E>> Class<T> getDaoClass(Class<E> modelClass) {
		return (Class<T>) entityAndDaoMap.get(modelClass);
	}
	
	/**
	 * 根据实体class取得dao
	 * @param modelClass
	 * @return
	 */
	public static <E, T extends BaseMapper<E>> T getDao(Class<E> modelClass) {
		Class<T> daoClass = getDaoClass(modelClass);
		Assist.notNull(daoClass, "daoClass not found [modelClass : {}]", modelClass);
		return ApplicationContextUtils.getBean(daoClass);
	}
}
