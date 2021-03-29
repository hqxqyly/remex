package com.github.hqxqyly.remex.solution.remon.cache.consumer.client;

import java.sql.Timestamp;
import java.util.Set;

import com.github.hqxqyly.remex.boot.interfaces.assist.IAssistClient;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.boot.utils.DateUtils;
import com.github.hqxqyly.remex.solution.cache.utils.CacheUtils;
import com.github.hqxqyly.remex.solution.remon.base.consumer.client.IRemonRetryConsumerClient;
import com.github.hqxqyly.remex.solution.remon.base.consumer.listener.IRemonListener;
import com.github.hqxqyly.remex.solution.remon.base.properties.RemonProperties;
import com.github.hqxqyly.remex.solution.remon.cache.bean.RemonCacheMessageBean;
import com.github.hqxqyly.remex.solution.remon.cache.consumer.properties.RemonCacheConsumerProperties;
import com.github.hqxqyly.remex.solution.remon.cache.utils.RemonCacheUtils;

/**
 * 消息中间件重试消费者处理器 - 缓存
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RemonCacheRetryConsumerClient implements IRemonRetryConsumerClient, IAssistClient {
	
	/** 缓存重试key前缀 */
	protected String cacheRetryPrefix = RemonCacheConsumerProperties.DEFAULT_CACHE_RETRY_PREFIX;
	
	/** 缓存重试key前缀 */
	protected String cacheRetryLockPrefix = RemonCacheConsumerProperties.DEFAULT_CACHE_RETRY_LOCK_PREFIX;
	
	/** 缓存死信息key前缀 */
	protected String cacheDiePrefix = RemonCacheConsumerProperties.DEFAULT_CACHE_DIE_PREFIX;
	
	/** 重试轮询休眠时间，单位：秒 */
	protected long retrySleepSeconds = 60;
	
	/** 重试最大次数 */
	protected int retryMaxCount = 10;
	
	/**
	 * 添加接口实现的消费者
	 * @param listener
	 * @param consumerGroup
	 * @param topic
	 */
	@Override
	public void registerImplConsumer(IRemonListener listener, String consumerGroup, String topic) {
		//订阅并启动
		subscribeAndStart(listener, consumerGroup, topic);
	}
	
	/**
	 * 订阅并启动逻辑处理
	 * @param listener
	 * @param topic
	 */
	public void subscribeAndStart(IRemonListener listener, String consumerGroup, String topic) {
		notNull(listener, "listener cannot be null");
		notBlank(topic, "topic cannot be blank");
		
		consumerGroup = defaultString(consumerGroup, RemonProperties.DEFAULT_CONSUMER_GROUP);
		//缓存重试key
		String key = RemonCacheUtils.packCacheKey(cacheRetryPrefix, consumerGroup, topic);
		//缓存重试锁key
		String lockKey = RemonCacheUtils.packCacheKey(cacheRetryLockPrefix, consumerGroup, topic);
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
							int curRetryCount = Assist.defaultInt(bean.getRetryCount()) + 1;
							Timestamp lastRetryTime = bean.getLastRetryTime();
							fill(bean, curRetryCount);
							
							//监听器消费
							boolean result = listener.receive(topic, Assist.toString(bean.getData()), bean.getId());
							if (result) {
								//消费成功，删除队列记录
								CacheUtils.delete(curKey);
							} else {
								//未超过最大重试次数，则继续重试
								if (curRetryCount < retryMaxCount) {
									if (lastRetryTime == null) {
										lastRetryTime = getCurTimestamp();
										bean.setLastRetryTime(lastRetryTime);
										CacheUtils.set(curKey, Assist.toJson(bean));
									} else {
										//重试时间已解冻，已解冻则做重试，未解冻则跳过等待下一次轮询
										if (isUnfreeze(curRetryCount, lastRetryTime)) {
											CacheUtils.set(curKey, Assist.toJson(bean));
										}
									}
								} else {  //超过最大重试次数，则放入到死信息队列
									CacheUtils.set(dieKey, Assist.toJson(bean));
									CacheUtils.delete(curKey);
								}
							}
						});
					} catch (Exception e) {
						getLogger().error("remon cache consumer polling error", e);
					}
					
					//轮询休眠
					Assist.sleep(retrySleepSeconds * 1000);
				}
			});
		}).start();;
	}
	
	
	
	
	protected void fill(RemonCacheMessageBean bean, int retryCount) {
		bean.setLastRetryTime(getCurTimestamp());
		bean.setRetryCount(retryCount);
	}
	
	/**
	 * 是否重试时间已解冻
	 * @param retryCount
	 * @param lastRetryTime
	 * @return
	 */
	protected boolean isUnfreeze(int retryCount, Timestamp lastRetryTime) {
		if (retryCount <= 1) return true;
		
		int num = new Double(Math.pow(retryCount - 1, 2)).intValue();
		int addSeconds = (int) (retrySleepSeconds * num);
		lastRetryTime = DateUtils.timestampAddSecond(lastRetryTime, addSeconds);
		return DateUtils.ge(getCurTimestamp(), lastRetryTime);
	}
	
	
	
	
	

	public String getCacheRetryPrefix() {
		return cacheRetryPrefix;
	}

	public void setCacheRetryPrefix(String cacheRetryPrefix) {
		this.cacheRetryPrefix = cacheRetryPrefix;
	}

	public String getCacheRetryLockPrefix() {
		return cacheRetryLockPrefix;
	}

	public void setCacheRetryLockPrefix(String cacheRetryLockPrefix) {
		this.cacheRetryLockPrefix = cacheRetryLockPrefix;
	}

	public String getCacheDiePrefix() {
		return cacheDiePrefix;
	}

	public void setCacheDiePrefix(String cacheDiePrefix) {
		this.cacheDiePrefix = cacheDiePrefix;
	}

	public long getRetrySleepSeconds() {
		return retrySleepSeconds;
	}

	public void setRetrySleepSeconds(long retrySleepSeconds) {
		this.retrySleepSeconds = retrySleepSeconds;
	}

	public int getRetryMaxCount() {
		return retryMaxCount;
	}

	public void setRetryMaxCount(int retryMaxCount) {
		this.retryMaxCount = retryMaxCount;
	}
}
