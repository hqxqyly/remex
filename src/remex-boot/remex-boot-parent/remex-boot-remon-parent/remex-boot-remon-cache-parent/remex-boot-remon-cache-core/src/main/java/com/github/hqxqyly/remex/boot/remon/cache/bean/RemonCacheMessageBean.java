package com.github.hqxqyly.remex.boot.remon.cache.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 消息中间件 - 缓存 - 消息bean
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RemonCacheMessageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 消息ID */
	protected String id;

	/** 业务数据 */
	protected Object data;
	
	/** 生产组名 */
	protected String producerGroup;
	
	/** 发送时间 */
	protected Timestamp sendTime;
	
	/** 正常消费的时间 */
	protected Timestamp receiveTime;
	
	/** 正常消费的消息 */
	protected String receiveMsg;
	
	/** 重试次数 */
	protected Integer retryCount = 0;
	
	/** 上一次重试时间 */
	protected Timestamp lastRetryTime;
	
	/** 上一次重试消息 */
	protected String lastRetryMsg;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Timestamp getSendTime() {
		return sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	public Timestamp getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Timestamp receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getReceiveMsg() {
		return receiveMsg;
	}

	public void setReceiveMsg(String receiveMsg) {
		this.receiveMsg = receiveMsg;
	}

	public Integer getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}

	public Timestamp getLastRetryTime() {
		return lastRetryTime;
	}

	public void setLastRetryTime(Timestamp lastRetryTime) {
		this.lastRetryTime = lastRetryTime;
	}

	public String getLastRetryMsg() {
		return lastRetryMsg;
	}

	public void setLastRetryMsg(String lastRetryMsg) {
		this.lastRetryMsg = lastRetryMsg;
	}
	
	public void setProducerGroup(String producerGroup) {
		this.producerGroup = producerGroup;
	}
	
	public String getProducerGroup() {
		return producerGroup;
	}
}
