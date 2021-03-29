package com.github.hqxqyly.remex.solution.fileserver.properties;

import java.util.HashMap;
import java.util.Map;

import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * 文件服务器properties
 * 
 * @author Qiaoxin.Hong
 *
 */
public class FileServerProperties {

	/** properties配置文件前缀 */
	public static final String PREFIX = "fileserver";
	
	/** 文件信息上下文，value为源文件路径 */
	protected Map<String, String> context = new HashMap<>();
	
	/** 缩略图模型列表，格式：<模型key, 模型信息> */
	protected Map<String, FileServerThumbnailModelProperties> thumbnailModelMap;
	
	
	/**
	 * 根据key查询文件路径
	 * @param key
	 * @return
	 */
	public String findPath(String key) {
		return Assist.notBlankFn(key, context::get);
	}
	
	
	
	
	
	
	
	public void setContext(Map<String, String> context) {
		this.context = context;
	}
	
	public Map<String, String> getContext() {
		return context;
	}
	
	public void setThumbnailModelMap(Map<String, FileServerThumbnailModelProperties> thumbnailModelMap) {
		this.thumbnailModelMap = thumbnailModelMap;
	}
	
	public Map<String, FileServerThumbnailModelProperties> getThumbnailModelMap() {
		return thumbnailModelMap;
	}
}
