package com.github.hqxqyly.remex.solution.remon.base.consumer.customer;

import com.github.hqxqyly.remex.boot.utils.ApplicationContextUtils;
import com.github.hqxqyly.remex.solution.remon.base.consumer.client.IRemonConsumerClient;

/**
 * 消息中间件消费者
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IRemonConsumer {
	
	/**
	 * 消息中间件消费者处理器
	 * @return
	 */
	default IRemonConsumerClient getClient() {
		//spring上下文可能存在一定延迟，有需要可以重写
		return ApplicationContextUtils.getBean(IRemonConsumerClient.class);
	}
}
