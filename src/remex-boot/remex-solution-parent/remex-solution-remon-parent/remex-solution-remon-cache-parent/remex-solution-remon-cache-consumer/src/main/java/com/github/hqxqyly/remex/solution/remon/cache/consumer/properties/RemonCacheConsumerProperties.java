package com.github.hqxqyly.remex.solution.remon.cache.consumer.properties;

import com.github.hqxqyly.remex.solution.remon.cache.properties.RemonCacheProperties;

/**
 * 消息中间件properties - 缓存 - 消费者
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RemonCacheConsumerProperties {
	
	/** properties配置文件前缀 */
	public static final String PREFIX = RemonCacheProperties.PREFIX + "consumer";

	/** 默认缓存锁key后缀 */
	public static String DEFAULT_CACHE_LOCK_PREFIX = RemonCacheProperties.DEFAULT_CACHE_PREFIX + "_LOCK";
	
	/** 默认缓存重试key后缀 */
	public static String DEFAULT_CACHE_RETRY_PREFIX = RemonCacheProperties.DEFAULT_CACHE_PREFIX + "_RETRY";
	
	/** 默认缓存重试锁key后缀 */
	public static String DEFAULT_CACHE_RETRY_LOCK_PREFIX = RemonCacheProperties.DEFAULT_CACHE_PREFIX + "_RETRY_LOCK";
	
	/** 默认缓存死信息key后缀 */
	public static String DEFAULT_CACHE_DIE_PREFIX = RemonCacheProperties.DEFAULT_CACHE_PREFIX + "_DIE";
	
	/** 缓存锁key前缀 */
	protected String cacheLockPrefix;
	
	/** 缓存重试key前缀 */
	protected String cacheRetryPrefix;
	
	/** 缓存重试锁key前缀 */
	protected String cacheRetryLockPrefix;
	
	/** 缓存死信息key前缀 */
	protected String cacheDiePrefix;
	
	/** 轮询休眠时间，单位：秒 */
	protected Long sleepSeconds;
	
	/** 是否开启重试 */
	protected Boolean isRetry;
	
	/** 重试轮询休眠时间，单位：秒 */
	protected Long retrySleepSeconds;
	
	/** 重试最大次数 */
	protected Integer retryMaxCount;

	public String getCacheRetryPrefix() {
		return cacheRetryPrefix;
	}

	public void setCacheRetryPrefix(String cacheRetryPrefix) {
		this.cacheRetryPrefix = cacheRetryPrefix;
	}

	public String getCacheDiePrefix() {
		return cacheDiePrefix;
	}

	public void setCacheDiePrefix(String cacheDiePrefix) {
		this.cacheDiePrefix = cacheDiePrefix;
	}

	public String getCacheLockPrefix() {
		return cacheLockPrefix;
	}

	public void setCacheLockPrefix(String cacheLockPrefix) {
		this.cacheLockPrefix = cacheLockPrefix;
	}

	public Long getSleepSeconds() {
		return sleepSeconds;
	}

	public void setSleepSeconds(Long sleepSeconds) {
		this.sleepSeconds = sleepSeconds;
	}

	public Boolean getIsRetry() {
		return isRetry;
	}

	public void setIsRetry(Boolean isRetry) {
		this.isRetry = isRetry;
	}

	public Long getRetrySleepSeconds() {
		return retrySleepSeconds;
	}

	public void setRetrySleepSeconds(Long retrySleepSeconds) {
		this.retrySleepSeconds = retrySleepSeconds;
	}

	public Integer getRetryMaxCount() {
		return retryMaxCount;
	}

	public void setRetryMaxCount(Integer retryMaxCount) {
		this.retryMaxCount = retryMaxCount;
	}

	public String getCacheRetryLockPrefix() {
		return cacheRetryLockPrefix;
	}

	public void setCacheRetryLockPrefix(String cacheRetryLockPrefix) {
		this.cacheRetryLockPrefix = cacheRetryLockPrefix;
	}
}
