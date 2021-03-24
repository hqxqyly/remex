package com.github.hqxqyly.remex.boot.remon.cache.properties;

/**
 * 消息中间件properties - 缓存
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RemonCacheProperties {

	/** properties配置文件前缀 */
	public static final String PREFIX = "remon.cache";
	
	/** 默认缓存key前缀 */
	public static String DEFAULT_CACHE_PREFIX = "REMON_CACHE";
	
	/** 缓存key前缀 */
	protected String cachePrefix;

	public String getCachePrefix() {
		return cachePrefix;
	}

	public void setCachePrefix(String cachePrefix) {
		this.cachePrefix = cachePrefix;
	}
}
