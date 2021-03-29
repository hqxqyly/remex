package com.github.hqxqyly.remex.solution.remon.base.producer.client;

import com.github.hqxqyly.remex.boot.interfaces.assist.IAssistClient;
import com.github.hqxqyly.remex.solution.remon.base.producer.bean.RemonSendResult;

/**
 * 消息中间件基础生产者处理器
 * 
 * @author Qiaoxin.Hong
 *
 */
public abstract class RemonBaseProducerClient implements IRemonProducerClient, IAssistClient {
	
	/** 默认生产组名 */
	public static String DEFAULT_PRODUCER_GROUP = "DEFAULT_PRODUCER_GROUP";
	
	/** 是否打印生产者日志 */
	protected boolean isProducerLog = true;
	
	/**
	 * 发送逻辑处理
	 * @param producerGroup 生产组名
	 * @param topic 主题
	 * @param message 消息
	 * @return
	 */
	public abstract RemonSendResult sendHandle(String producerGroup, String topic, String message);
	
	/**
	 * 发送
	 * @param producerGroup 生产组名
	 * @param topic 主题
	 * @param message 消息
	 * @return
	 */
	@Override
	public RemonSendResult send(String producerGroup, String topic, String message) {
		notBlank(topic, "topic cannot be blank");
		
		if (isProducerLog)
			getLogger().info("remon producer send begin [topic : {}] [message : {}]", topic, message);
		
		//发送逻辑处理
		RemonSendResult result = sendHandle(producerGroup, topic, message);
		
		//发送成功
		if (result.isResult())
			getLogger().info("remon producer send success [topic : {}]", topic);
		else //发送失败
			getLogger().error("remon producer send error [topic : {}] [result : {}] [message : {}]", topic, result.getMsg(), message);
		
		return result;
	}

	
	
	
	public boolean isProducerLog() {
		return isProducerLog;
	}

	public void setProducerLog(boolean isProducerLog) {
		this.isProducerLog = isProducerLog;
	}
}
