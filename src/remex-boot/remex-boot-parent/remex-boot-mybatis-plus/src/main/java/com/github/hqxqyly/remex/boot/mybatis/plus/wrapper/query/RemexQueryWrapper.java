package com.github.hqxqyly.remex.boot.mybatis.plus.wrapper.query;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.SharedString;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.github.hqxqyly.remex.boot.constant.BConst;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * 扩展QueryWrapper
 * 
 * @see com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<T>
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RemexQueryWrapper<T> extends AbstractWrapper<T, String, RemexQueryWrapper<T>> 
	implements Query<RemexQueryWrapper<T>, T, String> {
	
	private static final long serialVersionUID = 1L;
	/**
     * 查询字段
     */
    private SharedString sqlSelect = new SharedString();

    public RemexQueryWrapper() {
        this(null);
    }

    public RemexQueryWrapper(T entity) {
        super.setEntity(entity);
        super.initNeed();
    }

    public RemexQueryWrapper(T entity, String... columns) {
        super.setEntity(entity);
        super.initNeed();
        this.select(columns);
    }

    /**
     * 非对外公开的构造方法,只用于生产嵌套 sql
     *
     * @param entityClass 本不应该需要的
     */
    private RemexQueryWrapper(T entity, Class<T> entityClass, AtomicInteger paramNameSeq,
                         Map<String, Object> paramNameValuePairs, MergeSegments mergeSegments,
                         SharedString lastSql, SharedString sqlComment, SharedString sqlFirst) {
        super.setEntity(entity);
        super.setEntityClass(entityClass);
        this.paramNameSeq = paramNameSeq;
        this.paramNameValuePairs = paramNameValuePairs;
        this.expression = mergeSegments;
        this.lastSql = lastSql;
        this.sqlComment = sqlComment;
        this.sqlFirst = sqlFirst;
    }

    @Override
    public RemexQueryWrapper<T> select(String... columns) {
        if (ArrayUtils.isNotEmpty(columns)) {
            this.sqlSelect.setStringValue(String.join(StringPool.COMMA, columns));
        }
        return typedThis;
    }

    @Override
    public RemexQueryWrapper<T> select(Class<T> entityClass, Predicate<TableFieldInfo> predicate) {
        super.setEntityClass(entityClass);
        this.sqlSelect.setStringValue(TableInfoHelper.getTableInfo(getEntityClass()).chooseSelect(predicate));
        return typedThis;
    }

    @Override
    public String getSqlSelect() {
        return sqlSelect.getStringValue();
    }

    /**
     * 返回一个支持 lambda 函数写法的 wrapper
     */
    public RemexQueryWrapper<T> lambda() {
    	return new RemexQueryWrapper<>(getEntity(), getEntityClass(), paramNameSeq, paramNameValuePairs, new MergeSegments(),
                SharedString.emptyString(), SharedString.emptyString(), SharedString.emptyString());
    }

    /**
     * 用于生成嵌套 sql
     * <p>
     * 故 sqlSelect 不向下传递
     * </p>
     */
    @Override
    protected RemexQueryWrapper<T> instance() {
        return new RemexQueryWrapper<>(getEntity(), getEntityClass(), paramNameSeq, paramNameValuePairs, new MergeSegments(),
            SharedString.emptyString(), SharedString.emptyString(), SharedString.emptyString());
    }

    @Override
    public void clear() {
        super.clear();
        sqlSelect.toNull();
    }
    
    
    
    
    
    
    
    /**
	 * 如果值不为null，则{@link #eq(Object, Object)}
	 * @param column
	 * @param val
	 * @return
	 */
	public RemexQueryWrapper<T> ifNotNullEq(String column, Object val) {
		if (val != null)
			eq(column, val);
		return this;
	}
	
	/**
	 * 如果值不为null，则{@link #ge(Object, Object)}
	 * @param column
	 * @param val
	 * @return
	 */
	public RemexQueryWrapper<T> ifNotNullGe(String column, Object val) {
		if (val != null)
			ge(column, val);
		return this;
	}
	
	/**
	 * 如果值不为null，则{@link #lt(Object, Object)}
	 * @param column
	 * @param val
	 * @return
	 */
	public RemexQueryWrapper<T> ifNotNullLt(String column, Object val) {
		if (val != null)
			lt(column, val);
		return this;
	}
	
	/**
	 * 如果值不为null且不为0，则{@link #eq(Object, Object)}
	 * @param column
	 * @param val
	 * @return
	 */
	public RemexQueryWrapper<T> ifNotNullZeroEq(String column, Integer val) {
		if (val != null && !BConst.ZERO.equals(val))
			eq(column, val);
		return this;
	}
	
	/**
	 * 如果值不为blank，则{@link #eq(Object, Object)}
	 * @param column
	 * @param val
	 * @return
	 */
	public RemexQueryWrapper<T> ifNotBlankEq(String column, String val) {
		if (Assist.isNotBlank(val))
			eq(column, val);
		return this;
	}
	
	/**
	 * 如果值不为blank，则{@link #like(Object, Object)}
	 * @param column
	 * @param val
	 * @return
	 */
	public RemexQueryWrapper<T> ifNotBlankLike(String column, String val) {
		if (Assist.isNotBlank(val))
			like(column, val);
		return this;
	}
	
	/**
	 * 如果值不为blank，则{@link #like(Object, Object)}
	 * @param column
	 * @param val
	 * @return
	 */
	public RemexQueryWrapper<T> ifNotBlankLike(String column1, String column2, String val) {
		if (Assist.isNotBlank(val))
			and(wrapper -> wrapper.like(column1, val).or().like(column2, val));
		return this;
	}
	
	/**
	 * 如果值不为empty，则{@link #in(Object, Collection)}
	 * @param column
	 * @param val
	 * @return
	 */
	public RemexQueryWrapper<T> ifNotEmptyIn(String column, Collection<?> vals) {
		if (Assist.isNotEmpty(vals))
			in(column, vals);
		return this;
	}
}
