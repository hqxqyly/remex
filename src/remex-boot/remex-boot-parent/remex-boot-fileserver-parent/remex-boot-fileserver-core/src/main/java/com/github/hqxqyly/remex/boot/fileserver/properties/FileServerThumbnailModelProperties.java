package com.github.hqxqyly.remex.boot.fileserver.properties;

import java.io.Serializable;

/**
 * 文件服务器缩略图模型properties
 * 
 * @author Qiaoxin.Hong
 *
 */
public class FileServerThumbnailModelProperties implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 大小 */
	protected Integer size;
	
	/** 目录前缀 */
	protected String prefix;
	
	/** 目录后缀 */
	protected String suffix;
	
	public void setSize(Integer size) {
		this.size = size;
	}
	
	public Integer getSize() {
		return size;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
}
