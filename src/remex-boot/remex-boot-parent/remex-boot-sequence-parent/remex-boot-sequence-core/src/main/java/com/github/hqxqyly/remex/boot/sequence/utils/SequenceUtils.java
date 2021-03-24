package com.github.hqxqyly.remex.boot.sequence.utils;

import com.github.hqxqyly.remex.boot.sequence.client.ISequenceClient;
import com.github.hqxqyly.remex.boot.utils.ApplicationContextUtils;

/**
 * 序列工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class SequenceUtils {

	/**
	 * 取得处理器
	 * @return
	 */
	public static ISequenceClient getClient() {
		return ApplicationContextUtils.getBean(ISequenceClient.class);
	}
	
	/**
	 * 获取下一个序列，并不会直接修改数据
	 * @param key 序列key
	 * @return
	 */
	public static String nextSeq(String key) {
		return getClient().nextSeq(key);
	}
	
	/**
	 * 获取下一个序列，并直接修改数据
	 * @param key 序列key
	 * @return
	 */
	public static String nextAndSaveSeq(String key) {
		return getClient().nextAndSaveSeq(key);
	}
	
	/**
	 * 保存下一个序列，可用于{@link #nextSeq(String)}获取下一个序列后的修改数据
	 * @param key 序列key
	 * @param seq 序列
	 */
	public static void saveForNextSeq(String key, String seq) {
		getClient().saveForNextSeq(key, seq);
	}
}
