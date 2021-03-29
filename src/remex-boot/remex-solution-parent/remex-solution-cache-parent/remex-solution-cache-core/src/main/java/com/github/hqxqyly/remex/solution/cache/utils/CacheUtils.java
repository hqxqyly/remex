package com.github.hqxqyly.remex.solution.cache.utils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import com.github.hqxqyly.remex.boot.constant.BConst;
import com.github.hqxqyly.remex.boot.utils.ApplicationContextUtils;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.solution.cache.client.ICacheClient;

/**
 * 缓存工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class CacheUtils {

	/**
	 * 取得处理器
	 * @return
	 */
	public static ICacheClient getClient() {
		return ApplicationContextUtils.getBean(ICacheClient.class);
	}
	
	/**
	 * 拼接缓存key，以“:”作为分割符
	 * @param vals
	 * @return
	 */
	public static String packCacheKey(String...vals) {
		Assist.notEmpty(vals, "vals cannot be empty");
		
		StringBuffer sb = null;
		for (String val : vals) {
			if (sb == null)
				sb = new StringBuffer();
			else
				sb.append(BConst.COLON);
			
			sb.append(Assist.defaultString(val));
			
		}
		return sb.toString();
	}
	
	/**
	 * 根据条件获取所有key，可以以*作通配符，如：CACHE_KEY:*
	 * @param key
	 * @return
	 */
	public static Set<String> keys(String key) {
		return getClient().keys(key);
	}

	/**
	 * 设置数据
	 * @param key
	 * @param value
	 * @param timeout 超时时间，null或 <= 0则表示不超时
	 * @param unit
	 */
	public static void set(String key, String value, Long timeout, TimeUnit unit) {
		getClient().set(key, value, timeout, unit);
	}
	
	/**
	 * 设置数据到List，加到List后面
	 * @param key
	 * @param value
	 */
	public static void setToList(String key, String value) {
		getClient().setToList(key, value);
	}
	
	/**
	 * 设置数据到Set
	 * @param key
	 * @param value
	 */
	public static void setToSet(String key, String value) {
		getClient().setToSet(key, value);
	}
	
	/**
	 * 根据key取得数据
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		return getClient().get(key);
	}
	
	/**
	 * 根据key取得List
	 * @param key
	 * @return
	 */
	public static List<String> getList(String key) {
		return getClient().getList(key);
	}
	
	/**
	 * 根据key取得Set
	 * @param key
	 * @return
	 */
	public static Set<String> getSet(String key) {
		return getClient().getSet(key);
	}
	
	/**
	 * 根据key删除
	 * @param key
	 */
	public static void delete(String key) {
		getClient().delete(key);
	}
	
	/**
	 * 锁处理
	 * @param <T>
	 * @param key
	 * @param action
	 * @return
	 */
	public static <T> T lock(String key, Supplier<T> action) {
		return getClient().lock(key, action);
	}
	
	/**
	 * 是否存在key
	 * @param key
	 * @return
	 */
	public static boolean existKey(String key) {
		return getClient().existKey(key);
	}
	
	/**
	 * 设置数据
	 * @param key
	 * @param value
	 * @param timeout
	 * @param unit
	 */
	public static void set(String key, Object value, Long timeout, TimeUnit unit) {
		getClient().set(key, value, timeout, unit);
	}
	
	/**
	 * 设置json数据
	 * @param key
	 * @param value
	 * @param timeout
	 * @param unit
	 */
	public static void setJson(String key, Object value, Long timeout, TimeUnit unit) {
		getClient().setJson(key, value, timeout, unit);
	}
	
	/**
	 * 设置数据
	 * @param key
	 * @param value
	 */
	public static void set(String key, Object value) {
		getClient().set(key, value);
	}
	
	/**
	 * 设置json数据
	 * @param key
	 * @param value
	 */
	public static void setJson(String key, Object value) {
		getClient().setJson(key, value);
	}
	
	/**
	 * 设置数据
	 * @param key
	 * @param value
	 * @param millisecond 超时时间，单位：毫秒
	 */
	public static void set(String key, Object value, long millisecond) {
		getClient().set(key, value, millisecond);
	}
	
	/**
	 * 设置json数据
	 * @param key
	 * @param value
	 * @param millisecond 超时时间，单位：毫秒
	 */
	public static void setJson(String key, Object value, long millisecond) {
		getClient().setJson(key, value, millisecond);
	}
	
	/**
	 * <pre>
	 * 设置数据到List，加到List后面
	 * value类型：单对象、Stream、Collection、数组、Map（只取value）
	 * </pre>
	 * @param key
	 * @param value
	 */
	public static void setToList(String key, Object value) {
		getClient().setToList(key, value);
	}
	
	/**
	 * <pre>
	 * 设置json数据到List，加到List后面
	 * value类型：单对象、Stream、Collection、数组、Map（只取value）
	 * </pre>
	 * @param key
	 * @param value
	 */
	public static void setToListJson(String key, Object value) {
		getClient().setToListJson(key, value);
	}
	
	/**
	 * <pre>
	 * 设置数据到Set
	 * value类型：单对象、Stream、Collection、数组、Map（只取value）
	 * </pre>
	 * @param key
	 * @param value
	 */
	public static void setToSet(String key, Object value) {
		getClient().setToSet(key, value);
	}
	
	/**
	 * <pre>
	 * 设置json数据到Set
	 * value类型：单对象、Stream、Collection、数组、Map（只取value）
	 * </pre>
	 * @param key
	 * @param value
	 */
	public static void setToSetJson(String key, Object value) {
		getClient().setToSetJson(key, value);
	}
	
	/**
	 * 根据key取得Integer数据
	 * @param key
	 * @return
	 */
	public static Integer getInteger(String key) {
		return getClient().getInteger(key);
	}
	
	/**
	 * 根据key取得Long数据
	 * @param key
	 * @return
	 */
	public static Long getLong(String key) {
		return getClient().getLong(key);
	}
	
	/**
	 * 根据key取得Double数据
	 * @param key
	 * @return
	 */
	public static Double getDouble(String key) {
		return getClient().getDouble(key);
	}
	
	/**
	 * 根据key取得BigDecimal数据
	 * @param key
	 * @return
	 */
	public static BigDecimal getBigDecimal(String key) {
		return getClient().getBigDecimal(key);
	}
	
	/**
	 * 根据key取得json数据
	 * @param key
	 * @return
	 */
	public static <T> T getJson(String key, Class<T> clazz) {
		return getClient().getJson(key, clazz);
	}
	
	/**
	 * 根据key取得List json数据
	 * @param key
	 * @return
	 */
	public static <T> List<T> getListJson(String key, Class<T> clazz) {
		return getClient().getListJson(key, clazz);
	}
	
	/**
	 * 根据key取得List Integer数据
	 * @param key
	 * @return
	 */
	public static List<Integer> getListInteger(String key) {
		return getClient().getListInteger(key);
	}
	
	/**
	 * 根据key取得List Long数据
	 * @param key
	 * @return
	 */
	public static List<Long> getListLong(String key) {
		return getClient().getListLong(key);
	}
	
	/**
	 * 根据key取得List Double数据
	 * @param key
	 * @return
	 */
	public static List<Double> getListDouble(String key) {
		return getClient().getListDouble(key);
	}
	
	/**
	 * 根据key取得List BigDecimal数据
	 * @param key
	 * @return
	 */
	public static List<BigDecimal> getListBigDecimal(String key) {
		return getClient().getListBigDecimal(key);
	}
	
	/**
	 * 根据key取得Set json数据
	 * @param key
	 * @return
	 */
	public static <T> Set<T> getSetJson(String key, Class<T> clazz) {
		return getClient().getSetJson(key, clazz);
	}
	
	/**
	 * 根据key取得Set Integer数据
	 * @param key
	 * @return
	 */
	public static Set<Integer> getSetInteger(String key) {
		return getClient().getSetInteger(key);
	}
	
	/**
	 * 根据key取得Set Long数据
	 * @param key
	 * @return
	 */
	public static Set<Long> getSetLong(String key) {
		return getClient().getSetLong(key);
	}
	
	/**
	 * 根据key取得Set Double数据
	 * @param key
	 * @return
	 */
	public static Set<Double> getSetDouble(String key) {
		return getClient().getSetDouble(key);
	}
	
	/**
	 * 根据key取得Set BigDecimal数据
	 * @param key
	 * @return
	 */
	public static Set<BigDecimal> getSetBigDecimal(String key) {
		return getClient().getSetBigDecimal(key);
	}
	
	/**
	 * 批量删除
	 * @param coll
	 */
	public static void delete(Collection<String> coll) {
		getClient().delete(coll);
	}
	
	/**
	 * 根据key前缀删除，以*作通配符，如：CACHE_KEY:*
	 * @param keyPrefix
	 */
	public static void deleteByPrefix(String keyPrefix) {
		getClient().deleteByPrefix(keyPrefix);
	}
	
	/**
	 * 锁处理
	 * @param key
	 * @param action
	 * @return
	 */
	public static void lock(String key, Runnable action) {
		getClient().lock(key, action);
	}
}
