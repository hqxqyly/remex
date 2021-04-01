package com.github.hqxqyly.remex.boot.mybatis.jdbc;

import java.util.Collection;

import org.apache.ibatis.jdbc.AbstractSQL;

import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * 扩展AbstractSQL
 * 
 * @see org.apache.ibatis.jdbc.AbstractSQL<T>
 * @see org.apache.ibatis.jdbc.SQL
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RemexSQL extends AbstractSQL<RemexSQL> {

	@Override
	public RemexSQL getSelf() {
		return this;
	}
	
	/**
	 * 拼接in条件
	 * @param vals 值列表
	 * @param sqlFieldName sql字段名
	 * @param propertyFieldName 对象属性名
	 * @return
	 */
	public RemexSQL WHERE_IN(Collection<?> vals, String sqlFieldName, String propertyFieldName) {
		StringBuilder sb = new StringBuilder();
		sb.append(" ").append(sqlFieldName).append(" in ( ");
		if (Assist.isNotEmpty(vals)) {
			for (int i = 0; i < vals.size(); i++) {
				if (i != 0)
					sb.append(", ");
				sb.append(propertyFieldName).append("[").append(i).append("]");
			}
		}
		sb.append(" ) ");
		WHERE(sb.toString());
		return this;
	}

	/**
	 * 如果val不为null，则执行{@link #WHERE(String...)}
	 * @param val 字段值
	 * @param conditions sql语句
	 * @return
	 */
	public RemexSQL IF_NOT_NULL_WHERE(Object val, String... conditions) {
		if (val != null)
			WHERE(conditions);
		return this;
	}
	
	/**
	 * 如果vals不为空，则执行{@link #WHERE_IN(Collection, String, String)}
	 * @param vals
	 * @param sqlFieldName
	 * @param propertyFieldName
	 * @return
	 */
	public RemexSQL IF_NOT_NULL_WHERE_IN(Collection<?> vals, String sqlFieldName, String propertyFieldName) {
		if (Assist.isNotEmpty(vals))
			WHERE_IN(vals, sqlFieldName, propertyFieldName);
		return this;
	}
}
