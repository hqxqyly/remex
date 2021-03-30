package com.smart.remex.ark.fast.builder.dto;

import java.io.Serializable;

/**
 * 构建参数信息
 * 
 * @author Qiaoxin.Hong
 *
 */
public class BuildParamDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 域名 */
	private String domainName;
	
	/** 项目名 */
	private String projectName;
	
	/** 服务名 */
	private String serviceName;
	
	/** 服务类名 */
	private String serviceClassName;
	
	/** 输出地址 */
	private String filePath;
	
	/** jdbc驱动类 */
	private String jdbcDriver;
	
	/** jdbc url */
	private String jdbcUrl;
	
	/** jdbc用户名 */
	private String jdbcUsername;
	
	/** jdbc密码 */
	private String jdbcPassword;

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setServiceClassName(String serviceClassName) {
		this.serviceClassName = serviceClassName;
	}
	
	public String getServiceClassName() {
		return serviceClassName;
	}
	
	public String getJdbcDriver() {
		return jdbcDriver;
	}

	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getJdbcUsername() {
		return jdbcUsername;
	}

	public void setJdbcUsername(String jdbcUsername) {
		this.jdbcUsername = jdbcUsername;
	}

	public String getJdbcPassword() {
		return jdbcPassword;
	}

	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
	}
	
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String getServiceName() {
		return serviceName;
	}
}
