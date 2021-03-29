package com.github.hqxqyly.remex.boot.redis.client;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.github.hqxqyly.remex.boot.utils.ApplicationContextUtils;
import com.github.hqxqyly.remex.solution.cache.common.client.ICacheOutClient;

/**
 * redis处理器
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RedisClient implements ICacheOutClient {
	
	@Autowired
	RedissonClient redissonClient;
	
	/**
	 * 取得StringRedisTemplate
	 * @return
	 */
	public StringRedisTemplate getRedisTemplate() {
		return ApplicationContextUtils.getBean(StringRedisTemplate.class);
	}
	
	/**
	 * 取得opsForValue
	 * @return
	 */
	public ValueOperations<String, String> getOpsForValue() {
		return getRedisTemplate().opsForValue();
	}
	
	/**
	 * 取得opsForSet
	 * @return
	 */
	public SetOperations<String, String> getOpsForSet() {
		return getRedisTemplate().opsForSet();
	}
	
	/**
	 * 取得opsForList
	 * @return
	 */
	public ListOperations<String, String> getOpsForList() {
		return getRedisTemplate().opsForList();
	}

	
	
	/**
	 * 根据条件获取所有key，可以以*作通配符，如：CACHE_KEY:*
	 * @param key
	 * @return
	 */
	@Override
	public Set<String> keys(String key) {
		if (isBlank(key)) return null;
		return getRedisTemplate().keys(key);
	}

	/**
	 * 设置数据
	 * @param key
	 * @param value
	 * @param timeout
	 * @param unit
	 */
	@Override
	public void set(String key, String value, Long timeout, TimeUnit unit) {
		if (isBlank(key) || value == null) return;
		//使用超时
		if (isUseTimeout(timeout))
			getOpsForValue().set(key, value, timeout, unit);
		else  //不使用超时
			getOpsForValue().set(key, value);
	}

	/**
	 * 设置数据到List，加到List后面
	 * @param key
	 * @param value
	 */
	@Override
	public void setToList(String key, String value) {
		if (isBlank(key) || value == null) return;
		getOpsForList().rightPush(key, value);
	}
	
	/**
	 * 设置数据到Set
	 * @param key
	 * @param value
	 */
	@Override
	public void setToSet(String key, String value) {
		if (isBlank(key) || value == null) return;
		getOpsForSet().add(key, value);
	}
	
	/**
	 * 根据key取得数据
	 * @param key
	 * @return
	 */
	@Override
	public String get(String key) {
		if (isBlank(key)) return null;
		return getOpsForValue().get(key);
	}

	/**
	 * 根据key取得List
	 * @param key
	 * @return
	 */
	@Override
	public List<String> getList(String key) {
		if (isBlank(key)) return null;
		return getOpsForList().range(key, 0, -1);
	}

	/**
	 * 根据key取得Set
	 * @param key
	 * @return
	 */
	@Override
	public Set<String> getSet(String key) {
		if (isBlank(key)) return null;
		return getOpsForSet().members(key);
	}

	/**
	 * 根据key删除
	 * @param key
	 */
	@Override
	public void delete(String key) {
		if (isBlank(key)) return;
		getRedisTemplate().delete(key);
	}
	
	
	
	/**
	 * 锁处理
	 * @param key key
	 * @param action 业务处理
	 * @return action执行结果
	 */
	@Override
	public <T> T lock(String key, Supplier<T> action) {
		notBlank(key, "key cannot be blank");
		notNull(action, "action cannot be null");
		RLock lock = null;
		
		try {
			lock = redissonClient.getLock(key);
			
			lock.lock();
			
			return action.get();
		} finally {
			ifNotNull(lock, lock::unlock);
		}
	}
}
