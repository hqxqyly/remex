package com.github.hqxqyly.remex.solution.remon.cache.utils;

import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.solution.cache.utils.CacheUtils;

/**
 * 消息中间件工具类 - 缓存
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RemonCacheUtils {

	/**
	 * 拼接缓存key
	 * @param cachePrefix 缓存前缀
	 * @param group 组名
	 * @param topic 主题
	 * @return
	 */
	public static String packCacheKey(String cachePrefix, String group, String topic) {
		Assist.notBlank(cachePrefix, "cachePrefix cannot be blank");
		Assist.notBlank(group, "group cannot be blank");
		Assist.notBlank(topic, "topic cannot be blank");
		return CacheUtils.packCacheKey(cachePrefix, group, topic);
	}
	
	/**
	 * 拼接缓存key
	 * @param cachePrefix 缓存前缀
	 * @param group 组名
	 * @param topic 主题
	 * @param id ID
	 * @return
	 */
	public static String packCacheKey(String cachePrefix, String group, String topic, String id) {
		Assist.notBlank(id, "id cannot be blank");
		return CacheUtils.packCacheKey(packCacheKey(cachePrefix, group, topic), id);
	}
}
