package com.github.hqxqyly.remex.boot.web.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.unit.DataSize;

/**
 * remex MultipartProperties
 * 
 * @author Qiaoxin.Hong
 *
 */
@ConfigurationProperties("spring.servlet.multipart")
public class RemexMultipartProperties {

	/** 单个数据大小 */
	protected DataSize maxFileSize;
	
	/** 总上传数据大小 */
	protected DataSize maxRequestSize;

	public DataSize getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(DataSize maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public DataSize getMaxRequestSize() {
		return maxRequestSize;
	}

	public void setMaxRequestSize(DataSize maxRequestSize) {
		this.maxRequestSize = maxRequestSize;
	}
}
