package com.github.hqxqyly.remex.solution.remon.rocketmq.consumer.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.hqxqyly.remex.boot.rocketmq.consumer.client.IRocketMqConsumer;
import com.github.hqxqyly.remex.boot.rocketmq.consumer.client.RocketMqConsumer;
import com.github.hqxqyly.remex.boot.rocketmq.consumer.properties.RocketMqConsumerProperties;
import com.github.hqxqyly.remex.boot.rocketmq.properties.RocketMqProperties;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.solution.remon.base.consumer.client.RemonBaseConsumerClient;
import com.github.hqxqyly.remex.solution.remon.base.consumer.listener.IRemonListener;
import com.github.hqxqyly.remex.solution.remon.base.properties.RemonProperties;
import com.github.hqxqyly.remex.solution.remon.rocketmq.consumer.customer.RemonRocketMqConsumer;

/**
 * 消息中间件消费者处理器 - rocketmq
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RemonRocketMqConsumerClient extends RemonBaseConsumerClient {
	
	/** 接口实现的消费者 */
	protected Map<String, IRocketMqConsumer> implConsumerMap = new ConcurrentHashMap<>();
	
	@Autowired
	protected RocketMqConsumerProperties properties;
	
	@Autowired
	protected RocketMqProperties rocketMqProperties;
	
	/**
	 * 注册接口实现的消费者
	 * @param listener
	 * @param consumerGroup
	 * @param topic
	 */
	@Override
	public void registerImplConsumer(IRemonListener listener, String consumerGroup, String topic) {
		notBlank(topic, "topic cannot be blank");
		
		getLogger().info("remon rocketmq consumer register [consumerGroup : {}] [topic : {}]"
				, defaultString(consumerGroup, RemonProperties.DEFAULT_CONSUMER_GROUP)
				, topic);
		
		RocketMqConsumer implConsumer = new RemonRocketMqConsumer(listener);
		implConsumer.setConsumerGroup(consumerGroup);
		implConsumer.setTopic(topic);
		implConsumer.setProperties(properties);
		implConsumer.setRocketMqProperties(rocketMqProperties);
		
		implConsumer.initBefore();
		implConsumer.initAfter();
		implConsumer.initOver();
		
		String key = Assist.join(consumerGroup, ":", topic);
		
		implConsumerMap.put(key, implConsumer);
	}
}
