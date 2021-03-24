package com.github.hqxqyly.remex.boot.remon.base.producer.customer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * 消息中间件生产者
 * 
 * @author Qiaoxin.Hong
 *
 */
public abstract class RemonBaseProducer implements IRemonProducer, ApplicationRunner {

	/** 主题 */
	protected String topic;
	
	/** 生产组名 */
	protected String producerGroup;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		getClient().registerImplProducer(producerGroup, topic);
	}
	
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	@Override
	public String getTopic() {
		return topic;
	}
	
	public void setProducerGroup(String producerGroup) {
		this.producerGroup = producerGroup;
	}
	
	@Override
	public String getProducerGroup() {
		return producerGroup;
	}
}
