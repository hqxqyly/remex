package com.github.hqxqyly.remex.boot.rocketmq.consumer.properties;

/**
 * rocketmq properties - 消费者
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RocketMqConsumerProperties {
	
	/** properties配置文件前缀 */
	public static final String PREFIX = "rocketmq.consumer";
	
	/** 消费组名 */
	protected String consumerGroup;
	
	/** 消费最小线程数 */
	protected Integer consumeThreadMin;
	
	/** 消费最大线程数 */
	protected Integer consumeThreadMax;

	public Integer getConsumeThreadMin() {
		return consumeThreadMin;
	}

	public void setConsumeThreadMin(Integer consumeThreadMin) {
		this.consumeThreadMin = consumeThreadMin;
	}

	public Integer getConsumeThreadMax() {
		return consumeThreadMax;
	}

	public void setConsumeThreadMax(Integer consumeThreadMax) {
		this.consumeThreadMax = consumeThreadMax;
	}
	
	public void setConsumerGroup(String consumerGroup) {
		this.consumerGroup = consumerGroup;
	}
	
	public String getConsumerGroup() {
		return consumerGroup;
	}
}
