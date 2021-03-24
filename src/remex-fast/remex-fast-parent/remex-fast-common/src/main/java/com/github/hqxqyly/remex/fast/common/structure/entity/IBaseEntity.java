package com.github.hqxqyly.remex.fast.common.structure.entity;

import java.sql.Timestamp;

/**
 * 基础entity
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IBaseEntity {

	/**
	 * 设置主键编号
	 */
	void setId(String id);
	
	/**
	 * 取得主键编号
	 * @return
	 */
	String getId();

	/**
	 * 设置创建时间
	 */
	void setCreateTime(Timestamp createTime);
	
	/**
	 * 获取创建时间
	 * @return
	 */
    Timestamp getCreateTime();

	/**
	 * 设置修改时间
	 */
	void setUpdateTime(Timestamp updateTime);
	
	/**
	 * 获取修改时间
	 * @return
	 */
	Timestamp getUpdateTime();

	/**
	 * 设置软删除，0：可用；1：删除；
	 */
	void setIsDelete(Integer isDelete);
	
	/**
	 * 获取是否删除，0：否；1：是
	 * @return
	 */
	Integer getIsDelete();
	
	/**
	 * 设置操作人ID
	 */
	void setOpUserId(String opUserId);
	
	/**
	 * 取得操作人ID
	 * @return
	 */
	String getOpUserId();
	
	/**
	 * 设置操作人
	 */
	void setOpUserName(String opUserName);
	
	/**
	 * 取得操作人
	 * @return
	 */
	String getOpUserName();
}
