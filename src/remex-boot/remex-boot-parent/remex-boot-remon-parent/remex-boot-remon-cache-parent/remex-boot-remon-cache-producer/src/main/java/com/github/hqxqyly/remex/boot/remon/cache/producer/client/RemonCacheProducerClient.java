package com.github.hqxqyly.remex.boot.remon.cache.producer.client;

import java.util.ArrayList;
import java.util.List;

import com.github.hqxqyly.remex.boot.cache.utils.CacheUtils;
import com.github.hqxqyly.remex.boot.remon.base.producer.bean.RemonSendResult;
import com.github.hqxqyly.remex.boot.remon.base.producer.client.RemonBaseProducerClient;
import com.github.hqxqyly.remex.boot.remon.base.properties.RemonProperties;
import com.github.hqxqyly.remex.boot.remon.cache.bean.RemonCacheMessageBean;
import com.github.hqxqyly.remex.boot.remon.cache.properties.RemonCacheProperties;
import com.github.hqxqyly.remex.boot.remon.cache.utils.RemonCacheUtils;

/**
 * 消息中间件生产者处理器 - 缓存
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RemonCacheProducerClient extends RemonBaseProducerClient {
	
	/** 缓存key前缀 */
	protected String cachePrefix = RemonCacheProperties.DEFAULT_CACHE_PREFIX;
	
	/** 消费组名列表 */
	protected List<String> consumerGroupList = new ArrayList<>();
	
	/**
	 * 注册接口实现的生产者
	 * @param producerGroup
	 * @param topic
	 */
	@Override
	public void registerImplProducer(String producerGroup, String topic) {
		notBlank(topic, "topic cannot be blank");

		getLogger().info("remon cache producer register [producerGroup : {}] [topic : {}]"
				, defaultString(producerGroup, RemonProperties.DEFAULT_PRODUCER_GROUP)
				, topic);
	}

	/**
	 * 发送逻辑处理
	 * @param producerGroup 生产组名
	 * @param topic 主题
	 * @param message 消息
	 * @return
	 */
	@Override
	public RemonSendResult sendHandle(String producerGroup, String topic, String message) {
		notBlank(topic, "topic cannot be blank");
		
		message = defaultString(message);
		
		//生成一个随机ID
		String id = newId();
		
		RemonCacheMessageBean bean = new RemonCacheMessageBean();
		fill(bean, id, message, producerGroup);
		
		//默认消费组
		if (isEmpty(consumerGroupList))
			consumerGroupList.add(RemonProperties.DEFAULT_CONSUMER_GROUP);
		
		for (String consumerGroup : consumerGroupList) {
			if (isBlank(consumerGroup)) continue;
			String key = RemonCacheUtils.packCacheKey(cachePrefix, consumerGroup, topic, id);
			CacheUtils.setJson(key, bean);
		}
		
		return new RemonSendResult();
	}
	
	
	
	
	protected void fill(RemonCacheMessageBean bean, String id, String message, String producerGroup) {
		bean.setId(id);
		bean.setSendTime(getCurTimestamp());
		bean.setData(message);
		bean.setProducerGroup(producerGroup);
	}
	
	
	
	
	public void setCachePrefix(String cachePrefix) {
		this.cachePrefix = cachePrefix;
	}
	
	public String getCachePrefix() {
		return cachePrefix;
	}
	
	public void setConsumerGroupList(List<String> consumerGroupList) {
		this.consumerGroupList = consumerGroupList;
	}
	
	public List<String> getConsumerGroupList() {
		return consumerGroupList;
	}
}
