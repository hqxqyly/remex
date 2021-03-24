package com.github.hqxqyly.remex.boot.sequence.properties;

/**
 * 序列properties
 * 
 * @author Qiaoxin.Hong
 *
 */
public class SequenceProperties {

	/** properties配置文件前缀 */
	public static final String PREFIX = "sequence";
	
	/** 缓存锁key - 用于保存下一个序列 */
	protected String cacheLockKeySaveSeq;
	
	/** 默认序列数字长度，不包含前缀 */
	protected Integer defaultSeqNumLength;
	
	/** 默认序列递增值 */
	protected Integer defaultIncrease;

	public String getCacheLockKeySaveSeq() {
		return cacheLockKeySaveSeq;
	}

	public void setCacheLockKeySaveSeq(String cacheLockKeySaveSeq) {
		this.cacheLockKeySaveSeq = cacheLockKeySaveSeq;
	}

	public Integer getDefaultSeqNumLength() {
		return defaultSeqNumLength;
	}

	public void setDefaultSeqNumLength(Integer defaultSeqNumLength) {
		this.defaultSeqNumLength = defaultSeqNumLength;
	}

	public Integer getDefaultIncrease() {
		return defaultIncrease;
	}

	public void setDefaultIncrease(Integer defaultIncrease) {
		this.defaultIncrease = defaultIncrease;
	}
}
