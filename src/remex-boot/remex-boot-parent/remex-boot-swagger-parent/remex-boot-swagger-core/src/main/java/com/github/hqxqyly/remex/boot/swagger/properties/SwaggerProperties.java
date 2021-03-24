package com.github.hqxqyly.remex.boot.swagger.properties;

import com.github.hqxqyly.remex.boot.constant.BConst;

/**
 * swagger properties
 * 
 * @author Qiaoxin.Hong
 *
 */
public class SwaggerProperties {

	/** properties配置文件前缀 */
	public static final String PREFIX = "swagger";
	
	/** 是否开启swagger */
	protected Boolean enable = true;

	/** 标题 */
	protected String title;

	/** 描述 */
	protected String description;

	/** 版本 */
	protected String version;

	/** 联系人名称 */
	protected String name;

	/** 联系人url */
	protected String url;

	/** 联系人email */
	protected String email;
	
	/** 包路径，支持多个扫描路径，分割符{@link #basePackageSplit} */
	protected String basePackage;
	
	/** 包路径分割符，默认";" */
	protected String basePackageSplit = BConst.SEMICOLON;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public String getBasePackageSplit() {
		return basePackageSplit;
	}

	public void setBasePackageSplit(String basePackageSplit) {
		this.basePackageSplit = basePackageSplit;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
}
