package com.github.hqxqyly.remex.boot.rocketmq.consumer.client;

/**
 * rocketmq消费者
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IRocketMqConsumer {

	/**
	 * 接收
	 * @param message
	 * @param msgId
	 * @return
	 */
	boolean receive(String message, String msgId);
}
