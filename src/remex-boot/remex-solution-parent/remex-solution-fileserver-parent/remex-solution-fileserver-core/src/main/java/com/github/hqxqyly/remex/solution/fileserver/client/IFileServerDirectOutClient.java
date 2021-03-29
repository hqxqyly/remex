package com.github.hqxqyly.remex.solution.fileserver.client;

import java.io.InputStream;
import java.io.OutputStream;

import com.github.hqxqyly.remex.boot.utils.ApplicationContextUtils;
import com.github.hqxqyly.remex.solution.fileserver.common.client.IFileServerOutClient;

/**
 * 文件服务器处理器 - 衔接外部组件接口
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IFileServerDirectOutClient extends IFileServerClient {

	/**
	 * 取得外部组件处理器
	 * @return
	 */
	default IFileServerOutClient getOutClient() {
		return ApplicationContextUtils.getBean(IFileServerOutClient.class);
	}
	
	/**
	 * 文件是否存在
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @return
	 */
	@Override
	default boolean exist(String directory, String fileName) {
		return getOutClient().exist(directory, fileName);
	}
	
	/**
	 * 上传
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param inputStream
	 * @return
	 */
	@Override
	default String upload(String directory, String fileName, InputStream inputStream) {
		return getOutClient().upload(directory, fileName, inputStream);
	}
	
	/**
	 * 下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param outputStream
	 * @return
	 */
	@Override
	default void download(String directory, String fileName, OutputStream outputStream) {
		getOutClient().download(directory, fileName, outputStream);
	}
	
	/**
	 * 文件删除
	 * @param directory 文件目录
	 * @param fileName 文件名
	 */
	@Override
	default void delete(String directory, String fileName) {
		getOutClient().delete(directory, fileName);
	}
}
