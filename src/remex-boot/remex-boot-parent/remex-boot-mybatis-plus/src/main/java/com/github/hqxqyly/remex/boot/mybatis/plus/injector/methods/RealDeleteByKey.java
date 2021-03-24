package com.github.hqxqyly.remex.boot.mybatis.plus.injector.methods;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.github.hqxqyly.remex.boot.mybatis.plus.constant.RemexSqlMethod;

/**
 * mybatis plus全局公共方法 - 根据某字段值真实删除数据
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RealDeleteByKey extends BaseMethod {

	@Override
	public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
		RemexSqlMethod sqlMethod = RemexSqlMethod.REAL_DELETE_BY_KEY;
		String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName());
		SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, Object.class);

		return addDeleteMappedStatement(mapperClass, sqlMethod.getMethod(), sqlSource);
	}
}
