package com.github.hqxqyly.remex.boot.remon.base.producer.customer;

import com.github.hqxqyly.remex.boot.interfaces.assist.IAssistClient;
import com.github.hqxqyly.remex.boot.remon.base.producer.bean.RemonSendResult;
import com.github.hqxqyly.remex.boot.remon.base.producer.client.IRemonProducerClient;
import com.github.hqxqyly.remex.boot.utils.ApplicationContextUtils;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * 消息中间件生产者
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IRemonProducer extends IAssistClient {
	
	/** 默认生产组名 */
	public static String DEFAULT_PRODUCER_GROUP = "DEFAULT_PRODUCER_GROUP";
	
	/**
	 * 消息中间件生产者处理器
	 * @return
	 */
	default IRemonProducerClient getClient() {
		//spring上下文可能存在一定延迟，有需要可以重写
		return ApplicationContextUtils.getBean(IRemonProducerClient.class);
	}
	
	/**
	 * 取得主题
	 * @return
	 */
	String getTopic();
	
	/**
	 * 取得生产组名
	 * @return
	 */
	default String getProducerGroup() {
		return DEFAULT_PRODUCER_GROUP;
	}

	/**
	 * 发送
	 * @param topic
	 * @param message
	 * @return
	 */
	default RemonSendResult send(String message) {
		return getClient().send(getProducerGroup(), getTopic(), message);
	}
	
	/**
	 * 发送
	 * @param content
	 * @return
	 */
	default RemonSendResult sendJson(Object content) {
		return send(Assist.toJsonDefault(content));
	}
}
