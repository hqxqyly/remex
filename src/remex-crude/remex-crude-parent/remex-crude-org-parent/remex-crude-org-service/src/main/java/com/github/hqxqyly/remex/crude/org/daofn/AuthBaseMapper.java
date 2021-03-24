package com.github.hqxqyly.remex.crude.org.daofn;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.github.hqxqyly.remex.crude.org.entity.AuthEntity;
import com.github.hqxqyly.remex.fast.mybatis.plus.mapper.FastBaseMapper;

/**
 * 权限BaseMapper扩展
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface AuthBaseMapper<T extends AuthEntity> extends FastBaseMapper<T> {

	/**
	 * 查询某角色的权限url列表
	 * @return
	 */
	@Select("select distinct t.url from t_auth t "
			+ "join t_role_auth tra on tra.auth_id = t.id "
			+ "where t.is_delete = 0 and t.is_enabled = 1 and tra.role_id = #{roleId} "
			+ "order by t.sort, t.create_time")
	List<String> queryAuthUrlListByRole(@Param("roleId") String roleId);
	
	/**
	 * 查询某角色的权限编号列表
	 * @return
	 */
	@Select("select distinct t.code from t_auth t "
			+ "join t_role_auth tra on tra.auth_id = t.id "
			+ "where t.is_delete = 0 and t.is_enabled = 1 and tra.role_id = #{roleId} "
			+ "order by t.sort, t.create_time")
	List<String> queryAuthCodeListByRole(@Param("roleId") String roleId);
}
