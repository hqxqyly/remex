package com.github.hqxqyly.remex.boot.remon.base.consumer.client;

import com.github.hqxqyly.remex.boot.remon.base.consumer.listener.IRemonListener;

/**
 * 消息中间件重试消费者处理器
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IRemonRetryConsumerClient {

	/**
	 * 注册接口实现的消费者
	 * @param listener
	 * @param consumerGroup
	 * @param topic
	 */
	void registerImplConsumer(IRemonListener listener, String consumerGroup, String topic);
}
