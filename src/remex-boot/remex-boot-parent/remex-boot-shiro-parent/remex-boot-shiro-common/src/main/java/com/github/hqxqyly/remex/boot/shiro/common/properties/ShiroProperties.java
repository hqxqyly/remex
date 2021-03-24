package com.github.hqxqyly.remex.boot.shiro.common.properties;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * shiro properties
 * 
 * @author Qiaoxin.Hong
 *
 */
public class ShiroProperties {

	/** properties配置文件前缀 */
	public static final String PREFIX = "shiro";
	
	/** 是否启用开发模式 */
	protected Boolean dev;
	
	/** 是否启用权限验证 */
	protected Boolean enablePermit;
	
	/** session超时时间 */
	protected Long globalSessionTimeout;
	
	/** 是否定时检查失效的session */
	protected Boolean sessionValidationSchedulerEnabled;
	
	/** 是否删除过期的session */
	protected Boolean deleteInvalidSessions;
	
	/** 加密算法方式 */
	protected String encryptAlgorithmName = Md5Hash.ALGORITHM_NAME;
	
	/** 加密次数 */
	protected Integer encryptHashIterations = 1024;
	
	/** 是否开启密码二次动态加密 */
	protected Boolean isEncryptBcrypt = true;
	
	/** 缓存数据session所用的表名，在某些组件下使用 */
	protected String cacheSessionTableName = "shiro:session:";
	
	/** 缓存数据所用的表名，在某些组件下使用 */
	protected String cacheDataTableName = "shiro:data:";
	
	/** 缓存数据所用的实体主键名，在某些组件下使用 */
	protected String cacheDataEntityId = "id";

	public Boolean getDev() {
		return dev;
	}

	public void setDev(Boolean dev) {
		this.dev = dev;
	}

	public Boolean getEnablePermit() {
		return enablePermit;
	}

	public void setEnablePermit(Boolean enablePermit) {
		this.enablePermit = enablePermit;
	}

	public Long getGlobalSessionTimeout() {
		return globalSessionTimeout;
	}

	public void setGlobalSessionTimeout(Long globalSessionTimeout) {
		this.globalSessionTimeout = globalSessionTimeout;
	}

	public Boolean getSessionValidationSchedulerEnabled() {
		return sessionValidationSchedulerEnabled;
	}

	public void setSessionValidationSchedulerEnabled(Boolean sessionValidationSchedulerEnabled) {
		this.sessionValidationSchedulerEnabled = sessionValidationSchedulerEnabled;
	}

	public Boolean getDeleteInvalidSessions() {
		return deleteInvalidSessions;
	}

	public void setDeleteInvalidSessions(Boolean deleteInvalidSessions) {
		this.deleteInvalidSessions = deleteInvalidSessions;
	}

	public String getEncryptAlgorithmName() {
		return encryptAlgorithmName;
	}

	public void setEncryptAlgorithmName(String encryptAlgorithmName) {
		this.encryptAlgorithmName = encryptAlgorithmName;
	}

	public Integer getEncryptHashIterations() {
		return encryptHashIterations;
	}

	public void setEncryptHashIterations(Integer encryptHashIterations) {
		this.encryptHashIterations = encryptHashIterations;
	}

	public Boolean getIsEncryptBcrypt() {
		return isEncryptBcrypt;
	}

	public void setIsEncryptBcrypt(Boolean isEncryptBcrypt) {
		this.isEncryptBcrypt = isEncryptBcrypt;
	}

	public String getCacheSessionTableName() {
		return cacheSessionTableName;
	}

	public void setCacheSessionTableName(String cacheSessionTableName) {
		this.cacheSessionTableName = cacheSessionTableName;
	}

	public String getCacheDataTableName() {
		return cacheDataTableName;
	}

	public void setCacheDataTableName(String cacheDataTableName) {
		this.cacheDataTableName = cacheDataTableName;
	}

	public String getCacheDataEntityId() {
		return cacheDataEntityId;
	}

	public void setCacheDataEntityId(String cacheDataEntityId) {
		this.cacheDataEntityId = cacheDataEntityId;
	}
}
