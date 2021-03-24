package com.github.hqxqyly.remex.boot.ehcache.client;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import com.github.hqxqyly.remex.boot.cache.common.client.ICacheOutClient;

/**
 * EhCache处理器
 * 
 * @author Qiaoxin.Hong
 *
 */
public class EhCacheClient implements ICacheOutClient {

	@Override
	public Set<String> keys(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(String key, String value, Long timeout, TimeUnit unit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setToList(String key, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setToSet(String key, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getList(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getSet(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T lock(String key, Supplier<T> action) {
		// TODO Auto-generated method stub
		return null;
	}
}
