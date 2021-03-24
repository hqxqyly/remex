package com.github.hqxqyly.remex.boot.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

/**
 * 加锁工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class LockUtils {
	
	/** 锁map */
	protected static Map<String, Lock> lockMap = new ConcurrentHashMap<>();

	/**
	 * 锁处理
	 * @param key
	 * @param action
	 */
	public static <T> T lock(String key, Supplier<T> action) {
		Assist.notBlank(key, "key cannot be blank");
		Assist.notNull(action, "action cannot be null");
		Lock lock = null;
		
		try {
			lock = getLock(key);
			
			lock.lock();
			
			return action.get();
		} finally {
			Assist.ifNotNull(lock, Lock::unlock);
			lockMap.remove(key);
		}
	}
	
	/**
	 * 取得锁工具类
	 * @param key
	 * @return
	 */
	public static Lock getLock(String key) {
		Assist.notBlank(key, "key cannot be blank");
		Lock lock = lockMap.get(key);
		
		if (lock == null) {
			synchronized (key) {
				lock = lockMap.get(key);
				if (lock == null) {
					lock = new ReentrantLock(true);
					lockMap.put(key, lock);
				}
			}
		}
		
		return lock;
	}
}
