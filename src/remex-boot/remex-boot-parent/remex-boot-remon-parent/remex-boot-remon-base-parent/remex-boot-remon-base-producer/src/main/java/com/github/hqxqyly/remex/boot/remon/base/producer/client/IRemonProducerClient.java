package com.github.hqxqyly.remex.boot.remon.base.producer.client;

import com.github.hqxqyly.remex.boot.remon.base.producer.bean.RemonSendResult;

/**
 * 消息中间件生产者处理器
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IRemonProducerClient {
	
	/**
	 * 注册接口实现的生产者
	 * @param producerGroup
	 * @param topic
	 */
	void registerImplProducer(String producerGroup, String topic);

	/**
	 * 发送
	 * @param producerGroup 生产组名
	 * @param topic 主题
	 * @param message 消息
	 * @return
	 */
	RemonSendResult send(String producerGroup, String topic, String message);
}
