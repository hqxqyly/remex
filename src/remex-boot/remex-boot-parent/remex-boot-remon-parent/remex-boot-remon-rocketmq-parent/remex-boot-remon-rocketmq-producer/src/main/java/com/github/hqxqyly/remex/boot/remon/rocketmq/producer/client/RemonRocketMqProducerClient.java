package com.github.hqxqyly.remex.boot.remon.rocketmq.producer.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.hqxqyly.remex.boot.remon.base.producer.bean.RemonSendResult;
import com.github.hqxqyly.remex.boot.remon.base.producer.client.RemonBaseProducerClient;
import com.github.hqxqyly.remex.boot.remon.base.properties.RemonProperties;
import com.github.hqxqyly.remex.boot.rocketmq.producer.client.IRocketMqProducer;
import com.github.hqxqyly.remex.boot.rocketmq.producer.client.RocketMqProducer;
import com.github.hqxqyly.remex.boot.rocketmq.producer.properties.RocketMqProducerProperties;
import com.github.hqxqyly.remex.boot.rocketmq.properties.RocketMqProperties;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * 消息中间件生产者处理器 - rocketmq
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RemonRocketMqProducerClient extends RemonBaseProducerClient {
	
	/** 接口实现的生产者 */
	protected Map<String, IRocketMqProducer> implProducerMap = new ConcurrentHashMap<>();
	
	@Autowired
	protected RocketMqProducerProperties properties;
	
	@Autowired
	protected RocketMqProperties rocketMqProperties;
	
	/**
	 * 注册接口实现的生产者
	 * @param producerGroup
	 * @param topic
	 */
	@Override
	public void registerImplProducer(String producerGroup, String topic) {
		notBlank(topic, "topic cannot be blank");
		if (isBlank(producerGroup))
			producerGroup = RemonProperties.DEFAULT_PRODUCER_GROUP;
		
		getLogger().info("remon rocketmq producer register [producerGroup : {}] [topic : {}]", producerGroup, topic);
		
		RocketMqProducer implProducer = new RocketMqProducer();
		implProducer.setProducerGroup(producerGroup);
		implProducer.setTopic(topic);
		implProducer.setProperties(properties);
		implProducer.setRocketMqProperties(rocketMqProperties);
		
		implProducer.initBefore();
		implProducer.initAfter();
		implProducer.initOver();
		
		String key = Assist.join(producerGroup, ":", topic);
		
		implProducerMap.put(key, implProducer);
	}

	/**
	 * 发送逻辑处理
	 * @param producerGroup 生产组名
	 * @param topic 主题
	 * @param message 消息
	 * @return
	 */
	@Override
	public RemonSendResult sendHandle(String producerGroup, String topic, String message) {
		notBlank(topic, "topic cannot be blank");
		
		message = defaultString(message);
		
		try {
			SendResult sendResult = getImplProducer(producerGroup, topic).send(message);
			
			//发送成功
			if (SendStatus.SEND_OK.equals(sendResult.getSendStatus())) {
				return new RemonSendResult();
			} else {  //发送失败
				return RemonSendResult.createError(sendResult.toString());
			}
		} catch (Exception e) {
			throw Assist.transferException("remon rocketmq producer send error", e);
		}
	}
	
	/**
	 * 根据主题取得接口实现的生产者
	 * @param topic
	 * @return
	 */
	public IRocketMqProducer getImplProducer(String producerGroup, String topic) {
		notBlank(topic, "topic cannot be blank");
		
		String key = Assist.join(producerGroup, ":", topic);
		IRocketMqProducer implProducer = implProducerMap.get(key);
		notNull(implProducer, "implProducer cannot be null");
		
		return implProducer;
	}
}
