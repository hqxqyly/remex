package com.github.hqxqyly.remex.boot.rocketmq.properties;

/**
 * rocketmq properties
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RocketMqProperties {
	
	/** properties配置文件前缀 */
	public static final String PREFIX = "rocketmq";

	/** mq地址 */
	protected String namesrvAddr;
	
	public void setNamesrvAddr(String namesrvAddr) {
		this.namesrvAddr = namesrvAddr;
	}
	
	public String getNamesrvAddr() {
		return namesrvAddr;
	}
}
