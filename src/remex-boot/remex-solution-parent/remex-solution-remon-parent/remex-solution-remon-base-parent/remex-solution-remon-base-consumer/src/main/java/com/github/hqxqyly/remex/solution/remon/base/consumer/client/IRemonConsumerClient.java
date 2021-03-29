package com.github.hqxqyly.remex.solution.remon.base.consumer.client;

import com.github.hqxqyly.remex.solution.remon.base.consumer.listener.IRemonListener;

/**
 * 消息中间件消费者处理器
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IRemonConsumerClient {
	
	/**
	 * 注册接口实现的消费者
	 * @param listener
	 * @param consumerGroup
	 * @param topic
	 */
	default void registerImplConsumer(IRemonListener listener, String consumerGroup, String topic) {
		
	}
}
