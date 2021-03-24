package com.github.hqxqyly.remex.boot.remon.rocketmq.consumer.customer;

import com.github.hqxqyly.remex.boot.remon.base.consumer.listener.IRemonListener;
import com.github.hqxqyly.remex.boot.rocketmq.consumer.client.RocketMqConsumer;

/**
 * 消息中间件消费者 - rocketmq
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RemonRocketMqConsumer extends RocketMqConsumer {
	
	/** 消息中间件监听器 */
	protected IRemonListener listener;
	
	public RemonRocketMqConsumer() {
		
	}
	
	public RemonRocketMqConsumer(IRemonListener listener) {
		this.listener = listener;
	}
	

	/**
	 * 接收
	 * @param message
	 * @param msgId
	 * @return
	 */
	@Override
	public boolean receive(String message, String msgId) {
		notNull(listener, "remon listener cannot be null");
		return listener.receive(topic, message, msgId);
	}
}
