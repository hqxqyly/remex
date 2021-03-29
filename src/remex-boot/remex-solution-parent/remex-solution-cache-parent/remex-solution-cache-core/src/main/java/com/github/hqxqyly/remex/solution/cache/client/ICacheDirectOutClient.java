package com.github.hqxqyly.remex.solution.cache.client;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import com.github.hqxqyly.remex.boot.utils.ApplicationContextUtils;
import com.github.hqxqyly.remex.solution.cache.common.client.ICacheOutClient;

/**
 * 缓存处理器 - 衔接外部组件接口
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface ICacheDirectOutClient extends ICacheClient {

	/**
	 * 取得外部组件处理器
	 * @return
	 */
	default ICacheOutClient getOutClient() {
		return ApplicationContextUtils.getBean(ICacheOutClient.class);
	}
	
	/**
	 * 根据条件获取所有key，可以以*作通配符，如：CACHE_KEY:*
	 * @param key
	 * @return
	 */
	@Override
	default Set<String> keys(String key) {
		return getOutClient().keys(key);
	}

	/**
	 * 设置数据
	 * @param key
	 * @param value
	 * @param timeout
	 * @param unit
	 */
	@Override
	default void set(String key, String value, Long timeout, TimeUnit unit) {
		getOutClient().set(key, value, timeout, unit);
	}
	
	/**
	 * 设置数据到List，加到List后面
	 * @param key
	 * @param value
	 */
	@Override
	default void setToList(String key, String value) {
		getOutClient().setToList(key, value);
	}
	
	/**
	 * 设置数据到Set
	 * @param key
	 * @param value
	 */
	@Override
	default void setToSet(String key, String value) {
		getOutClient().setToSet(key, value);
	}
	
	/**
	 * 根据key取得数据
	 * @param key
	 * @return
	 */
	@Override
	default String get(String key) {
		return getOutClient().get(key);
	}
	
	/**
	 * 根据key取得List
	 * @param key
	 * @return
	 */
	@Override
	default List<String> getList(String key) {
		return getOutClient().getList(key);
	}

	/**
	 * 根据key取得Set
	 * @param key
	 * @return
	 */
	@Override
	default Set<String> getSet(String key) {
		return getOutClient().getSet(key);
	}

	/**
	 * 根据key删除
	 * @param key
	 */
	@Override
	default void delete(String key) {
		getOutClient().delete(key);
	}
	
	/**
	 * 锁处理
	 * @param <T>
	 * @param key
	 * @param action
	 * @return
	 */
	@Override
	default <T> T lock(String key, Supplier<T> action) {
		return getOutClient().lock(key, action);
	}
}
