package com.github.hqxqyly.remex.boot.mybatis.plus.injector.methods;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.github.hqxqyly.remex.boot.mybatis.plus.constant.RemexSqlMethod;

/**
 * mybatis plus全局公共方法 - 根据ID真实删除数据
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RealDeleteById extends AbstractMethod {

	@Override
	public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
		RemexSqlMethod sqlMethod = RemexSqlMethod.REAL_DELETE_BY_ID;
		String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), tableInfo.getKeyColumn(),
				tableInfo.getKeyProperty());
		SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, Object.class);
		return this.addDeleteMappedStatement(mapperClass, sqlMethod.getMethod(), sqlSource);
	}
}
