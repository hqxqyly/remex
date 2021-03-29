package com.github.hqxqyly.remex.boot.redis.utils;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.github.hqxqyly.remex.boot.redis.client.RedisClient;
import com.github.hqxqyly.remex.boot.utils.ApplicationContextUtils;
import com.github.hqxqyly.remex.solution.cache.common.utils.CacheOutUtils;

/**
 * redis工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RedisUtils extends CacheOutUtils {
	
	/**
	 * 取得redis缓存处理器
	 * @return
	 */
	public static RedisClient getRedisClient() {
		return ApplicationContextUtils.getBean(RedisClient.class);
	}

	/**
	 * 取得StringRedisTemplate
	 * @return
	 */
	public static StringRedisTemplate getRedisTemplate() {
		return getRedisClient().getRedisTemplate();
	}
	
	/**
	 * 取得opsForValue
	 * @return
	 */
	public static ValueOperations<String, String> getOpsForValue() {
		return getRedisClient().getOpsForValue();
	}
	
	/**
	 * 取得opsForSet
	 * @return
	 */
	public static SetOperations<String, String> getOpsForSet() {
		return getRedisClient().getOpsForSet();
	}
	
	/**
	 * 取得opsForList
	 * @return
	 */
	public static ListOperations<String, String> getOpsForList() {
		return getRedisClient().getOpsForList();
	}
}
