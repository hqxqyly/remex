package com.github.hqxqyly.remex.boot.sequence.id.client;

import com.github.hqxqyly.remex.boot.sequence.client.ISequenceClient;

/**
 * 序列处理器 - id
 * 
 * @author Qiaoxin.Hong
 *
 */
public class SequenceIdClient implements ISequenceClient {

	/**
	 * 获取下一个序列，并不会直接修改数据
	 * @param key 序列key
	 * @return
	 */
	@Override
	public String nextSeq(String key) {
		return newId();
	}

	/**
	 * 保存下一个序列，可用于{@link #nextSeq(String)}获取下一个序列后的修改数据
	 * @param key 序列key
	 * @param seq 序列
	 * @return
	 */
	@Override
	public void saveForNextSeq(String key, String seq) {
		//序列ID处理器默认采用UUID，每次生成都不一样，所以无需修改
	}
}
