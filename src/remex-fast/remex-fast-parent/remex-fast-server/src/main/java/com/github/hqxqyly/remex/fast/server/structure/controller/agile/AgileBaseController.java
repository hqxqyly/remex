package com.github.hqxqyly.remex.fast.server.structure.controller.agile;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.github.hqxqyly.remex.fast.common.structure.entity.IBaseEntity;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.interfaces.IAgileControllerBase;

/**
 * agile基础controller
 * 
 * @author Qiaoxin.Hong
 *
 */
public class AgileBaseController<BEAN extends IBaseEntity> implements IAgileControllerBase<BEAN> {

	/** bean Class */
	protected Class<BEAN> beanClass;
	
	@SuppressWarnings("unchecked")
	public AgileBaseController() {
		//生成各泛型的class
		Type genType = getClass().getGenericSuperclass();   
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments(); 
		beanClass = (Class<BEAN>) params[0];
	}
	
	/**
	 * 取得bean Class
	 * @return
	 */
	public Class<BEAN> getBeanClass() {
		return beanClass;
	}
}
