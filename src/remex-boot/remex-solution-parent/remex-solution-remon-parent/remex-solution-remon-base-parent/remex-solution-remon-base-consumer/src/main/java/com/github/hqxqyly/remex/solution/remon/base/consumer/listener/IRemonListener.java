package com.github.hqxqyly.remex.solution.remon.base.consumer.listener;

/**
 * 消息中间件监听器
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IRemonListener {

	/**
	 * 接收
	 * @param topic 主题
	 * @param message 消息
	 * @param messageId 消息ID
	 */
	boolean receive(String topic, String message, String messageId);
}
