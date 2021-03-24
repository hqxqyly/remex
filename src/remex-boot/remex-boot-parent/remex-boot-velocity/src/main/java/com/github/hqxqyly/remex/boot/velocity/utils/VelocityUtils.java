package com.github.hqxqyly.remex.boot.velocity.utils;

import com.github.hqxqyly.remex.boot.utils.ApplicationContextUtils;
import com.github.hqxqyly.remex.boot.velocity.client.VelocityClient;

/**
 * velocity工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class VelocityUtils {

	/**
	 * 取得velocity处理器
	 * @return
	 */
	public static VelocityClient getClient() {
		return ApplicationContextUtils.getBean(VelocityClient.class);
	}
	
	/**
	 * 文本渲染
	 * @param content
	 * @param data
	 * @return
	 */
	public static String render(String content, Object data) {
		return getClient().render(content, data);
	}
}
