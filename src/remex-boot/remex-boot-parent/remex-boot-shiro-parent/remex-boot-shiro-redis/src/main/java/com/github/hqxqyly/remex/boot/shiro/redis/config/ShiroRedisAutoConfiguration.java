package com.github.hqxqyly.remex.boot.shiro.redis.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.IRedisManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisClusterManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.interfaces.assist.IAssistConfig;
import com.github.hqxqyly.remex.boot.shiro.common.properties.ShiroProperties;
import com.github.hqxqyly.remex.boot.shiro.component.access.ShiroSessionManager;
import com.github.hqxqyly.remex.boot.shiro.component.realm.RemexAuthorizingRealm;
import com.github.hqxqyly.remex.boot.shiro.config.ShiroAutoConfiguration;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * shiro redis自动配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@AutoConfigureBefore({ShiroAutoConfiguration.class})
@AutoConfigureAfter({RedisAutoConfiguration.class})
@Configuration
public class ShiroRedisAutoConfiguration implements IAssistConfig {

	/**
	 * shiro核心安全事务管理器
	 * @param shiroRealm
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public SecurityManager createSecurityManager(ValidatingSessionManager validatingSessionManager , RemexAuthorizingRealm shiroRealm
			, RedisCacheManager redisCacheManager) {
		DefaultWebSecurityManager bean = new DefaultWebSecurityManager();
		bean.setSessionManager(validatingSessionManager);
		bean.setRealm(shiroRealm);
		bean.setCacheManager(redisCacheManager);
		return bean;
	}
	
	/**
	 * shiro ValidatingSessionManager
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public ValidatingSessionManager createValidatingSessionManager(ShiroProperties properties, RedisSessionDAO redisSessionDAO) {
		DefaultSessionManager bean = new ShiroSessionManager();
		ifNotNull(properties.getGlobalSessionTimeout(), bean::setGlobalSessionTimeout);
		ifNotNull(properties.getSessionValidationSchedulerEnabled(), bean::setSessionValidationSchedulerEnabled);
		ifNotNull(properties.getDeleteInvalidSessions(), bean::setDeleteInvalidSessions);
		bean.setSessionDAO(redisSessionDAO);
		return bean;
	}
	
	/**
	 * shiro RedisSessionDAO
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public RedisSessionDAO createRedisSessionDAO(IRedisManager redisManager, ShiroProperties properties) {
		RedisSessionDAO bean = new RedisSessionDAO();
		bean.setRedisManager(redisManager);
		//缓存数据所用的表名
		Assist.defaultString(properties.getCacheSessionTableName(), "shiro:shiro:session:", bean::setKeyPrefix);
		return bean;
	}
	
	/**
	 * shiro RedisCacheManager
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public RedisCacheManager createRedisCacheManager(IRedisManager redisManager, ShiroProperties properties) {
		RedisCacheManager bean = new RedisCacheManager();
		bean.setRedisManager(redisManager);
		//缓存数据所用的表名
		Assist.defaultString(properties.getCacheDataTableName(), "shiro:data:", bean::setKeyPrefix);
		//缓存数据所用的实体主键名
		Assist.defaultString(properties.getCacheDataEntityId(), "id", bean::setPrincipalIdFieldName);
		return bean;
	}
	
	/**
	 * shiro RedisManager
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public IRedisManager createRedisManager(RedisProperties redisProperties) {
		IRedisManager bean = null;
		//集群
		if (redisProperties.getCluster() != null) {
			bean = createRedisManagerCluster(redisProperties);
		} else {  //单点
			bean = createRedisManagerSingle(redisProperties);
		}
		
		return bean;
	}
	
	
	
	
	/**
	 * 创建集群的shiro redis manager
	 * @param redisProperties
	 * @return
	 */
	protected IRedisManager createRedisManagerCluster(RedisProperties redisProperties) {
		notEmpty(redisProperties.getCluster().getNodes(), "redis cluster nodes cannot be blank");
		
		RedisClusterManager bean = new RedisClusterManager();
		bean.setHost(Assist.join(redisProperties.getCluster().getNodes()));
		bean.setDatabase(redisProperties.getDatabase());
		ifNotBlank(redisProperties.getPassword(), bean::setPassword);
		return bean;
	}
	
	/**
	 * 创建单点的shiro redis manager
	 * @param redisProperties
	 * @return
	 */
	protected IRedisManager createRedisManagerSingle(RedisProperties redisProperties) {
		RedisManager bean = new RedisManager();
		bean.setHost(redisProperties.getHost());
		bean.setDatabase(redisProperties.getDatabase());
		return bean;
	}
}
