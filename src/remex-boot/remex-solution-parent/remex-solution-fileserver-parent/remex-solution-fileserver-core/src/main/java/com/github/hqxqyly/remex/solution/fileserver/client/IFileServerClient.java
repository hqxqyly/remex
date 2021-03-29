package com.github.hqxqyly.remex.solution.fileserver.client;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.github.hqxqyly.remex.boot.io.wrapper.InputStreamWrapper;
import com.github.hqxqyly.remex.boot.thumbnailator.utils.ThumbnailUtils;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.boot.utils.DateUtils;
import com.github.hqxqyly.remex.boot.utils.ServletUtils;
import com.github.hqxqyly.remex.solution.fileserver.bean.FileServerThumbnailModelBean;
import com.github.hqxqyly.remex.solution.fileserver.common.client.IFileServerBaseClient;
import com.github.hqxqyly.remex.solution.fileserver.interfaces.IFileServerThumbnailUploadHandler;

/**
 * 文件服务器处理器
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IFileServerClient extends IFileServerBaseClient {
	
	/**
	 * 取得文件服务器缩略图模式处理器
	 * @return
	 */
	default FileServerThumbnailModelClient getFileServerThumbnailClient() {
		return getBean(FileServerThumbnailModelClient.class);
	}
	
	/**
	 * 缩略图模式，文件是否存在
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param modelKey 缩略图模型key
	 * @return
	 */
	default boolean thumbnailExist(String directory, String fileName, String modelKey) {
		//缩略图文件目录
		directory = getFileServerThumbnailClient().packModelPath(directory, modelKey);
		return exist(directory, fileName);
	}
	
	/**
	 * 缩略图模式，上传逻辑处理
	 * @param handler 入：当前所要使用的文件目录；出：上传后的文件名
	 * @return
	 */
	default String thumbnailUploadHandle(String directory, InputStream inputStream, IFileServerThumbnailUploadHandler handler) {
		//缩略图模型列表
		List<FileServerThumbnailModelBean> thumbnailModelList = getFileServerThumbnailClient().getThumbnailModelList();
		notEmpty(thumbnailModelList, "thumbnailModelList cannot be empty");
		
		//最终的文件名
		String finalFileName = null;
		//临时文件名
		String itemFileName = null;
		//输入流包装器，用于重复获取输入流
		InputStreamWrapper inputStreamWrapper = new InputStreamWrapper(inputStream);
		//当前输入流
		InputStream curInputStream;
		
		for (FileServerThumbnailModelBean model : thumbnailModelList) {
			try {
				//新文件目录
				String curDirectory = getFileServerThumbnailClient().packModelPath(directory, model);
				
				//原型图，则直接使用
				if (model.isPrototypeSize()) {
					curInputStream = inputStreamWrapper.getInputStream();
				} else {  //非原型图，则进行图片等比压缩
					curInputStream = ThumbnailUtils.thumbnail(inputStreamWrapper.getInputStream(), model.getSize());
				}
				
				//上传
				itemFileName = handler.handle(curDirectory, curInputStream);
				finalFileName = defaultVal(finalFileName, itemFileName);
			} catch (Exception e) {
				getLogger().error("thumbnail upload error", e);
			}
		}
		return finalFileName;
	}

	/**
	 * 缩略图模式，上传
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param inputStream
	 */
	default String thumbnailUpload(String directory, String fileName, InputStream inputStream) {
		return thumbnailUploadHandle(directory, inputStream, (curDirectory, curInputStream) -> {
			return upload(curDirectory, fileName, curInputStream);
		});
	}
	
	/**
	 * 缩略图模式，上传
	 * @param directory 文件目录
	 * @param file 文件
	 */
	default String thumbnailUpload(String directory, MultipartFile file) {
		notNull(file, "file cannot be null");
		String fileName = packFileName(file);
		return thumbnailUpload(directory, fileName, getInputStream(file));
	}
	
	/**
	 * 缩略图模式，批量上传
	 * @param directory 文件目录
	 * @param file 文件
	 */
	default List<String> thumbnailUploadBatch(String directory, MultipartFile[] fileList) {
		return Assist.forEachToList(fileList, file -> thumbnailUpload(directory, file));
	}
	
	/**
	 * 缩略图模式，上传，以日期分目录
	 * @param directory 文件目录
	 * @param file 文件
	 */
	default String thumbnailUploadForDate(String directory, MultipartFile file) {
		notBlank(directory, "directory cannot be blank");
		notNull(file, "file cannot be null");
		
		String curDateStr = DateUtils.getCurBasicDateStr();
		String fileName = packFileName(file);
		
		String basicFileName = thumbnailUploadHandle(directory, getInputStream(file), (curDirectory, curInputStream) -> {
			curDirectory = packDirectoryPath(curDirectory, curDateStr);
			return upload(curDirectory, fileName, curInputStream);
		});
		return Assist.join(curDateStr, "/", basicFileName);
	}
	
	/**
	 * 缩略图模式，上传，以日期分目录
	 * @param directory 文件目录
	 * @param file 文件
	 */
	default List<String> thumbnailUploadBatchForDate(String directory, MultipartFile[] fileList) {
		return Assist.forEachToList(fileList, file -> thumbnailUpload(directory, file));
	}
	
	/**
	 * 缩略图模式，下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param outputStream
	 * @param modelKey 缩略图模型key
	 */
	default void thumbnailDownload(String directory, String fileName, OutputStream outputStream, String modelKey) {
		//缩略图文件目录
		directory = getFileServerThumbnailClient().packModelPath(directory, modelKey);
		download(directory, fileName, outputStream);
	}
	
	/**
	 * 缩略图模式，下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param response
	 * @param modelKey 缩略图模型key
	 */
	default void thumbnailDownload(String directory, String fileName, HttpServletResponse response, String modelKey) {
		//缩略图文件目录
		directory = getFileServerThumbnailClient().packModelPath(directory, modelKey);
		download(directory, fileName, response);
	}
	
	/**
	 * 缩略图模式，下载到HttpServletResponse
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param modelKey 缩略图模型key
	 */
	default void thumbnailDownloadToResponse(String directory, String fileName, String modelKey) {
		thumbnailDownload(directory, fileName, ServletUtils.getResponse(), modelKey);
	}
	
	/**
	 * 缩略图模式，下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param modelKey 缩略图模型key
	 */
	default byte[] thumbnailDownloadByte(String directory, String fileName, String modelKey) {
		//缩略图文件目录
		directory = getFileServerThumbnailClient().packModelPath(directory, modelKey);
		return downloadByte(directory, fileName);
	}
	
	/**
	 * 缩略图模式，下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param modelKey 缩略图模型key
	 */
	default String thumbnailDownloadBase64(String directory, String fileName, String modelKey) {
		//缩略图文件目录
		directory = getFileServerThumbnailClient().packModelPath(directory, modelKey);
		return downloadBase64(directory, fileName);
	}

	/**
	 * 缩略图模式，下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param modelKey 缩略图模型key
	 */
	default InputStream thumbnailDownloadInputStream(String directory, String fileName, String modelKey) {
		//缩略图文件目录
		directory = getFileServerThumbnailClient().packModelPath(directory, modelKey);
		return downloadInputStream(directory, fileName);
	}
	
	/**
	 * 缩略图模式，多文件下载，依次遍历，直到下载到
	 * @param directoryList 文件目录列表
	 * @param fileName 文件名
	 * @param outputStream
	 */
	default void thumbnailDownloadEach(List<String> directoryList, String fileName, OutputStream outputStream, String modelKey) {
		notEmpty(directoryList, "directoryList cannot be empty");
		notBlank(fileName, "fileName cannot be blank");
		
		directoryList = Assist.forEachToList(directoryList, directory -> getFileServerThumbnailClient().packModelPath(directory, modelKey));
		downloadEach(directoryList, fileName, outputStream);
	}
	
	/**
	 * 缩略图模式，多文件下载，依次遍历，直到下载到
	 * @param directoryList 文件目录列表
	 * @param fileName 文件名
	 * @param destOutputStream
	 */
	default void thumbnailDownloadEach(List<String> directoryList, String fileName, HttpServletResponse response, String modelKey) {
		notBlank(fileName, "fileName cannot be blank");
		notNull(response, "response cannot be null");
		
		OutputStream outputStream = packDownloadResponse(response, fileName);
		thumbnailDownloadEach(directoryList, fileName, outputStream, modelKey);
	}
	
	/**
	 * 缩略图模式，多文件下载response，依次遍历，直到下载到
	 * @param directoryList 文件目录列表
	 * @param fileName 文件名
	 */
	default void thumbnailDownloadEachToResponse(List<String> directoryList, String fileName, String modelKey) {
		thumbnailDownloadEach(directoryList, fileName, ServletUtils.getResponse(), modelKey);
	}
}
