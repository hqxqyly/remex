package com.github.hqxqyly.remex.solution.fileserver.interfaces;

import java.io.InputStream;

/**
 * 缩略图模式，上传逻辑处理器
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IFileServerThumbnailUploadHandler {

	/**
	 * 上传逻辑处理
	 * @param curDirectory 当前文件目录
	 * @param curInputStream 当前输入流
	 * @return
	 */
	String handle(String curDirectory, InputStream curInputStream);
}
