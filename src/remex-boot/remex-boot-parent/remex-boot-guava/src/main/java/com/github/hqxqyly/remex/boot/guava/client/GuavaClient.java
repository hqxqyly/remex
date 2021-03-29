package com.github.hqxqyly.remex.boot.guava.client;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.boot.utils.LockUtils;
import com.github.hqxqyly.remex.solution.cache.common.client.ICacheOutClient;

/**
 * <pre>
 * guava处理器
 * 保存数据时使用initCacheContainer获取缓存容器，需要做初始化；
 * 其余操作使用getCache获取缓存容器，在没数据时，缓存容器也是不存在的
 * </pre>
 * 
 * @author Qiaoxin.Hong
 *
 */
public class GuavaClient implements ICacheOutClient {
	
	/** 缓存容器 */
	protected Map<String, Cache<Object, Object>> cacheContainerMap = new ConcurrentHashMap<>();
	
	/**
	 * 初始化并取得默认的无超时时间的缓存容器
	 * @param key
	 * @param timeout
	 * @param unit
	 * @return
	 */
	public Cache<Object, Object> initCacheContainer(String key) {
		return initCacheContainer(key, null, null);
	}
	
	/**
	 * 初始化并取得缓存容器
	 * @param key
	 * @param timeout
	 * @param unit
	 * @return
	 */
	public Cache<Object, Object> initCacheContainer(String key, Long timeout, TimeUnit unit) {
		Assist.notBlank(key, "key cannot be blank");
		
		Cache<Object, Object> cache = cacheContainerMap.get(key);
		if (cache == null) {
			synchronized (this) {
				cache = cacheContainerMap.get(key);
				if (cache == null) {
					cache = isUseTimeout(timeout) ? CacheBuilder.newBuilder().expireAfterWrite(timeout, unit).build() 
							: CacheBuilder.newBuilder().build();
					cacheContainerMap.put(key, cache);
				}
			}
		}
		return cache;
	}
	
	/**
	 * 取得缓存容器
	 * @param key
	 * @return
	 */
	public Cache<Object, Object> getCache(String key) {
		Assist.notBlank(key, "key cannot be blank");
		return cacheContainerMap.get(key);
	}
	
	/**
	 * 取得缓存容器，并执行action返回其结果集
	 * @param key
	 * @param action
	 * @return
	 */
	public <T> T getCacheFn(String key, Function<Cache<Object, Object>, T> action) {
		return Assist.ifNotNullFn(getCache(key), action);
	}
	
	/**
	 * 取得缓存容器，并执行action
	 * @param key
	 * @param action
	 * @return
	 */
	public void getCacheRun(String key, Consumer<Cache<Object, Object>> action) {
		Assist.ifNotNull(getCache(key), action);
	}
	
	
	

	/**
	 * 根据条件获取所有key，可以以*作通配符，如：CACHE_KEY:*
	 * @param key
	 * @return
	 */
	@Override
	public Set<String> keys(String key) {
		if (isBlank(key)) return null;
		
		if (key.lastIndexOf("*") != 0)
			key = key.substring(0, key.length() - 1);
		
		Set<String> result = new HashSet<>();
		
		for (String keyItem : cacheContainerMap.keySet()) {
			if (keyItem.startsWith(key))
				result.add(keyItem);
		}
		
		return result;
	}

	/**
	 * 设置数据
	 * @param key
	 * @param value
	 * @param timeout
	 * @param unit
	 */
	@Override
	public void set(String key, String value, Long timeout, TimeUnit unit) {
		if (isBlank(key)) return;
		initCacheContainer(key, timeout, unit).put(key, value);
	}

	/**
	 * 设置数据到List，加到List后面
	 * @param key
	 * @param value
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setToList(String key, String value) {
		if (isBlank(key)) return;
		Cache<Object, Object> cache = initCacheContainer(key);
		List<String> list = Assist.defaultList((List<String>) cache.getIfPresent(key));
		list.add(value);
	}

	/**
	 * 设置数据到Set
	 * @param key
	 * @param value
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setToSet(String key, String value) {
		if (isBlank(key)) return;
		Cache<Object, Object> cache = initCacheContainer(key);
		Set<String> list = Assist.defaultSet((Set<String>) cache.getIfPresent(key));
		list.add(value);
	}

	/**
	 * 根据key取得数据
	 * @param key
	 * @return
	 */
	@Override
	public String get(String key) {
		if (isBlank(key)) return null;
		return getCacheFn(key, cache -> Assist.toString(cache.getIfPresent(key)));
	}

	/**
	 * 根据key取得List
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getList(String key) {
		if (isBlank(key)) return null;
		return (List<String>) getCacheFn(key, cache -> cache.getIfPresent(key));
	}

	/**
	 * 根据key取得Set
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<String> getSet(String key) {
		if (isBlank(key)) return null;
		return (Set<String>) getCacheFn(key, cache -> cache.getIfPresent(key));
	}

	/**
	 * 根据key删除
	 * @param key
	 */
	@Override
	public void delete(String key) {
		if (isBlank(key)) return;
		getCacheRun(key, cache -> {
			//失效缓存
			cache.invalidate(key);
			//删除缓存容器
			cacheContainerMap.remove(key);
		});
	}

	/**
	 * 锁处理
	 * @param <T>
	 * @param key
	 * @param action
	 * @return
	 */
	@Override
	public <T> T lock(String key, Supplier<T> action) {
		return LockUtils.lock(key, action);
	}
}
