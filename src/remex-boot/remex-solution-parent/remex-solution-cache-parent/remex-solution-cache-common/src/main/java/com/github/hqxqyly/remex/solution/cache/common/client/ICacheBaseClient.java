package com.github.hqxqyly.remex.solution.cache.common.client;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import com.github.hqxqyly.remex.boot.interfaces.assist.IAssist;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * <pre>
 * 缓存基础处理器
 * 统一说明：
 * 	timeout：null或 <= 0则表示不超时
 *  value：所有值都以字符串格式存储，null不做处理
 *  方法调用时，如果key为无值，则不做操作，而非抛出异常
 * </pre>
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface ICacheBaseClient extends IAssist {
	
	/** 默认超时时间，-1 */
	public final static long DEFAULT_MILLISECOND = -1;
	
	/**
	 * 是否使用超时
	 * @return
	 */
	default boolean isUseTimeout(Long timeout) {
		return timeout != null && timeout > 0;
	}
	
	
	
	
	
	/**
	 * 根据条件获取所有key，可以以*作通配符，如：CACHE_KEY:*
	 * @param key
	 * @return
	 */
	Set<String> keys(String key);

	/**
	 * 设置数据
	 * @param key
	 * @param value
	 * @param timeout 超时时间，null或 <= 0则表示不超时
	 * @param unit
	 */
	void set(String key, String value, Long timeout, TimeUnit unit);
	
	/**
	 * 设置数据到List，加到List后面
	 * @param key
	 * @param value
	 */
	void setToList(String key, String value);
	
	/**
	 * 设置数据到Set
	 * @param key
	 * @param value
	 */
	void setToSet(String key, String value);
	
	/**
	 * 根据key取得数据
	 * @param key
	 * @return
	 */
	String get(String key);
	
	/**
	 * 根据key取得List
	 * @param key
	 * @return
	 */
	List<String> getList(String key);
	
	/**
	 * 根据key取得Set
	 * @param key
	 * @return
	 */
	Set<String> getSet(String key);
	
	/**
	 * 根据key删除
	 * @param key
	 */
	void delete(String key);
	
	/**
	 * 锁处理
	 * @param <T>
	 * @param key
	 * @param action
	 * @return
	 */
	<T> T lock(String key, Supplier<T> action);
	
	
	
	
	/**
	 * 是否存在key
	 * @param key
	 * @return
	 */
	default boolean existKey(String key) {
		return Assist.isNotEmpty(keys(key));
	}
	
	/**
	 * 设置数据
	 * @param key
	 * @param value
	 * @param timeout
	 * @param unit
	 */
	default void set(String key, Object value, Long timeout, TimeUnit unit) {
		set(key, Assist.defaultStringNull(value), timeout, unit);
	}
	
	/**
	 * 设置json数据
	 * @param key
	 * @param value
	 * @param timeout
	 * @param unit
	 */
	default void setJson(String key, Object value, Long timeout, TimeUnit unit) {
		set(key, Assist.toJson(value), timeout, unit);
	}
	
	/**
	 * 设置数据
	 * @param key
	 * @param value
	 */
	default void set(String key, Object value) {
		set(key, value, null, null);
	}
	
	/**
	 * 设置json数据
	 * @param key
	 * @param value
	 */
	default void setJson(String key, Object value) {
		setJson(key, value, null, null);
	}
	
	/**
	 * 设置数据
	 * @param key
	 * @param value
	 * @param millisecond 超时时间，单位：毫秒
	 */
	default void set(String key, Object value, long millisecond) {
		set(key, value, millisecond, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * 设置json数据
	 * @param key
	 * @param value
	 * @param millisecond 超时时间，单位：毫秒
	 */
	default void setJson(String key, Object value, long millisecond) {
		setJson(key, value, millisecond, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * <pre>
	 * 设置数据到List，加到List后面
	 * value类型：单对象、Stream、Collection、数组、Map（只取value）
	 * </pre>
	 * @param key
	 * @param value
	 */
	default void setToList(String key, Object value) {
		if (isBlank(key)) return;
		Assist.tryEach(value, item -> {
			setToList(key, Assist.defaultStringNull(item));
		});
	}
	
	/**
	 * <pre>
	 * 设置json数据到List，加到List后面
	 * value类型：单对象、Stream、Collection、数组、Map（只取value）
	 * </pre>
	 * @param key
	 * @param value
	 */
	default void setToListJson(String key, Object value) {
		if (isBlank(key)) return;
		Assist.tryEach(value, item -> {
			setToList(key, Assist.toJson(item));
		});
	}
	
	/**
	 * <pre>
	 * 设置数据到Set
	 * value类型：单对象、Stream、Collection、数组、Map（只取value）
	 * </pre>
	 * @param key
	 * @param value
	 */
	default void setToSet(String key, Object value) {
		if (isBlank(key)) return;
		Assist.tryEach(value, item -> {
			setToSet(key, Assist.defaultStringNull(item));
		});
	}
	
	/**
	 * <pre>
	 * 设置json数据到Set
	 * value类型：单对象、Stream、Collection、数组、Map（只取value）
	 * </pre>
	 * @param key
	 * @param value
	 */
	default void setToSetJson(String key, Object value) {
		if (isBlank(key)) return;
		Assist.tryEach(value, item -> {
			setToSet(key, Assist.toJson(item));
		});
	}
	
	/**
	 * 根据key取得Integer数据
	 * @param key
	 * @return
	 */
	default Integer getInteger(String key) {
		return Assist.toInteger(get(key));
	}
	
	/**
	 * 根据key取得Long数据
	 * @param key
	 * @return
	 */
	default Long getLong(String key) {
		return Assist.toLong(get(key));
	}
	
	/**
	 * 根据key取得Double数据
	 * @param key
	 * @return
	 */
	default Double getDouble(String key) {
		return Assist.toDouble(get(key));
	}
	
	/**
	 * 根据key取得BigDecimal数据
	 * @param key
	 * @return
	 */
	default BigDecimal getBigDecimal(String key) {
		return Assist.toBigDecimal(get(key));
	}
	
	/**
	 * 根据key取得json数据
	 * @param key
	 * @return
	 */
	default <T> T getJson(String key, Class<T> clazz) {
		return Assist.toJsonBean(get(key), clazz);
	}
	
	/**
	 * 根据key取得List json数据
	 * @param key
	 * @return
	 */
	default <T> List<T> getListJson(String key, Class<T> clazz) {
		return Assist.toJsonBeanList(getList(key), clazz);
	}
	
	/**
	 * 根据key取得List Integer数据
	 * @param key
	 * @return
	 */
	default List<Integer> getListInteger(String key) {
		return Assist.forEachToList(getList(key), Assist::toInteger);
	}
	
	/**
	 * 根据key取得List Long数据
	 * @param key
	 * @return
	 */
	default List<Long> getListLong(String key) {
		return Assist.forEachToList(getList(key), Assist::toLong);
	}
	
	/**
	 * 根据key取得List Double数据
	 * @param key
	 * @return
	 */
	default List<Double> getListDouble(String key) {
		return Assist.forEachToList(getList(key), Assist::toDouble);
	}
	
	/**
	 * 根据key取得List BigDecimal数据
	 * @param key
	 * @return
	 */
	default List<BigDecimal> getListBigDecimal(String key) {
		return Assist.forEachToList(getList(key), Assist::toBigDecimal);
	}
	
	/**
	 * 根据key取得Set json数据
	 * @param key
	 * @return
	 */
	default <T> Set<T> getSetJson(String key, Class<T> clazz) {
		return Assist.toJsonBeanSet(getSet(key), clazz);
	}
	
	/**
	 * 根据key取得Set Integer数据
	 * @param key
	 * @return
	 */
	default Set<Integer> getSetInteger(String key) {
		return Assist.forEachToSet(getSet(key), Assist::toInteger);
	}
	
	/**
	 * 根据key取得Set Long数据
	 * @param key
	 * @return
	 */
	default Set<Long> getSetLong(String key) {
		return Assist.forEachToSet(getSet(key), Assist::toLong);
	}
	
	/**
	 * 根据key取得Set Double数据
	 * @param key
	 * @return
	 */
	default Set<Double> getSetDouble(String key) {
		return Assist.forEachToSet(getSet(key), Assist::toDouble);
	}
	
	/**
	 * 根据key取得Set BigDecimal数据
	 * @param key
	 * @return
	 */
	default Set<BigDecimal> getSetBigDecimal(String key) {
		return Assist.forEachToSet(getSet(key), Assist::toBigDecimal);
	}
	
	/**
	 * 批量删除
	 * @param coll
	 */
	default void delete(Collection<String> coll) {
		Assist.forEach(coll, this::delete);
	}
	
	/**
	 * 根据key前缀删除，以*作通配符，如：CACHE_KEY:*
	 * @param keyPrefix
	 */
	default void deleteByPrefix(String keyPrefix) {
		if (Assist.isBlank(keyPrefix)) return;
		Set<String> keys = keys(keyPrefix);
		delete(keys);
	}
	
	/**
	 * 锁处理
	 * @param key
	 * @param action
	 * @return
	 */
	default void lock(String key, Runnable action) {
		lock(key, () -> {
			action.run();
			return null;
		});
	}
}
