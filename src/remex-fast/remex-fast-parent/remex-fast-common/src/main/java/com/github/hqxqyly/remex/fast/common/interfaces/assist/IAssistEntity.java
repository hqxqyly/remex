package com.github.hqxqyly.remex.fast.common.interfaces.assist;

import com.github.hqxqyly.remex.boot.interfaces.assist.IAssist;
import com.github.hqxqyly.remex.boot.utils.ApplicationContextUtils;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.fast.common.structure.entity.IBaseEntity;
import com.github.hqxqyly.remex.fast.common.structure.entity.fill.IEntityFillClient;

/**
 * 辅助接口 - 业务实体
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IAssistEntity extends IAssist {
	
	/**
	 * 取得设置业务实体的公共属性处理器
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	default <T extends IBaseEntity> IEntityFillClient<T> getEntityFillClient() {
		return ApplicationContextUtils.getBean(IEntityFillClient.class);
	}

	/**
	 * 创建一个新增业务的实体，默认设置一些公共属性
	 * @param <T>
	 * @return
	 */
	default <T extends IBaseEntity> T newCreateEntity(Class<T> clazz) {
		return null;
	}

	/**
	 * 创建一个新增业务的实体，默认设置一些公共属性，并复制orig属性
	 * @param <T>
	 * @param clazz
	 * @param orig
	 * @return
	 */
	default <T extends IBaseEntity> T newCreateEntity(Class<T> clazz, Object orig) {
		T entity = newInstance(clazz);
		
		if (orig != null)
			Assist.copyProperties(entity, orig);
		
		//设置公共属性
		fillCreateEntity(entity);
		
		return entity;
	}
	
	/**
	 * 设置新增业务实体的公共属性
	 * @param <T>
	 * @param entity
	 * @return
	 */
	default <T extends IBaseEntity> T fillCreateEntity(T entity) {
		getEntityFillClient().fillCreateEntity(entity);
		return entity;
	}
	
	/**
	 * 设置新增业务实体的公共属性
	 * @param <T>
	 * @param entity
	 * @param id 有值则直接用，没值就初始化
	 * @return
	 */
	default <T extends IBaseEntity> T fillCreateEntity(T entity, String id) {
		getEntityFillClient().fillCreateEntity(entity, id);
		return entity;
	}
	
	/**
	 * 创建一个修改业务的实体，默认设置一些公共属性
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	default <T extends IBaseEntity> T newUpdateEntity(Class<T> clazz) {
		return newUpdateEntity(clazz, null);
	}
	
	/**
	 * 创建一个修改业务的实体，默认设置一些公共属性，并复制orig属性
	 * @param <T>
	 * @param clazz
	 * @param orig
	 * @return
	 */
	default <T extends IBaseEntity> T newUpdateEntity(Class<T> clazz, Object orig) {
		T entity = newInstance(clazz);

		if (orig != null)
			Assist.copyProperties(entity, orig);

		//设置公共属性
		fillUpdateEntity(entity);

		return entity;
	}
	
	/**
	 * 设置修改业务实体的公共属性
	 * @param <T>
	 * @param entity
	 * @return
	 */
	default <T extends IBaseEntity> T fillUpdateEntity(T entity) {
		getEntityFillClient().fillUpdateEntity(entity);
		return entity;
	}
	
	/**
	 * 创建一个软删业务的实体，默认设置一些公共属性，并复制orig属性
	 * @param <T>
	 * @param clazz
	 * @param orig
	 * @return
	 */
	default <T extends IBaseEntity> T newDeleteEntity(Class<T> clazz, String id) {
		T entity = newInstance(clazz);
		
		//设置公共属性
		fillDeleteEntity(entity, id);
		
		return entity;
	}
	
	/**
	 * 设置软删业务实体的公共属性
	 * @param <T>
	 * @param entity
	 * @param id
	 * @return
	 */
	default <T extends IBaseEntity> T fillDeleteEntity(T entity, String id) {
		getEntityFillClient().fillDeleteEntity(entity, id);
		return entity;
	}
}
