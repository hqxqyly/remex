package com.github.hqxqyly.remex.boot.rocketmq.consumer.client;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.github.hqxqyly.remex.boot.exception.RemexException;
import com.github.hqxqyly.remex.boot.interfaces.assist.IAssistClient;
import com.github.hqxqyly.remex.boot.rocketmq.consumer.properties.RocketMqConsumerProperties;
import com.github.hqxqyly.remex.boot.rocketmq.properties.RocketMqProperties;
import com.github.hqxqyly.remex.boot.rocketmq.utils.RocketMqUtils;

/**
 * rocketmq消费者
 * 
 * @author Qiaoxin.Hong
 *
 */
public abstract class RocketMqConsumer extends DefaultMQPushConsumer implements IAssistClient, IRocketMqConsumer
	, MessageListenerConcurrently, ApplicationContextAware, ApplicationRunner, InitializingBean {

	/** 默认消费组名 */
	public static String DEFAULT_CONSUMER_GROUP = "DEFAULT_CONSUMER_GROUP";
	
	/** 主题 */
	protected String topic;

	/** tag */
	protected String tag;
	
	@Autowired
	protected RocketMqConsumerProperties properties;
	
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

	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
		for (MessageExt messageExt : msgs) {
			String message = new String(messageExt.getBody());
			
			boolean result = receive(message, messageExt.getMsgId());
			if (!result)
				return ConsumeConcurrentlyStatus.RECONSUME_LATER;
		}
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}
	
	
	
	/**
	 * 初始化前
	 */
	public void initBefore() {
		ifNotBlank(rocketMqProperties.getNamesrvAddr(), this::setNamesrvAddr);
		ifNotNull(properties.getConsumeThreadMin(), this::setConsumeThreadMin);
		ifNotNull(properties.getConsumeThreadMax(), this::setConsumeThreadMax);
		defaultString(properties.getConsumerGroup(), DEFAULT_CONSUMER_GROUP, this::setConsumerGroup);
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
			registerMessageListener(this);
			subscribe(topic, tag);
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

	public RocketMqConsumerProperties getProperties() {
		return properties;
	}

	public void setProperties(RocketMqConsumerProperties properties) {
		this.properties = properties;
	}

	public RocketMqProperties getRocketMqProperties() {
		return rocketMqProperties;
	}

	public void setRocketMqProperties(RocketMqProperties rocketMqProperties) {
		this.rocketMqProperties = rocketMqProperties;
	}
}
