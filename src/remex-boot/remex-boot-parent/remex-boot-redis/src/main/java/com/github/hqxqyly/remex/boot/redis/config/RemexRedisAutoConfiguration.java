package com.github.hqxqyly.remex.boot.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.redis.client.RedisClient;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * redis自动配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
public class RemexRedisAutoConfiguration {

	/**
	 * redis处理器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public RedisClient createRedisClient() {
		return new RedisClient();
	}
	
	/**
	 * RedissonClient
	 * @param properties
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public RedissonClient createRedissonClient(RedisProperties properties) {
		Config config = new Config();
		
		//单服务
		if (properties.getCluster() == null) {
			config.useSingleServer()
				.setAddress(Assist.join("redis://", properties.getHost(), ":", properties.getPort()))
				.setDatabase(properties.getDatabase())
				.setPassword(properties.getPassword());
		} else {  //集群
			String[] nodes = Assist.toArrayString(properties.getCluster().getNodes());
			config.useClusterServers()
				.addNodeAddress(nodes)
				.setPassword(properties.getPassword());
		}
		return Redisson.create(config);
	}
}
