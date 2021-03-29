package com.github.hqxqyly.remex.boot.guava.utils;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

import com.google.common.cache.Cache;
import com.github.hqxqyly.remex.boot.guava.client.GuavaClient;
import com.github.hqxqyly.remex.boot.utils.ApplicationContextUtils;
import com.github.hqxqyly.remex.solution.cache.common.utils.CacheOutUtils;

/**
 * guava工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class GuavaUtils extends CacheOutUtils {

	/**
	 * 取得guava缓存处理器
	 * @return
	 */
	public static GuavaClient getGuavaClient() {
		return ApplicationContextUtils.getBean(GuavaClient.class);
	}
	
	/**
	 * 初始化并取得默认的无超时时间的缓存容器
	 * @param key
	 * @param timeout
	 * @param unit
	 * @return
	 */
	public static Cache<Object, Object> initCacheContainer(String key) {
		return getGuavaClient().initCacheContainer(key);
	}
	
	/**
	 * 初始化并取得缓存容器
	 * @param key
	 * @param timeout
	 * @param unit
	 * @return
	 */
	public static Cache<Object, Object> initCacheContainer(String key, Long timeout, TimeUnit unit) {
		return getGuavaClient().initCacheContainer(key, timeout, unit);
	}
	
	/**
	 * 取得缓存容器
	 * @param key
	 * @return
	 */
	public static Cache<Object, Object> getCache(String key) {
		return getGuavaClient().getCache(key);
	}
	
	/**
	 * 取得缓存容器，并执行action返回其结果集
	 * @param key
	 * @param action
	 * @return
	 */
	public static <T> T getCacheFn(String key, Function<Cache<Object, Object>, T> action) {
		return getGuavaClient().getCacheFn(key, action);
	}
	
	/**
	 * 取得缓存容器，并执行action
	 * @param key
	 * @param action
	 * @return
	 */
	public static void getCacheRun(String key, Consumer<Cache<Object, Object>> action) {
		getGuavaClient().getCacheRun(key, action);
	}
}
