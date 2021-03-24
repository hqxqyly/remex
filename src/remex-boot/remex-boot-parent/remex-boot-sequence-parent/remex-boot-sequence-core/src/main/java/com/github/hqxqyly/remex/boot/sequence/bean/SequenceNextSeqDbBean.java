package com.github.hqxqyly.remex.boot.sequence.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 下一个序列db信息
 * 
 * @author Qiaoxin.Hong
 *
 */
public class SequenceNextSeqDbBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** ID */
	protected String id;

	/** 序列key */
	protected String key;
	
	/** 序列前缀 */
    protected String prefix;
	
	/** 当前db序列值 */
	protected Long curSeq;
	
	/** 递增值 */
	protected Integer increase;
	
	/** 序列数字长度 */
	protected Integer seqNumLength;
	
	/** 是否开启日期序列 */
	protected Boolean isDateSeq;
	
	/** 日期格式化格式 */
	protected String dateFormatPattern;
	
	/** 最后修改日期 */
	protected Timestamp lastDate;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Long getCurSeq() {
		return curSeq;
	}

	public void setCurSeq(Long curSeq) {
		this.curSeq = curSeq;
	}

	public Integer getIncrease() {
		return increase;
	}

	public void setIncrease(Integer increase) {
		this.increase = increase;
	}

	public Integer getSeqNumLength() {
		return seqNumLength;
	}

	public void setSeqNumLength(Integer seqNumLength) {
		this.seqNumLength = seqNumLength;
	}

	public Boolean getIsDateSeq() {
		return isDateSeq;
	}

	public void setIsDateSeq(Boolean isDateSeq) {
		this.isDateSeq = isDateSeq;
	}

	public String getDateFormatPattern() {
		return dateFormatPattern;
	}

	public void setDateFormatPattern(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public void setLastDate(Timestamp lastDate) {
		this.lastDate = lastDate;
	}
	
	public Timestamp getLastDate() {
		return lastDate;
	}
}
