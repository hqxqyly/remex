package com.github.hqxqyly.remex.solution.remon.base.consumer.customer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import com.github.hqxqyly.remex.boot.interfaces.assist.IAssistClient;
import com.github.hqxqyly.remex.solution.remon.base.consumer.listener.IRemonListener;
import com.github.hqxqyly.remex.solution.remon.base.properties.RemonProperties;

/**
 * 消息中间件消费者
 * 
 * @author Qiaoxin.Hong
 *
 */
public abstract class RemonBaseConsumer implements IAssistClient, IRemonConsumer, IRemonListener, ApplicationRunner {

	/** 主题 */
	protected String topic;
	
	/** 消费组名 */
	protected String consumerGroup;
	
	/** 是否打印日志 */
	protected boolean isLog = true;
	
	/** 是否打印参数日志 */
	protected boolean isArgsLog = true;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		getLogger().info("remon consumer register [consumerGroup : {}] [topic : {}]"
				, defaultString(consumerGroup, RemonProperties.DEFAULT_CONSUMER_GROUP)
				, topic);
		getClient().registerImplConsumer(this, consumerGroup, topic);
	}
	
	/**
	 * 接收
	 * @param topic 主题
	 * @param message 消息
	 * @param messageId 消息ID
	 */
	@Override
	public boolean receive(String topic, String message, String messageId) {
		if (isLog) {
			if (isArgsLog)
				getLogger().info("remon consumer receive [topic: {}] [message: {}]", topic, message);
			else
				getLogger().info("remon consumer receive [topic: {}]", topic);
		}
		
		return receiveHandle(topic, message);
	}
	
	/**
	 * 接收
	 * @param topic 主题
	 * @param message 消息
	 */
	public abstract boolean receiveHandle(String topic, String message);
	
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public String getTopic() {
		return topic;
	}

	public boolean isLog() {
		return isLog;
	}

	public void setLog(boolean isLog) {
		this.isLog = isLog;
	}

	public boolean isArgsLog() {
		return isArgsLog;
	}

	public void setArgsLog(boolean isArgsLog) {
		this.isArgsLog = isArgsLog;
	}

	public String getConsumerGroup() {
		return consumerGroup;
	}

	public void setConsumerGroup(String consumerGroup) {
		this.consumerGroup = consumerGroup;
	}
}
