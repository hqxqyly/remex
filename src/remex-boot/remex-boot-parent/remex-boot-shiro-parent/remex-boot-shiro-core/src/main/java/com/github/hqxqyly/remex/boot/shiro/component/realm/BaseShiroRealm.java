package com.github.hqxqyly.remex.boot.shiro.component.realm;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.github.hqxqyly.remex.boot.shiro.utils.ShiroUtils;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * 登录权限登录器基础类，建议不注入service的bean，会导致aop、事务的失效
 * 
 * @author Qiaoxin.Hong
 *
 * @param <U> 查询用户的实体
 * @param <P> 保存在shiro中的缓存实体
 */
public abstract class BaseShiroRealm<U, P> extends RemexAuthorizingRealm {
	
	/** 查询用户的实体class */
	protected Class<U> userClass;
	
	/** 保存在shiro中的缓存实体class */
	protected Class<P> principalClass;
	
	@SuppressWarnings("unchecked")
	public BaseShiroRealm() {
		//生成各泛型的class
		Type genType = getClass().getGenericSuperclass();   
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments(); 
		userClass = (Class<U>) params[0];
		principalClass = (Class<P>) params[1];
	}

	/**
	 * 认证登录
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		
		//查询用户
		U userDto = findUser(upToken.getUsername());
		if (userDto == null) {
			throw new UnknownAccountException("user not exists");
		}
		
		//取得密码
		String password = getUserPassword(userDto);
		//密码盐值
		String salt = getRawSalt(userDto, upToken.getUsername());
		//加密盐值
		ByteSource saltByteSource = ShiroUtils.encryptSalt(salt);
		
		//转换保存在shiro中的缓存实体
		P shiroUserInfoDto = toShiroUserInfo(userDto); 
		AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(shiroUserInfoDto, password, saltByteSource, getName());
		
		return authenticationInfo;
	}
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		@SuppressWarnings("unchecked")
		//取得登录信息
		P shiroUserInfoDto = (P) getAvailablePrincipal(principals);
		//取得权限列表
		List<String> permissions = Assist.defaultList(getPermissions(shiroUserInfoDto));
		info.addStringPermissions(permissions);
		
		return info;
	}
	
	/**
	 * 取得授权缓存的key
	 */
	@Override
	protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
		return ShiroUtils.getSessionId();
	}
	
	/**
	 * 查询用户
	 * @param username
	 * @return
	 */
	protected abstract U findUser(String username);
	
	/**
	 * 转换保存在shiro中的缓存实体
	 * @param user
	 * @return
	 */
	protected P toShiroUserInfo(U user) {
		return Assist.toBean(user, principalClass);
	}
	
	/**
	 * 取得用户密码
	 * @return
	 */
	protected abstract String getUserPassword(U user);
	
	/**
	 * 取得权限列表
	 * @param shiroUserInfo 保存在shiro中的缓存实体
	 * @return
	 */
	protected List<String> getPermissions(P shiroUserInfo) {
		return null;
	}
	
	/**
	 * 取得盐值
	 * @param user
	 * @return
	 */
	protected String getRawSalt(U user, String username) {
		return username;
	}
	
	
	
	
	
	
	
	
	

	public Class<U> getUserClass() {
		return userClass;
	}

	public void setUserClass(Class<U> userClass) {
		this.userClass = userClass;
	}

	public Class<P> getPrincipalClass() {
		return principalClass;
	}

	public void setPrincipalClass(Class<P> principalClass) {
		this.principalClass = principalClass;
	}
}
