package com.github.hqxqyly.remex.boot.rocketmq.utils;

import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * rocketmq工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RocketMqUtils {

	/**
	 * 以topic拼接默认tag
	 * @param topic
	 * @return
	 */
	public static String packTag(String topic) {
		Assist.notBlank(topic, "topic cannot be blank");
		return Assist.join(topic, "_TAG");
	}
}
