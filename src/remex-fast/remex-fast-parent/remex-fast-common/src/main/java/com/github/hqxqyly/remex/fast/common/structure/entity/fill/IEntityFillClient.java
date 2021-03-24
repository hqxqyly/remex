package com.github.hqxqyly.remex.fast.common.structure.entity.fill;

import java.sql.Timestamp;

import com.github.hqxqyly.remex.boot.constant.BConst;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.boot.utils.DateUtils;
import com.github.hqxqyly.remex.boot.utils.ServletUtils;
import com.github.hqxqyly.remex.fast.common.structure.entity.IBaseEntity;

/**
 * 设置业务实体的公共属性处理器
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IEntityFillClient<T extends IBaseEntity> {
	
	/** header参数名 - 操作人ID */
	public static String HEADER_NAME_OP_USER_ID = "opUserId";
	
	/** header参数名 - 操作人 */
	public static String HEADER_NAME_OP_USER_Name = "opUserName";
	
	/**
	 * 设置业务实体的公共业务属性
	 * @param entity
	 */
	default void fillBizProperty(T entity) {
		entity.setOpUserId(ServletUtils.getHeader(HEADER_NAME_OP_USER_ID));
		entity.setOpUserName(ServletUtils.getHeader(HEADER_NAME_OP_USER_Name));
	}

	/**
	 * 设置新增业务实体的公共属性
	 * @param entity
	 * @return
	 */
	default T fillCreateEntity(T entity) {
		return fillCreateEntity(entity, null);
	}
	
	/**
	 * 设置新增业务实体的公共属性
	 * @param entity
	 * @param id 有值则直接用，没值就初始化
	 * @return
	 */
	default T fillCreateEntity(T entity, String id) {
		Timestamp curTimestamp = DateUtils.getCurTimestamp();
		
		//如果未初始化ID，则进行初始化
		if (Assist.isBlank(id))
			entity.setId(Assist.newId());
		else  //有ID则直接用
			entity.setId(id);
		
		entity.setCreateTime(curTimestamp);
		entity.setUpdateTime(curTimestamp);
		entity.setIsDelete(BConst.NO);
		
		//设置公共业务属性
		fillBizProperty(entity);
		
		return entity;
	}
	
	/**
	 * 设置修改业务实体的公共属性
	 * @param entity
	 * @return
	 */
	default T fillUpdateEntity(T entity) {
		entity.setCreateTime(null);
		entity.setUpdateTime(DateUtils.getCurTimestamp());

		//设置公共业务属性
		fillBizProperty(entity);

		return entity;
	}
	
	/**
	 * 设置软删业务实体的公共属性
	 * @param entity
	 * @return
	 */
	default T fillDeleteEntity(T entity, String id) {
		Assist.notBlank(id, "id cannot be blank");
		
		entity.setId(id);
		entity.setCreateTime(null);
		entity.setUpdateTime(DateUtils.getCurTimestamp());
		entity.setIsDelete(BConst.YES);

		//设置公共业务属性
		fillBizProperty(entity);

		return entity;
	}
}
