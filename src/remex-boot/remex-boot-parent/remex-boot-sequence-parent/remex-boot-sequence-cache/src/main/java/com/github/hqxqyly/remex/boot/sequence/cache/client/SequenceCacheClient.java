package com.github.hqxqyly.remex.boot.sequence.cache.client;

import java.sql.Timestamp;

import com.github.hqxqyly.remex.boot.cache.utils.CacheUtils;
import com.github.hqxqyly.remex.boot.sequence.bean.SequenceNextSeqDbBean;
import com.github.hqxqyly.remex.boot.sequence.client.SequenceBaseClient;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * 序列处理器 - 缓存
 * 
 * @author Qiaoxin.Hong
 *
 */
public class SequenceCacheClient extends SequenceBaseClient {
	
	/** 序列缓存数据key前缀 */
	protected String cacheKeyPrefixData = "REMEX_SEQUENCE_DATA";

	/**
	 * 获取下一个序列逻辑处理，并不会直接修改数据
	 * @param key 序列key
	 * @return
	 */
	@Override
	public SequenceNextSeqDbBean nextSeqDbInfo(String key) {
		String cacheKey = packCacheKey(key);

		Long seq = CacheUtils.getLong(cacheKey);
		if (seq == null) {
			seq = 0L;
			CacheUtils.set(cacheKey, seq);
		}
		
		//组装下一下序列信息
		SequenceNextSeqDbBean bo = new SequenceNextSeqDbBean();
		bo.setKey(key);
		bo.setPrefix(key);
		bo.setCurSeq(seq);
		
		return bo;
	}
	
	/**
	 * 保存序列到db
	 * @param bo
	 * @param seq
	 */
	@Override
	public void saveDbSeq(SequenceNextSeqDbBean bo, Long seq, Timestamp curDate) {
		String cacheKey = packCacheKey(bo.getKey());
		CacheUtils.set(cacheKey, seq);
	}
	
	
	
	
	/**
	 * 拼接缓存key
	 * @param key
	 * @return
	 */
	protected String packCacheKey(String key) {
		Assist.notBlank(key, "key cannot be blank");
		
		String cacheKey = CacheUtils.packCacheKey(cacheKeyPrefixData, key);
		
		//拼接序列日期前缀
		String dateSeqPrefix = packSeqDatePrefix(null, null);
		if (Assist.isNotBlank(dateSeqPrefix))
			cacheKey = CacheUtils.packCacheKey(cacheKey, dateSeqPrefix);
		
		return cacheKey;
	}
	
	
	
	public void setCacheKeyPrefixData(String cacheKeyPrefixData) {
		this.cacheKeyPrefixData = cacheKeyPrefixData;
	}
	
	public String getCacheKeyPrefixData() {
		return cacheKeyPrefixData;
	}
}
