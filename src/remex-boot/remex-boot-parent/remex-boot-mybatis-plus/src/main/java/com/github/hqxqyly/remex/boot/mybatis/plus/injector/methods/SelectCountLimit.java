package com.github.hqxqyly.remex.boot.mybatis.plus.injector.methods;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.github.hqxqyly.remex.boot.mybatis.plus.constant.RemexSqlMethod;

/**
 * mybatis plus全局公共方法 - 查询记录数，限制在一定数量范围内，避免全量查询
 * 
 * @author Qiaoxin.Hong
 *
 */
public class SelectCountLimit extends BaseMethod {

	@Override
	public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
		RemexSqlMethod sqlMethod = RemexSqlMethod.SELECT_COUNT_LIMIT;
        String sql = String.format(sqlMethod.getSql(), sqlFirst(), sqlCount(), tableInfo.getTableName(),
            sqlWhereEntityWrapper(true, tableInfo), sqlComment());
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addSelectMappedStatementForOther(mapperClass, sqlMethod.getMethod(), sqlSource, Integer.class);
	}
}
