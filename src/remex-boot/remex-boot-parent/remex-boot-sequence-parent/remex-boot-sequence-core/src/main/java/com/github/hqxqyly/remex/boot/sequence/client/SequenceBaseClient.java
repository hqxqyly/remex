package com.github.hqxqyly.remex.boot.sequence.client;

import java.sql.Timestamp;
import java.util.Date;

import com.github.hqxqyly.remex.boot.cache.utils.CacheUtils;
import com.github.hqxqyly.remex.boot.constant.BConst;
import com.github.hqxqyly.remex.boot.exception.RemexException;
import com.github.hqxqyly.remex.boot.sequence.bean.SequenceNextSeqDbBean;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.boot.utils.DateUtils;

/**
 * 序列处理器
 * 
 * @author Qiaoxin.Hong
 *
 */
public abstract class SequenceBaseClient implements ISequenceClient {

	/** 缓存锁key - 用于保存下一个序列 */
	protected String cacheLockKeySaveSeq = "REMEX_SEQUENCE_LOCK_SAVE_SEQ";
	
	/** 默认序列数字长度，不包含前缀 */
	protected int defaultSeqNumLength = 8;
	
	/** 默认序列递增值 */
	protected int defaultIncrease = 1;
	
	/** 默认是否开启日期序列 */
	protected boolean defaultIsDateSeq = false;
	
	/** 默认日期格式化格式 */
	protected String defaultDateFormatPattern = "yyyyMMdd";
	
	/**
	 * 获取下一个序列db信息
	 * @param key 序列key
	 * @return
	 */
	public abstract SequenceNextSeqDbBean nextSeqDbInfo(String key);
	
	/**
	 * 保存序列到db
	 * @param bo
	 * @param seq
	 * @param curDate
	 */
	public abstract void saveDbSeq(SequenceNextSeqDbBean bo, Long seq, Timestamp curDate);
	
	/**
	 * 获取下一个序列，并不会直接修改数据
	 * @param key 序列key
	 * @return
	 */
	@Override
	public String nextSeq(String key) {
		notBlank(key, "key cannot be blank");
		
		//获取下一个序列db信息
		SequenceNextSeqDbBean bo = nextSeqDbInfo(key);
		//计算下一个序列，并补充固定序列长度
		String nextSeqStr = calcNextAndFixedSeq(bo);
		
		return nextSeqStr;
	}
	
	/**
	 * 保存下一个序列，可用于{@link #nextSeq(String)}获取下一个序列后的修改数据
	 * @param key 序列key
	 * @param seq 序列
	 * @return
	 */
	@Override
	public void saveForNextSeq(String key, String seq) {
		Assist.notBlank(key, "key cannot be blank");
		Assist.notBlank(seq, "seq cannot be blank");
		
		//修改缓存锁
		String cacheLockKey = CacheUtils.packCacheKey(cacheLockKeySaveSeq, key);
		
		CacheUtils.lock(cacheLockKey, () -> {
			String curSeq = seq;
			Timestamp curDate = DateUtils.getCurTimestamp();
			
			//获取下一个序列db信息
			SequenceNextSeqDbBean bo = nextSeqDbInfo(key);
			
			if (Assist.isNotBlank(bo.getPrefix()))
				Assist.mustTrue(seq.startsWith(bo.getPrefix()), "seq must start with prefix");
			
			//计算db的下一个序列，并补充固定序列长度
			String dbNextSeqStr = calcNextAndFixedSeq(bo);
			
			//验证序列是否已过时
			if (!curSeq.equals(dbNextSeqStr)) {
				throw new RemexException("seq overdue " + curSeq);
			}
			
			if (Assist.isNotBlank(bo.getPrefix()))
				//移除当前序列的key
				curSeq = curSeq.replaceFirst(bo.getPrefix(), "");
			
			//是否跨天
			boolean isDateOverdue = false;
			
			//是否开启日期序列
			boolean curIsDateSeq = bo.getIsDateSeq() == null ? defaultIsDateSeq : bo.getIsDateSeq();
			//开启日期序列
			if (curIsDateSeq) {
				String curDateFormatPattern = Assist.isBlank(bo.getDateFormatPattern()) 
						? defaultDateFormatPattern : bo.getDateFormatPattern();
				String dateSeqPrefix = DateUtils.format(curDate, curDateFormatPattern);
				
				Assist.mustTrue(curSeq.startsWith(dateSeqPrefix), "seq must start with cur date");
				
				//移除当前序列的日期
				curSeq = curSeq.replaceFirst(dateSeqPrefix, "");
				
				//最后修改时间
				Timestamp lastDate = bo.getLastDate();
				if (lastDate != null) {
					String lastDateSeqPrefix = DateUtils.format(lastDate, curDateFormatPattern);
					//如果库里的最后修改时间与当前时间不匹配，则认为跨天了，则重置序列值
					isDateOverdue = !dateSeqPrefix.equals(lastDateSeqPrefix);
				}
			}
			
			//取得当前序列num，如果跨天则重置为0
			Long seqNum = isDateOverdue ? 0 : Assist.toLong(curSeq);
			
			//保存序列
			saveDbSeq(bo, seqNum, curDate);
		});
	}
	
