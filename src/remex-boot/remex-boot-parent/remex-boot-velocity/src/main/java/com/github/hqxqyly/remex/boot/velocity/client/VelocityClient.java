package com.github.hqxqyly.remex.boot.velocity.client;

/**
 * velocity处理器
 * 
 * @author Qiaoxin.Hong
 *
 */
public class VelocityClient {

	/**
	 * 文本渲染
	 * @param content
	 * @param data
	 * @return
	 */
	public String render(String content, Object data) {
		System.out.println("velocity =========");
		return content;
	}

}
