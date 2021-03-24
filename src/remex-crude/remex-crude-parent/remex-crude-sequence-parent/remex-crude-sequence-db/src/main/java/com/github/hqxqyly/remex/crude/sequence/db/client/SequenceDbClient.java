package com.github.hqxqyly.remex.crude.sequence.db.client;

import java.sql.Timestamp;

import com.github.hqxqyly.remex.boot.constant.BConst;
import com.github.hqxqyly.remex.boot.sequence.bean.SequenceNextSeqDbBean;
import com.github.hqxqyly.remex.boot.sequence.client.SequenceBaseClient;
import com.github.hqxqyly.remex.boot.utils.ApplicationContextUtils;
import com.github.hqxqyly.remex.crude.sequence.controller.SequenceController;
import com.github.hqxqyly.remex.crude.sequence.entity.SequenceEntity;
import com.github.hqxqyly.remex.crude.sequence.msg.MsgSequenceEnum;
import com.github.hqxqyly.remex.fast.mybatis.plus.mapper.FastBaseMapper;
import com.github.hqxqyly.remex.fast.server.structure.service.IBaseService;

/**
 * 序列处理器 - db
 * 
 * @author Qiaoxin.Hong
 *
 */
public class SequenceDbClient extends SequenceBaseClient implements IBaseService {
	
	/**
	 * 取得Controller
	 */
	@SuppressWarnings("unchecked")
	protected <T extends SequenceEntity> SequenceController<T> getController() {
		return ApplicationContextUtils.getBean(SequenceController.class);
	}
	
	/**
	 * 取得dao
	 */
	@SuppressWarnings("unchecked")
	protected <T extends SequenceEntity, M extends FastBaseMapper<T>> M getDao() {
		return (M) getController().getDao();
	}

	/**
	 * 获取下一个序列逻辑处理，并不会直接修改数据
	 * @param key 序列key
	 * @return
	 */
	@Override
	public SequenceNextSeqDbBean nextSeqDbInfo(String key) {
		//查询序列
		SequenceEntity entity = findBySeqKey(key);
		//组装下一下序列信息
		SequenceNextSeqDbBean bo = packNextSeqInfo(entity);

		return bo;
	}
	
	/**
	 * 保存序列到db
	 * @param bo
	 * @param seq
	 */
	@Override
	public void saveDbSeq(SequenceNextSeqDbBean bo, Long seq, Timestamp curDate) {
		SequenceEntity entity = newUpdateEntity(SequenceEntity.class);
		entity.setId(bo.getId());
		entity.setSeq(seq);
		entity.setLastDate(curDate);
		
		getDao().assertUpdateById(entity);
	}
	
	/**
	 * 根据序列key，查询序列bean
	 * @param key
	 * @return
	 */
	protected SequenceEntity findBySeqKey(String key) {
		return getDao().assertExistSelectOneByKey("seq_type", key, MsgSequenceEnum.SEQUENCE_NOT_EXISTS);
	}
	
	/**
	 * 组装下一下序列信息
	 * @param entity
	 * @return
	 */
	protected SequenceNextSeqDbBean packNextSeqInfo(SequenceEntity entity) {
		SequenceNextSeqDbBean bo = new SequenceNextSeqDbBean();
		fill(bo, entity);
		
		//计算下一个序列，并补充固定序列长度
		calcNextAndFixedSeq(bo);
		
		return bo;
	}
	
	
	
	
	protected void fill(SequenceNextSeqDbBean bo, SequenceEntity entity) {
		bo.setId(entity.getId());
		bo.setKey(entity.getSeqType());
		bo.setPrefix(entity.getPrefix());
		bo.setCurSeq(entity.getSeq());
		bo.setIncrease(entity.getIncrease());
		bo.setSeqNumLength(entity.getSeqNumLength());
		if (entity.getIsDateSeq() != null) {
			bo.setIsDateSeq(BConst.YES.equals(entity.getIsDateSeq()));
		}
		bo.setDateFormatPattern(entity.getDateFormatPattern());
		bo.setLastDate(entity.getLastDate());
	}
}