	/**
	 * 计算下一个序列
	 * @param seq 当前序列
	 * @param increase 递增值，null则使用默认递增值，正常为1
	 * @return
	 */
	protected long calcNextSeq(Long seq, Integer increase) {
		Assist.notNull(seq, "seq cannot be null");
		if (increase == null)
			increase = defaultIncrease;
		
		return seq + increase;
	}
	
	/**
	 * 补充固定序列长度
	 * @param seq 序列
	 * @param seqNumLength 序列数字长度，null则使用默认序列数字长度
	 * @param isDateSeq 是否开启日期序列，null或false不开启日期序列
	 * @param dateFormatPattern 日期格式化格式，null则使用默认日期格式化格式
	 * @return
	 */
	protected String supplementFixedSeq(Long seq, Integer seqNumLength, Boolean isDateSeq, String dateFormatPattern) {
		Assist.notNull(seq, "seq cannot be null");
		String seqStr = seq.toString();
		
		if (seqNumLength == null)
			seqNumLength = defaultSeqNumLength;
		
		//拼接序列日期前缀
		String dateSeqPrefix = packSeqDatePrefix(isDateSeq, dateFormatPattern);
		
		int lengthDif = seqNumLength - dateSeqPrefix.length() - seqStr.length();
		if (lengthDif > 0) {
			for (int i = 0; i < lengthDif; i++) {
				seqStr = "0" + seqStr;
			}
		}
		
		seqStr = dateSeqPrefix +seqStr;
		
		return seqStr;
	}
	
	/**
	 * 计算下一个序列，并补充固定序列长度
	 * @param bo 下一个序列结果信息
	 * @return
	 */
	protected String calcNextAndFixedSeq(SequenceNextSeqDbBean bo) {
		//计算下一个序列
		long nextSeq = calcNextSeq(bo.getCurSeq(), bo.getIncrease());
		//补充固定序列长度
		String nextSeqStr = supplementFixedSeq(nextSeq, bo.getSeqNumLength(), bo.getIsDateSeq(), bo.getDateFormatPattern());
		//序列key+序列值
		nextSeqStr = Assist.join(bo.getPrefix(), nextSeqStr);
		
		return nextSeqStr;
	}
	
	/**
	 * 拼接序列日期前缀
	 * @param isDateSeq
	 * @param dateFormatPattern
	 * @return
	 */
	protected String packSeqDatePrefix(Boolean isDateSeq, String dateFormatPattern) {
		//日期序列前缀
		String dateSeqPrefix = BConst.EMPTY;
		
		if (isDateSeq == null)
			isDateSeq = defaultIsDateSeq;
		
		//开启日期序列
		if (isDateSeq) {
			if (isBlank(dateFormatPattern))
				dateFormatPattern = defaultDateFormatPattern;
			dateSeqPrefix = DateUtils.format(new Date(), dateFormatPattern);
		}
		return dateSeqPrefix;
	}
	
	
	
	

	public String getCacheLockKeySaveSeq() {
		return cacheLockKeySaveSeq;
	}

	public void setCacheLockKeySaveSeq(String cacheLockKeySaveSeq) {
		this.cacheLockKeySaveSeq = cacheLockKeySaveSeq;
	}

	public int getDefaultSeqNumLength() {
		return defaultSeqNumLength;
	}

	public void setDefaultSeqNumLength(int defaultSeqNumLength) {
		this.defaultSeqNumLength = defaultSeqNumLength;
	}

	public int getDefaultIncrease() {
		return defaultIncrease;
	}

	public void setDefaultIncrease(int defaultIncrease) {
		this.defaultIncrease = defaultIncrease;
	}
}
