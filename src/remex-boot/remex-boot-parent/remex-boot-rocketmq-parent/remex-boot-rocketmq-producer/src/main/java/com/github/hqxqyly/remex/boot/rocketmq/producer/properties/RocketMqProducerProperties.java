package com.github.hqxqyly.remex.boot.rocketmq.producer.properties;

/**
 * rocketmq properties - 生产者
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RocketMqProducerProperties {
	
	/** properties配置文件前缀 */
	public static final String PREFIX = "rocketmq.producer";
	
	/** 消费组名 */
	protected String producerGroup;
	
	/** 消息最大字节数 */
	protected Integer maxMessageSize;
	
	/** 发送超时时间 */
	protected Integer sendMsgTimeout;

	public Integer getMaxMessageSize() {
		return maxMessageSize;
	}

	public void setMaxMessageSize(Integer maxMessageSize) {
		this.maxMessageSize = maxMessageSize;
	}

	public Integer getSendMsgTimeout() {
		return sendMsgTimeout;
	}

	public void setSendMsgTimeout(Integer sendMsgTimeout) {
		this.sendMsgTimeout = sendMsgTimeout;
	}

	public void setProducerGroup(String producerGroup) {
		this.producerGroup = producerGroup;
	}
	
	public String getProducerGroup() {
		return producerGroup;
	}
}
