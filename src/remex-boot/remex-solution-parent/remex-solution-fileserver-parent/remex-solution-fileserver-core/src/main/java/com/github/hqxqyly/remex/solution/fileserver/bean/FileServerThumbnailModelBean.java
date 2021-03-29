package com.github.hqxqyly.remex.solution.fileserver.bean;

import java.io.Serializable;

/**
 * 文件服务器缩略图模型bean
 * 
 * @author Qiaoxin.Hong
 *
 */
public class FileServerThumbnailModelBean implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 模型key */
	protected String key;
	
	/** 大小，<= 0则为原图大小 */
	protected int size = -1;
	
	/** 目录前缀 */
	protected String prefix;
	
	/** 目录后缀 */
	protected String suffix;
	
	public FileServerThumbnailModelBean() {
		super();
	}

	public FileServerThumbnailModelBean(String key) {
		super();
		this.key = key;
	}

	/**
	 * 是否原型图大小
	 * @return
	 */
	public boolean isPrototypeSize() {
		return size <= 0;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public String getSuffix() {
		return suffix;
	}
}
