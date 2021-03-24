package com.github.hqxqyly.remex.boot.rocketmq.producer.client;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.github.hqxqyly.remex.boot.exception.RemexException;
import com.github.hqxqyly.remex.boot.interfaces.assist.IAssistClient;
import com.github.hqxqyly.remex.boot.rocketmq.producer.properties.RocketMqProducerProperties;
import com.github.hqxqyly.remex.boot.rocketmq.properties.RocketMqProperties;
import com.github.hqxqyly.remex.boot.rocketmq.utils.RocketMqUtils;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * rocketmq生产者
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RocketMqProducer extends DefaultMQProducer implements IAssistClient, IRocketMqProducer
	, ApplicationContextAware, ApplicationRunner, InitializingBean {
	
	/** 默认生产组名 */
	public static String DEFAULT_PRODUCER_GROUP = "DEFAULT_PRODUCER_GROUP";

	/** 主题 */
	protected String topic;

	/** tag */
	protected String tag;
	
	@Autowired
	protected RocketMqProducerProperties properties;
	
	@Autowired
	protected RocketMqProperties rocketMqProperties;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		//初始化前
		initBefore();
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		//初始化后
		initAfter();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		//初始化结束
		initOver();
	}

	/**
	 * 发送
	 * @param message
	 * @return
	 */
	@Override
	public SendResult send(String message) {
		notBlank(topic, "topic cannot be blank");
		notBlank(tag, "tag cannot be blank");
		
		try {
			Message messageExt = new Message(topic, tag, message.getBytes());
			
			return send(messageExt);
		} catch (Exception e) {
			throw Assist.transferException("rocketmq send error", e);
		}
	}
	
	/**
	 * 初始化前
	 */
	public void initBefore() {
		ifNotBlank(rocketMqProperties.getNamesrvAddr(), this::setNamesrvAddr);
		ifNotNull(properties.getMaxMessageSize(), this::setMaxMessageSize);
		ifNotNull(properties.getSendMsgTimeout(), this::setSendMsgTimeout);
		defaultString(properties.getProducerGroup(), DEFAULT_PRODUCER_GROUP, this::setProducerGroup);
	}
	
	/**
	 * 初始化后
	 */
	public void initAfter() {
		if (isBlank(tag))
			tag = RocketMqUtils.packTag(topic);
	}
	
	/**
	 * 初始化结束
	 */
	public void initOver() {
		try {
			//启动
			start();
		} catch (Exception e) {
			throw new RemexException("rocketmq producer init over error", e);
		}
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public RocketMqProducerProperties getProperties() {
		return properties;
	}

	public void setProperties(RocketMqProducerProperties properties) {
		this.properties = properties;
	}

	public RocketMqProperties getRocketMqProperties() {
		return rocketMqProperties;
	}

	public void setRocketMqProperties(RocketMqProperties rocketMqProperties) {
		this.rocketMqProperties = rocketMqProperties;
	}
}
