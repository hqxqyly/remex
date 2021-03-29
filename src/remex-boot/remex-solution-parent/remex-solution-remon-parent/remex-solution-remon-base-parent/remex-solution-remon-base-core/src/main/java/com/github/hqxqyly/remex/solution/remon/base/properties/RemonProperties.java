package com.github.hqxqyly.remex.solution.remon.base.properties;

/**
 * 消息中间件properties
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RemonProperties {
	
	/** properties配置文件前缀 */
	public static final String PREFIX = "remon";
	
	/** 默认生产组名 */
	public static String DEFAULT_PRODUCER_GROUP = "DEFAULT_PRODUCER_GROUP";
	
	/** 默认消费组名 */
	public static String DEFAULT_CONSUMER_GROUP = "DEFAULT_CONSUMER_GROUP";

	/** 是否打印消费者日志 */
	protected Boolean isConsumerLog;
	
	/** 是否打印生产者日志 */
	protected Boolean isProducerLog;

	public Boolean getIsConsumerLog() {
		return isConsumerLog;
	}

	public void setIsConsumerLog(Boolean isConsumerLog) {
		this.isConsumerLog = isConsumerLog;
	}

	public Boolean getIsProducerLog() {
		return isProducerLog;
	}

	public void setIsProducerLog(Boolean isProducerLog) {
		this.isProducerLog = isProducerLog;
	}
}
