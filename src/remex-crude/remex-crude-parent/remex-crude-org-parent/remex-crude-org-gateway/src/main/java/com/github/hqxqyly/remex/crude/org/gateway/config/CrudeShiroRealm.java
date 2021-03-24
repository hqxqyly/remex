package com.github.hqxqyly.remex.crude.org.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.hqxqyly.remex.boot.shiro.component.realm.BaseShiroRealm;
import com.github.hqxqyly.remex.crude.org.api.UserSafeApi;
import com.github.hqxqyly.remex.crude.org.entity.UserSafeEntity;
import com.github.hqxqyly.remex.crude.org.req.UserLoginReq;
import com.github.hqxqyly.remex.crude.org.rsp.PrincipalDto;

/**
 * crude shiro realm
 * 
 * @author Qiaoxin.Hong
 *
 * @param <U> 查询用户的实体
 * @param <P> 保存在shiro中的缓存实体
 */
public class CrudeShiroRealm<U extends UserSafeEntity, P extends PrincipalDto> extends BaseShiroRealm<U, P> {
	
	@Autowired
	UserSafeApi<U> userSafeApi;

	/**
	 * 查询用户
	 * @param username
	 * @return
	 */
	@Override
	protected U findUser(String username) {
		return userSafeApi.findUserByLogin(new UserLoginReq(username)).ok();
	}

	/**
	 * 取得用户密码
	 * @return
	 */
	@Override
	protected String getUserPassword(U user) {
		return user.getPassword();
	}
	
	/**
	 * 取得盐值
	 * @param user
	 * @return
	 */
	@Override
	protected String getRawSalt(U user, String username) {
		return user.getId();
	}
}
