package com.github.hqxqyly.remex.boot.sequence.cache.properties;

import com.github.hqxqyly.remex.boot.sequence.properties.SequenceProperties;

/**
 * 序列properties - 缓存
 * 
 * @author Qiaoxin.Hong
 *
 */
public class SequenceCacheProperties extends SequenceProperties {

	/** 序列缓存数据key前缀 */
	protected String cacheKeyPrefixData;
	
	public void setCacheKeyPrefixData(String cacheKeyPrefixData) {
		this.cacheKeyPrefixData = cacheKeyPrefixData;
	}
	
	public String getCacheKeyPrefixData() {
		return cacheKeyPrefixData;
	}
}
