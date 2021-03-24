package com.github.hqxqyly.remex.boot.rocketmq.producer.client;

import org.apache.rocketmq.client.producer.SendResult;

import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * rocketmq生产者
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IRocketMqProducer {

	/**
	 * 发送
	 * @param message
	 * @return
	 */
	SendResult send(String message);
	
	/**
	 * 发送json
	 * @param obj
	 * @return
	 */
	default SendResult sendJson(Object obj) {
		String message = Assist.toJsonDefault(obj);
		return send(message);
	}
}
