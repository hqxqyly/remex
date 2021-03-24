package com.github.hqxqyly.remex.fast.server.structure.controller.agile.interfaces;

import com.github.hqxqyly.remex.boot.mybatis.plus.utils.MybatisPlusUtils;
import com.github.hqxqyly.remex.fast.common.structure.entity.IBaseEntity;
import com.github.hqxqyly.remex.fast.mybatis.plus.mapper.FastBaseMapper;
import com.github.hqxqyly.remex.fast.server.structure.controller.IBaseController;

/**
 * agile controller基础插件
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IAgileControllerBase<BEAN extends IBaseEntity> extends IBaseController {

	/**
	 * 取得bean Class
	 * @return
	 */
	Class<BEAN> getBeanClass();
	
	/**
	 * 取得dao
	 * @return
	 */
	default FastBaseMapper<BEAN> getDao() {
		return MybatisPlusUtils.getDao(getBeanClass());
	}
	
	/**
	 * 验证bean id
	 * @param bean
	 */
	default void validateBeanId(BEAN bean) {
		validateNotBlank(bean.getId(), "id");
	}
}
