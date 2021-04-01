package com.github.hqxqyly.remex.boot.mybatis.plus.mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.hqxqyly.remex.boot.exception.RemexServerException;
import com.github.hqxqyly.remex.boot.msg.IMsgEnum;
import com.github.hqxqyly.remex.boot.msg.MsgBasicEnum;
import com.github.hqxqyly.remex.boot.mybatis.plus.utils.MybatisPlusUtils;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * 扩展BaseMethod，提供一些新的方法
 * 
 * @see com.github.hqxqyly.remex.boot.mybatis.plus.injector.methods.BaseMethod
 * 
 * @author Qiaoxin.Hong
 *
 * @param <T>
 */
public interface RemexBaseMapper<T> extends BaseMapper<T> {
	
	/**
	 * 查询记录数，限制在一定数量范围内，避免全量查询，需要关闭IPage.setSearchCount
	 * @return
	 */
	<E extends IPage<T>> Integer selectCountLimit(E page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
	
	/**
	 * 根据某字段值真实删除数据
	 * @param key
	 * @param value
	 * @return
	 */
	int realDeleteByKey(@Param("key") String key, @Param("value") Object value);
	
	/**
     * 根据ID真实删除
     *
     * @param id 主键ID
     */
    int realDeleteById(Serializable id);
	
	
	
	
	
    /**
	 * 创建条件
	 * @return
	 */
	default QueryWrapper<T> newWrapper() {
		return new QueryWrapper<>();
	}
	
	/**
	 * 创建条件
	 * @return
	 */
	default QueryWrapper<T> newWrapper(String key, Object value) {
		return newWrapper().eq(key, value);
	}
	
	/**
	 * 创建条件
	 * @return
	 */
	default QueryWrapper<T> newWrapper(T entity) {
		return new QueryWrapper<T>(entity);
	}
	
	/**
	 * 创建条件
	 * @return
	 */
	default LambdaQueryWrapper<T> newLambdaWrapper() {
		return new LambdaQueryWrapper<>();
	}
	
	/**
	 * 创建条件
	 * @return
	 */
	default LambdaQueryWrapper<T> newLambdaWrapper(SFunction<T, ?> key, Object value) {
		return newLambdaWrapper().eq(key, value);
	}
	
	/**
	 * 创建分页条件
	 * @return
	 */
	default Page<T> newPageReq(int pageNum, int pageSize) {
		return new Page<T>(pageNum, pageSize);
	}
	
	/**
	 * 创建分页条件，默认第一页
	 * @return
	 */
	default Page<T> newPageReq(int pageSize) {
		return newPageReq(1, pageSize);
	}
	
	
	
	/**
	 * 根据某字段值查询数据列表
	 * @return
	 */
	default List<T> selectListByKey(String key, Object value) {
		return selectList(newWrapper(key, value));
	}
	
	/**
	 * 根据某字段值查询数据列表，并转换对象
	 * @return
	 */
	default <DTO> List<DTO> selectListByKey(Class<DTO> dtoClass, String key, Object value) {
		return Assist.toBeanList(selectListByKey(key, value), dtoClass);
	}
	
	/**
	 * 根据某字段值查询数据列表
	 * @return
	 */
	default List<T> selectListByKey(String key1, Object value1, String key2, Object value2) {
		return selectList(newWrapper(key1, value1).eq(key2, value2));
	}
	
	/**
	 * 根据某字段值查询数据列表，并转换对象
	 * @return
	 */
	default <DTO> List<DTO> selectListByKey(Class<DTO> dtoClass, String key1, Object value1, String key2, Object value2) {
		return Assist.toBeanList(selectListByKey(key1, value1, key2, value2), dtoClass);
	}
	
	/**
	 * 根据某字段值查询数据列表
	 * @return
	 */
	default List<T> selectListByKey(String key1, Object value1, String key2, Object value2, String key3, Object value3) {
		return selectList(newWrapper(key1, value1).eq(key2, value2).eq(key3, value3));
	}
	
	/**
	 * 根据某字段值查询数据列表，并转换对象
	 * @return
	 */
	default <DTO> List<DTO> selectListByKey(Class<DTO> dtoClass, String key1, Object value1, String key2, Object value2, String key3, Object value3) {
		return Assist.toBeanList(selectListByKey(key1, value1, key2, value2, key3, value3), dtoClass);
	}
	
	/**
	 * 根据某字段值查询数据列表并升序
	 * @return
	 */
	default List<T> selectListByKeyAsc(String key, Object value, String order) {
		return selectList(newWrapper(key, value).orderByAsc(order));
	}
	
	/**
	 * 根据某字段值查询数据列表并升序，并转换对象
	 * @return
	 */
	default <DTO> List<DTO> selectListByKeyAsc(Class<DTO> dtoClass, String key, Object value, String order) {
		return Assist.toBeanList(selectListByKeyAsc(key, value, order), dtoClass);
	}
	
	/**
	 * 根据某字段值查询数据列表并降序
	 * @return
	 */
	default List<T> selectListByKeyDesc(String key, Object value, String order) {
		return selectList(newWrapper(key, value).orderByDesc(order));
	}
	
	/**
	 * 根据某字段值查询数据列表并降序，并转换对象
	 * @return
	 */
	default <DTO> List<DTO> selectListByKeyDesc(Class<DTO> dtoClass, String key, Object value, String order) {
		return Assist.toBeanList(selectListByKeyDesc(key, value, order), dtoClass);
	}
	
	/**
	 * 根据某字段值查询数据列表
	 * @return
	 */
	default List<T> selectListByKey(SFunction<T, ?> key, Object value) {
		return selectList(newLambdaWrapper(key, value));
	}
	
	/**
	 * 根据某字段值查询数据列表，并转换对象
	 * @return
	 */
	default <DTO> List<DTO> selectListByKey(Class<DTO> dtoClass, SFunction<T, ?> key, Object value) {
		return Assist.toBeanList(selectListByKey(key, value), dtoClass);
	}
	
	/**
	 * 根据某字段值查询数据列表
	 * @return
	 */
	default List<T> selectListByKey(SFunction<T, ?> key1, Object value1, SFunction<T, ?> key2, Object value2) {
		return selectList(newLambdaWrapper(key1, value1).eq(key2, value2));
	}
	
	/**
	 * 根据某字段值查询数据列表，并转换对象
	 * @return
	 */
	default <DTO> List<DTO> selectListByKey(Class<DTO> dtoClass, SFunction<T, ?> key1, Object value1, SFunction<T, ?> key2, Object value2) {
		return Assist.toBeanList(selectListByKey(key1, value1, key2, value2), dtoClass);
	}
	
	/**
	 * 根据某字段值查询数据列表
	 * @return
	 */
	default List<T> selectListByKey(SFunction<T, ?> key1, Object value1, SFunction<T, ?> key2, Object value2, SFunction<T, ?> key3, Object value3) {
		return selectList(newLambdaWrapper(key1, value1).eq(key2, value2).eq(key3, value3));
	}
	
	/**
	 * 根据某字段值查询数据列表，并转换对象
	 * @return
	 */
	default <DTO> List<DTO> selectListByKey(Class<DTO> dtoClass, SFunction<T, ?> key1, Object value1, SFunction<T, ?> key2, Object value2, SFunction<T, ?> key3, Object value3) {
		return Assist.toBeanList(selectListByKey(key1, value1, key2, value2, key3, value3), dtoClass);
	}
	
	/**
	 * 根据某字段值查询数据列表并升序
	 * @return
	 */
	default List<T> selectListByKeyAsc(SFunction<T, ?> key, Object value, SFunction<T, ?> order) {
		return selectList(newLambdaWrapper(key, value).orderByAsc(order));
	}
	
	/**
	 * 根据某字段值查询数据列表并升序，并转换对象
	 * @return
	 */
	default <DTO> List<DTO> selectListByKeyAsc(Class<DTO> dtoClass, SFunction<T, ?> key, Object value, SFunction<T, ?> order) {
		return Assist.toBeanList(selectListByKeyAsc(key, value, order), dtoClass);
	}
	
	/**
	 * 根据某字段值查询数据列表并降序
	 * @return
	 */
	default List<T> selectListByKeyDesc(SFunction<T, ?> key, Object value, SFunction<T, ?> order) {
		return selectList(newLambdaWrapper(key, value).orderByDesc(order));
	}
	
	/**
	 * 根据某字段值查询数据列表并降序，并转换对象
	 * @return
	 */
	default <DTO> List<DTO> selectListByKeyDesc(Class<DTO> dtoClass, SFunction<T, ?> key, Object value, SFunction<T, ?> order) {
		return Assist.toBeanList(selectListByKeyDesc(key, value, order), dtoClass);
	}
	
	/**
	 * 查询所有数据列表
	 * @return
	 */
	default List<T> selectListAll() {
		return selectList(null);
	}
	
	/**
	 * 查询所有数据列表，并转换对象
	 * @return
	 */
	default <DTO> List<DTO> selectListAll(Class<DTO> dtoClass) {
		return Assist.toBeanList(selectListAll(), dtoClass);
	}
	
	/**
	 * 查询所有数据列表并升序
	 * @return
	 */
	default List<T> selectListAllAsc(String order) {
		return selectList(newWrapper().orderByAsc(order));
	}
	
	/**
	 * 查询所有数据列表并降序
	 * @return
	 */
	default List<T> selectListAllDesc(String order) {
		return selectList(newWrapper().orderByAsc(order));
	}
	
	/**
	 * 查询所有数据列表并升序
	 * @return
	 */
	default List<T> selectListAllAsc(SFunction<T, ?> order) {
		return selectList(newLambdaWrapper().orderByAsc(order));
	}
	
	/**
	 * 查询所有数据列表并降序
	 * @return
	 */
	default List<T> selectListAllDesc(SFunction<T, ?> order) {
		return selectList(newLambdaWrapper().orderByAsc(order));
	}
	
	/**
	 * 根据条件查询数据列表，并转换对象
	 * @return
	 */
	default <DTO> List<DTO> selectList(Class<DTO> dtoClass, Wrapper<T> queryWrapper) {
		return Assist.toBeanList(selectList(queryWrapper), dtoClass);
	}
	
	/**
	 * 根据条件查询数据列表
	 * @param entity
	 * @return
	 */
	default List<T> selectListByEntity(T entity) {
		return selectList(newWrapper(entity));
	}
	
	/**
	 * 根据条件查询数据列表，并转换对象
	 * @param entity
	 * @return
	 */
	default <DTO> List<DTO> selectListByEntity(Class<DTO> dtoClass, T entity) {
		return Assist.toBeanList(selectListByEntity(entity), dtoClass);
	}
	
	/**
     * 查询（根据ID 批量查询），并转换对象
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
	default <DTO> List<DTO> selectBatchIds(Class<DTO> dtoClass, Collection<? extends Serializable> idList) {
		return Assist.toBeanList(selectBatchIds(idList), dtoClass);
	}
	
	/**
     * 查询（根据 columnMap 条件），并转换对象
     *
     * @param columnMap 表字段 map 对象
     */
	default <DTO> List<DTO> selectByMap(Class<DTO> dtoClass, Map<String, Object> columnMap) {
		return Assist.toBeanList(selectByMap(columnMap), dtoClass);
	}
	
	
	
	/**
	 * 根据某字段值查询单条数据
	 * @return
	 */
	default T selectOneByKey(String key, Object value) {
		return selectOne(newWrapper(key, value));
	}
	
	/**
	 * 根据某字段值查询单条数据，并转换对象
	 * @return
	 */
	default <DTO> DTO selectOneByKey(Class<DTO> dtoClass, String key, Object value) {
		return Assist.toBean(selectOneByKey(key, value), dtoClass);
	}
	
	/**
	 * 根据某字段值查询单条数据
	 * @return
	 */
	default T selectOneByKey(String key1, Object value1, String key2, Object value2) {
		return selectOne(newWrapper(key1, value1).eq(key2, value2));
	}
	
	/**
	 * 根据某字段值查询单条数据，并转换对象
	 * @return
	 */
	default <DTO> DTO selectOneByKey(Class<DTO> dtoClass, String key1, Object value1, String key2, Object value2) {
		return Assist.toBean(selectOneByKey(key1, value1, key2, value2), dtoClass);
	}
	
	/**
	 * 根据某字段值查询单条数据
	 * @return
	 */
	default T selectOneByKey(String key1, Object value1, String key2, Object value2, String key3, Object value3) {
		return selectOne(newWrapper(key1, value1).eq(key2, value2).eq(key2, value3));
	}
	
	/**
	 * 根据某字段值查询单条数据，并转换对象
	 * @return
	 */
	default <DTO> DTO selectOneByKey(Class<DTO> dtoClass, String key1, Object value1, String key2, Object value2, String key3, Object value3) {
		return Assist.toBean(selectOneByKey(key1, value1, key2, value2, key3, value3), dtoClass);
	}
	
	/**
	 * 根据某字段值查询单条数据
	 * @return
	 */
	default T selectOneByKey(SFunction<T, ?> key, Object value) {
		return selectOne(newLambdaWrapper(key, value));
	}
	
	/**
	 * 根据某字段值查询单条数据，并转换对象
	 * @return
	 */
	default <DTO> DTO selectOneByKey(Class<DTO> dtoClass, SFunction<T, ?> key, Object value) {
		return Assist.toBean(selectOneByKey(key, value), dtoClass);
	}
	
	/**
	 * 根据某字段值查询单条数据
	 * @return
	 */
	default T selectOneByKey(SFunction<T, ?> key1, Object value1, SFunction<T, ?> key2, Object value2) {
		return selectOne(newLambdaWrapper(key1, value1).eq(key2, value2));
	}
	
	/**
	 * 根据某字段值查询单条数据，并转换对象
	 * @return
	 */
	default <DTO> DTO selectOneByKey(Class<DTO> dtoClass, SFunction<T, ?> key1, Object value1, SFunction<T, ?> key2, Object value2) {
		return Assist.toBean(selectOneByKey(key1, value1, key2, value2), dtoClass);
	}
	
	/**
	 * 根据某字段值查询单条数据
	 * @return
	 */
	default T selectOneByKey(SFunction<T, ?> key1, Object value1, SFunction<T, ?> key2, Object value2, SFunction<T, ?> key3, Object value3) {
		return selectOne(newLambdaWrapper(key1, value1).eq(key2, value2).eq(key2, value3));
	}
	
	/**
	 * 根据某字段值查询单条数据，并转换对象
	 * @return
	 */
	default <DTO> DTO selectOneByKey(Class<DTO> dtoClass, SFunction<T, ?> key1, Object value1, SFunction<T, ?> key2, Object value2, SFunction<T, ?> key3, Object value3) {
		return Assist.toBean(selectOneByKey(key1, value1, key2, value2, key3, value3), dtoClass);
	}
	
	/**
     * 根据 entity 条件，查询一条记录，并转换对象
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
	default <DTO> DTO selectOne(Class<DTO> dtoClass, Wrapper<T> queryWrapper) {
		return Assist.toBean(selectOne(queryWrapper), dtoClass);
	}
	
	/**
     * 根据 ID 查询，并转换对象
     *
     * @param id 主键ID
     */
	default <DTO> DTO selectById(Class<DTO> dtoClass, Serializable id) {
		return Assist.toBean(selectById(id), dtoClass);
    }
	
	
	
	/**
	 * 根据条件分页查询数据列表
	 * @param entity
	 * @return
	 */
	default IPage<T> selectPageByEntity(IPage<T> page, T entity) {
		return selectPage(page, newWrapper(entity));
	}
	
	/**
	 * 根据条件分页查询数据列表，并转换对象
	 * @return
	 */
	default <DTO> IPage<DTO> selectPageByEntity(Class<DTO> dtoClass, IPage<T> page, T entity) {
		return MybatisPlusUtils.toPage(selectPageByEntity(page, entity), dtoClass);
	}
	
	/**
	 * 根据条件分页查询数据列表
	 * @return
	 */
	default IPage<T> selectPage(int pageNum, int pageSize) {
		return selectPage(pageNum, pageSize, null);
	}
	
	/**
	 * 根据条件分页查询数据列表
	 * @return
	 */
	default IPage<T> selectPage(int pageNum, int pageSize, Wrapper<T> queryWrapper) {
    	return selectPage(newPageReq(pageNum, pageSize), queryWrapper);
    }
	
	/**
	 * 根据条件分页查询数据列表，并转换对象
	 * @return
	 */
	default <DTO> IPage<DTO> selectPage(Class<DTO> dtoClass, int pageNum, int pageSize) {
		return selectPage(dtoClass, pageNum, pageSize, null);
	}
	
	/**
	 * 根据条件分页查询数据列表，并转换对象
	 * @return
	 */
	default <DTO> IPage<DTO> selectPage(Class<DTO> dtoClass, int pageNum, int pageSize, Wrapper<T> queryWrapper) {
    	IPage<T> resultPage = selectPage(pageNum, pageSize, queryWrapper);
		return MybatisPlusUtils.toPage(resultPage, dtoClass);
    }
	
	
	
	/**
	 * 根据某字段值修改数据
	 * @return
	 */
	default int updateByKey(T entity, String key, Object value) {
		return update(entity, newWrapper(key, value));
	}
	
	/**
	 * 根据某字段值修改数据
	 * @return
	 */
	default int updateByKey(T entity, SFunction<T, ?> key, Object value) {
		return update(entity, newLambdaWrapper(key, value));
	}
	

	
	/**
	 * 根据某字段值删除数据
	 * @param key
	 * @param value
	 * @return
	 */
	default int deleteByKey(String key, Object value) {
		return delete(newWrapper(key, value));
	}
	
	/**
	 * 根据某字段值删除数据
	 * @param key
	 * @param value
	 * @return
	 */
	default int deleteByKey(SFunction<T, ?> key, Object value) {
		return delete(newLambdaWrapper(key, value));
	}
	
	
	
	/**
	 * 根据某字段值断言必须存在数据，否则抛出异常
	 */
	default void assertExistByKey(String key, Object value) {
		assertExistByKey(key, value, MsgBasicEnum.DATA_NOT_EXISTS);
	}
	
	/**
	 * 根据某字段值断言必须存在数据，否则抛出异常
	 */
	default void assertExistByKey(String key, Object value, IMsgEnum msgEnum, Object...msgArgs) {
		if (existByKey(key, value))
			throw new RemexServerException(msgEnum, msgArgs);
	}
	
	/**
	 * 根据某字段值断言不能存在数据，否则抛出异常
	 */
	default void assertNotExistByKey(String key, Object value) {
		assertNotExistByKey(key, value, MsgBasicEnum.DATA_ALREADY_EXISTS);
	}
	
	/**
	 * 根据某字段值断言不能存在数据，否则抛出异常
	 */
	default void assertNotExistByKey(String key, Object value, IMsgEnum msgEnum, Object...msgArgs) {
		if (!existByKey(key, value))
			throw new RemexServerException(msgEnum, msgArgs);
	}
	
	/**
	 * 根据某字段值断言必须存在数据，否则抛出异常
	 */
	default void assertExistByKey(SFunction<T, ?> key, Object value) {
		assertExistByKey(key, value, MsgBasicEnum.DATA_NOT_EXISTS);
	}
	
	/**
	 * 根据某字段值断言必须存在数据，否则抛出异常
	 */
	default void assertExistByKey(SFunction<T, ?> key, Object value, IMsgEnum msgEnum, Object...msgArgs) {
		if (existByKey(key, value))
			throw new RemexServerException(msgEnum, msgArgs);
	}
	
	/**
	 * 根据某字段值断言不能存在数据，否则抛出异常
	 */
	default void assertNotExistByKey(SFunction<T, ?> key, Object value) {
		assertNotExistByKey(key, value, MsgBasicEnum.DATA_ALREADY_EXISTS);
	}
	
	/**
	 * 根据某字段值断言不能存在数据，否则抛出异常
	 */
	default void assertNotExistByKey(SFunction<T, ?> key, Object value, IMsgEnum msgEnum, Object...msgArgs) {
		if (!existByKey(key, value))
			throw new RemexServerException(msgEnum, msgArgs);
	}
	
	/**
	 * 根据ID断言必须存在数据，否则抛出异常
	 */
	default T assertExistById(String id) {
		return assertExistById(id, MsgBasicEnum.DATA_NOT_EXISTS);
	}

	/**
	 * 根据ID断言必须存在数据，否则抛出异常
	 */
	default T assertExistById(String id, IMsgEnum msgEnum, Object...msgArgs) {
		T entity = selectById(id);
		if (entity == null)
			throw new RemexServerException(msgEnum, msgArgs);
		return entity;
	}
	
	/**
	 * 根据ID断言不能存在数据，否则抛出异常
	 */
	default void assertNotExistById(String id) {
		assertNotExistById(id, MsgBasicEnum.DATA_ALREADY_EXISTS);
	}
	
	/**
	 * 根据ID断言不能存在数据，否则抛出异常
	 */
	default void assertNotExistById(String id, IMsgEnum msgEnum, Object...msgArgs) {
		if (selectById(id) != null)
			throw new RemexServerException(msgEnum, msgArgs);
	}
	
	/**
	 * 根据某字段值断言必须存在数据，有则返回数据，没有则抛出异常
	 */
	default T assertExistSelectOneByKey(String key, Object value) {
		return assertExistSelectOneByKey(key, value, MsgBasicEnum.DATA_NOT_EXISTS);
	}

	/**
	 * 根据某字段值断言必须存在数据，有则返回数据，没有则抛出异常
	 */
	default T assertExistSelectOneByKey(String key, Object value, IMsgEnum msgEnum, Object...msgArgs) {
		T result = selectOneByKey(key, value);
		Assist.notNull(result, msgEnum, msgArgs);
		return result;
	}
	
	/**
	 * 根据某字段值断言必须存在数据，有则返回数据，没有则抛出异常
	 */
	default List<T> assertExistSelectByKey(String key, Object value) {
		return assertExistSelectByKey(key, value, MsgBasicEnum.DATA_NOT_EXISTS);
	}

	/**
	 * 根据某字段值断言必须存在数据，有则返回数据，没有则抛出异常
	 */
	default List<T> assertExistSelectByKey(String key, Object value, IMsgEnum msgEnum, Object...msgArgs) {
		List<T> result = selectListByKey(key, value);
		Assist.notEmpty(result, msgEnum, msgArgs);
		return result;
	}
	
	/**
	 * 根据某字段值断言必须存在数据，有则返回数据，没有则抛出异常
	 */
	default T assertExistSelectOneByKey(SFunction<T, ?> key, Object value) {
		return assertExistSelectOneByKey(key, value, MsgBasicEnum.DATA_NOT_EXISTS);
	}

	/**
	 * 根据某字段值断言必须存在数据，有则返回数据，没有则抛出异常
	 */
	default T assertExistSelectOneByKey(SFunction<T, ?> key, Object value, IMsgEnum msgEnum, Object...msgArgs) {
		T result = selectOneByKey(key, value);
		Assist.notNull(result, msgEnum, msgArgs);
		return result;
	}
	
	/**
	 * 根据某字段值断言必须存在数据，有则返回数据，没有则抛出异常
	 */
	default List<T> assertExistSelectByKey(SFunction<T, ?> key, Object value) {
		return assertExistSelectByKey(key, value, MsgBasicEnum.DATA_NOT_EXISTS);
	}

	/**
	 * 根据某字段值断言必须存在数据，有则返回数据，没有则抛出异常
	 */
	default List<T> assertExistSelectByKey(SFunction<T, ?> key, Object value, IMsgEnum msgEnum, Object...msgArgs) {
		List<T> result = selectListByKey(key, value);
		Assist.notEmpty(result, msgEnum, msgArgs);
		return result;
	}
	
	/**
	 * 根据ID修改，无记录修改成功则抛出异常
	 * @param entity
	 * @param msgEnum
	 * @param msgArgs
	 */
	default int assertUpdateById(T entity) {
		int count = updateById(entity);
		if (count == 0)
			throw Assist.newException(MsgBasicEnum.DATA_NOT_EXISTS);
		return count;
	}
	
	
	
	/**
	 * 根据某字段值判断是否存在数据
	 * @param key
	 * @param value
	 * @return
	 */
	default boolean existByKey(String key, Object value) {
		return selectCountLimit(1, newWrapper(key, value)) != 0;
	}
	
	/**
	 * 根据某字段值判断是否存在数据
	 * @param key
	 * @param value
	 * @return
	 */
	default boolean existByKey(SFunction<T, ?> key, Object value) {
		return selectCountLimit(1, newLambdaWrapper(key, value)) != 0;
	}
	
	/**
	 * 查询记录数，限制在一定数量范围内，避免全量查询
	 * @return
	 */
	default Integer selectCountLimit(int pageSize, Wrapper<T> queryWrapper) {
		Page<T> page = newPageReq(pageSize);
		page.setSearchCount(false);
		return selectCountLimit(page, queryWrapper);
	}
}
