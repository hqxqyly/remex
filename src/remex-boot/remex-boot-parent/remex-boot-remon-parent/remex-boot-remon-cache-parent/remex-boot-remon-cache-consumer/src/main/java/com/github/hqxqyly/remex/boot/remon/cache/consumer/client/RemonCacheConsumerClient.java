package com.github.hqxqyly.remex.boot.remon.cache.consumer.client;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.hqxqyly.remex.boot.cache.utils.CacheUtils;
import com.github.hqxqyly.remex.boot.remon.base.consumer.client.IRemonRetryConsumerClient;
import com.github.hqxqyly.remex.boot.remon.base.consumer.client.RemonBaseConsumerClient;
import com.github.hqxqyly.remex.boot.remon.base.consumer.listener.IRemonListener;
import com.github.hqxqyly.remex.boot.remon.base.properties.RemonProperties;
import com.github.hqxqyly.remex.boot.remon.cache.bean.RemonCacheMessageBean;
import com.github.hqxqyly.remex.boot.remon.cache.consumer.properties.RemonCacheConsumerProperties;
import com.github.hqxqyly.remex.boot.remon.cache.properties.RemonCacheProperties;
import com.github.hqxqyly.remex.boot.remon.cache.utils.RemonCacheUtils;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * 消息中间件消费者处理器 - 缓存
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RemonCacheConsumerClient extends RemonBaseConsumerClient {
	
	/** 缓存key前缀 */
	protected String cachePrefix = RemonCacheProperties.DEFAULT_CACHE_PREFIX;
	
	/** 缓存锁key前缀 */
	protected String cacheLockPrefix = RemonCacheConsumerProperties.DEFAULT_CACHE_LOCK_PREFIX;
	
	/** 缓存重试key前缀 */
	protected String cacheRetryPrefix = RemonCacheConsumerProperties.DEFAULT_CACHE_RETRY_PREFIX;
	
	/** 缓存死信息key前缀 */
	protected String cacheDiePrefix = RemonCacheConsumerProperties.DEFAULT_CACHE_DIE_PREFIX;
	
	/** 轮询休眠时间，单位：秒 */
	protected long sleepSeconds = 1;
	
	/** 是否开启重试 */
	protected boolean isRetry = true;
	
	@Autowired
	protected IRemonRetryConsumerClient remonRetryConsumerClient;
	
	/**
	 * 注册接口实现的消费者
	 * @param listener
	 * @param consumerGroup
	 * @param topic
	 */
	@Override
	public void registerImplConsumer(IRemonListener listener, String consumerGroup, String topic) {
		notNull(listener, "listener cannot be null");
		notBlank(topic, "topic cannot be blank");
		
		getLogger().info("remon cache consumer register [consumerGroup : {}] [topic : {}]"
				, defaultString(consumerGroup, RemonProperties.DEFAULT_CONSUMER_GROUP)
				, topic);
		
		//订阅并启动
		subscribeAndStart(listener, consumerGroup, topic);
		
		//给重试处理器注册接口实现的消费者
		remonRetryConsumerClient.registerImplConsumer(listener, consumerGroup, topic);
	}
	
	/**
	 * 订阅并启动
	 * @param listener
	 * @param topic
	 */
	public void subscribeAndStart(IRemonListener listener, String consumerGroup, String topic) {
		consumerGroup = defaultString(consumerGroup, RemonProperties.DEFAULT_CONSUMER_GROUP);
		//缓存key
		String key = RemonCacheUtils.packCacheKey(cachePrefix, consumerGroup, topic);
		//缓存锁key
		String lockKey = RemonCacheUtils.packCacheKey(cacheLockPrefix, consumerGroup, topic);
		//缓存重试key
		String retryKey = RemonCacheUtils.packCacheKey(cacheRetryPrefix, consumerGroup, topic);
		//缓存死信息key
		String dieKey = RemonCacheUtils.packCacheKey(cacheDiePrefix, consumerGroup, topic);
		
		new Thread(() -> {
			CacheUtils.lock(lockKey, () -> {
				while (true) {
					try {
						//查询当前所有缓存key
						Set<String> keyList = CacheUtils.keys(key + ":*");
						Assist.forEach(keyList, curKey -> {
							String message = CacheUtils.get(curKey);
							RemonCacheMessageBean bean = Assist.toJsonBean(message, RemonCacheMessageBean.class);
							fill(bean);
							
							//监听器消费
							boolean result = listener.receive(topic, Assist.toString(bean.getData()), bean.getId());
							if (result) {
								//消费成功
							} else {
								//重试
								if (isRetry) {
									CacheUtils.set(retryKey, Assist.toJson(bean));
								} else {  //不重试，则直接放入死信息队列
									CacheUtils.set(dieKey, Assist.toJson(bean));
								}
							}
							
							//删除原队列记录
							CacheUtils.delete(curKey);
						});
					} catch (Exception e) {
						getLogger().error("remon cache consumer polling error", e);
					}
					
					//轮询休眠
					Assist.sleep(sleepSeconds * 1000);
				}
			});
		}).start();;
	}
	
	
	
	
	
	protected void fill(RemonCacheMessageBean bean) {
		bean.setReceiveTime(getCurTimestamp());
	}
	
	
	
	
	

	public String getCachePrefix() {
		return cachePrefix;
	}

	public void setCachePrefix(String cachePrefix) {
		this.cachePrefix = cachePrefix;
	}

	public String getCacheLockPrefix() {
		return cacheLockPrefix;
	}

	public void setCacheLockPrefix(String cacheLockPrefix) {
		this.cacheLockPrefix = cacheLockPrefix;
	}

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

	public long getSleepSeconds() {
		return sleepSeconds;
	}

	public void setSleepSeconds(long sleepSeconds) {
		this.sleepSeconds = sleepSeconds;
	}

	public boolean isRetry() {
		return isRetry;
	}

	public void setRetry(boolean isRetry) {
		this.isRetry = isRetry;
	}
}
