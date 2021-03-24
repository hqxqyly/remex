package com.github.hqxqyly.remex.boot.sequence.client;

import com.github.hqxqyly.remex.boot.interfaces.assist.IAssist;

/**
 * 序列处理器
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface ISequenceClient extends IAssist {

	/**
	 * 获取下一个序列，并不会直接修改数据
	 * @param key 序列key
	 * @return
	 */
	String nextSeq(String key);
	
	/**
	 * 保存下一个序列，可用于{@link #nextSeq(String)}获取下一个序列后的修改数据
	 * @param key 序列key
	 * @param seq 序列
	 */
	void saveForNextSeq(String key, String seq);
	
	/**
	 * 获取下一个序列，并直接修改数据
	 * @param key 序列key
	 * @return
	 */
	default String nextAndSaveSeq(String key) {
		notBlank(key, "key cannot be blank");
		
		//获取下一个序列
		String seq = nextSeq(key);
		//保存下一个序列
		saveForNextSeq(key, seq);
		
		return seq;
	}
}
