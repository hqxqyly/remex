package com.github.hqxqyly.remex.boot.shiro.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.github.hqxqyly.remex.boot.interfaces.assist.IAssistConfig;
import com.github.hqxqyly.remex.boot.shiro.common.config.ShiroCommonAutoConfiguration;
import com.github.hqxqyly.remex.boot.shiro.common.properties.ShiroProperties;
import com.github.hqxqyly.remex.boot.shiro.component.access.ShiroSessionManager;
import com.github.hqxqyly.remex.boot.shiro.component.credential.RemexHashedCredentialsMatcher;
import com.github.hqxqyly.remex.boot.shiro.component.filter.ShiroAuthenticationFilter;
import com.github.hqxqyly.remex.boot.shiro.component.realm.RemexAuthorizingRealm;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * shiro自动配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@ConditionalOnClass({RemexAuthorizingRealm.class})
@AutoConfigureAfter({ShiroCommonAutoConfiguration.class})
@Configuration
public class ShiroAutoConfiguration implements IAssistConfig {

	/**
	 * ShiroFilterFactoryBean
	 * @return
	 * @throws Exception 
	 */
	@Primary
	@Bean("shiroFilterRegistration")
	public FilterRegistrationBean<AbstractShiroFilter> createShiroFilterFactoryBean(ShiroProperties properties, SecurityManager securityManager
			, ShiroAuthenticationFilter shiroAuthenticationFilter, RemexAuthorizingRealm remexAuthorizingRealm) throws Exception {
		//是否启用开发模式
		boolean isDev = Assist.defaultTrue(properties.getDev());
		
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager);
		
		//拦截器列表
		Map<String, Filter> filters = Assist.asMap("authc", shiroAuthenticationFilter);
		bean.setFilters(filters);
		
		//配置访问权限
		Map<String, String> filterChainDefinitionMap = new HashMap<>();
		
		//开发模式，则放行所有请求
		if (isDev) {
			filterChainDefinitionMap.put("/**", "anon");
		} else {  //非开发模式
			//放行的路径
			Assist.forEach(remexAuthorizingRealm.getAnonFilterChainDefinitionList(), url -> filterChainDefinitionMap.put(url, "anon"));
			//其余路径
			filterChainDefinitionMap.put("/**", "authc");
		}
		
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		AbstractShiroFilter filter = (AbstractShiroFilter) bean.getObject();
		
		FilterRegistrationBean<AbstractShiroFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setOrder(500);
		filterRegistrationBean.setFilter(filter);
		filterRegistrationBean.setName("shiroFilter");
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
	
	/**
	 * ShiroAuthenticationFilter
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public ShiroAuthenticationFilter createShiroAuthenticationFilter(ShiroProperties properties) {
		ShiroAuthenticationFilter bean = new ShiroAuthenticationFilter();
		ifNotNull(properties.getDev(), bean::setDev);
		ifNotNull(properties.getEnablePermit(), bean::setEnablePermit);
		return bean;
	}
	
	/**
	 * shiro核心安全事务管理器
	 * @param shiroRealm
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public SecurityManager createSecurityManager(ValidatingSessionManager validatingSessionManager , RemexAuthorizingRealm shiroRealm) {
		DefaultWebSecurityManager bean = new DefaultWebSecurityManager();
		bean.setSessionManager(validatingSessionManager);
		bean.setRealm(shiroRealm);
		return bean;
	}
	
	/**
	 * shiro ValidatingSessionManager
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public ValidatingSessionManager createValidatingSessionManager(ShiroProperties properties) {
		DefaultSessionManager bean = new ShiroSessionManager();
		ifNotNull(properties.getGlobalSessionTimeout(), bean::setGlobalSessionTimeout);
		ifNotNull(properties.getSessionValidationSchedulerEnabled(), bean::setSessionValidationSchedulerEnabled);
		ifNotNull(properties.getDeleteInvalidSessions(), bean::setDeleteInvalidSessions);
		return bean;
	}
	
	/**
	 * 密码加密比对
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public CredentialsMatcher createCredentialsMatcher(ShiroProperties properties, RemexAuthorizingRealm remexAuthorizingRealm) {
		RemexHashedCredentialsMatcher bean = new RemexHashedCredentialsMatcher();
		ifNotNull(properties.getEncryptAlgorithmName(), bean::setHashAlgorithmName);
		ifNotNull(properties.getEncryptHashIterations(), bean::setHashIterations);
		ifNotNull(properties.getIsEncryptBcrypt(), bean::setEncryptBcrypt);
		
		//realm设置此密码比对
		remexAuthorizingRealm.setCredentialsMatcher(bean);
		return bean;
	}
}
