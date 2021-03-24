package com.github.hqxqyly.remex.boot.fileserver.common.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.github.hqxqyly.remex.boot.exception.RemexException;
import com.github.hqxqyly.remex.boot.exception.io.RemexFileNotFoundException;
import com.github.hqxqyly.remex.boot.interfaces.assist.IAssist;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.boot.utils.DateUtils;
import com.github.hqxqyly.remex.boot.utils.FileUtils;
import com.github.hqxqyly.remex.boot.utils.ServletUtils;

/**
 * 文件服务器基础处理器
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IFileServerBaseClient extends IAssist {

	/**
	 * 文件是否存在
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @return
	 */
	boolean exist(String directory, String fileName);
	
	/**
	 * 上传
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param inputStream
	 * @return
	 */
	String upload(String directory, String fileName, InputStream inputStream);
	
	/**
	 * 下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param outputStream
	 * @return
	 */
	void download(String directory, String fileName, OutputStream outputStream);
	
	/**
	 * 文件删除
	 * @param directory 文件目录
	 * @param fileName 文件名
	 */
	void delete(String directory, String fileName);
	
	
	
	
	
	/**
	 * 文件是否存在
	 * @param filePath 文件路径
	 * @return
	 */
	default boolean exist(String filePath) {
		return exist(null, filePath);
	}
	
	/**
	 * 验证文件是否存在，不存在则抛出异常
	 * @param directory
	 * @param fileName
	 */
	default void validateExist(String directory, String fileName) {
		if (!exist(directory, fileName))
			throw new RemexFileNotFoundException("file not found [directory : {}] [fileName : {}]", directory, fileName);
	}
	
	/**
	 * 上传
	 * @param filePath 文件路径
	 * @param inputStream 
	 */
	default String upload(String filePath, InputStream inputStream) {
		return upload(null, filePath, inputStream);
	}
	
	/**
	 * 上传
	 * @param directory 文件目录
	 * @param file 文件
	 */
	default String upload(String directory, MultipartFile file) {
		String fileName = packFileName(file);
		return upload(directory, file, fileName);
	}
	
	/**
	 * 上传
	 * @param directory 文件目录
	 * @param file 文件
	 */
	default String upload(String directory, MultipartFile file, String fileName) {
		Assist.notNull(file, "file cannot be null");
		return upload(directory, fileName, getInputStream(file));
	}
	
	/**
	 * 批量上传
	 * @param directory 文件目录
	 * @param file 文件
	 */
	default List<String> uploadBatch(String directory, MultipartFile[] fileList) {
		return Assist.forEachToList(fileList, file -> upload(directory, file));
	}
	
	/**
	 * 上传，以日期分目录
	 * @param directory 文件目录
	 * @param file 文件
	 */
	default String uploadForDate(String directory, MultipartFile file) {
		return uploadForDate(directory, file, null);
	}
	
	/**
	 * 上传，以日期分目录
	 * @param directory 文件目录
	 * @param file 文件
	 * @param curDate 日期，null则为当前日期
	 */
	default String uploadForDate(String directory, MultipartFile file, Date curDate) {
		return uploadForDateHandle(directory, curDate, newDirectory -> upload(newDirectory, file));
	}
	
	/**
	 * 上传，以日期分目录
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param inputStream
	 * @param curDate 日期，null则为当前日期
	 * @return
	 */
	default String uploadForDate(String directory, String fileName, InputStream inputStream) {
		return uploadForDate(directory, fileName, inputStream, null);
	}
	
	/**
	 * 上传，以日期分目录
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param inputStream
	 * @param curDate 日期，null则为当前日期
	 * @return
	 */
	default String uploadForDate(String directory, String fileName, InputStream inputStream, Date curDate) {
		return uploadForDateHandle(directory, curDate, newDirectory -> upload(newDirectory, fileName, inputStream));
	}
	
	/**
	 * 批量上传，以日期分目录
	 * @param directory 文件目录
	 * @param file 文件
	 */
	default List<String> uploadBatchForDate(String directory, MultipartFile[] fileList) {
		//文件上传
		return Assist.forEachToList(fileList, file -> uploadForDate(directory, file));
	}
	
	/**
	 * 上传，以日期分目录
	 * @param directory 文件目录
	 * @param file 文件
	 */
	default String uploadForDateHandle(String directory, Date curDate, Function<String, String> action) {
		notBlank(directory, "directory cannot be blank");
		
		if (curDate == null)
			curDate = DateUtils.getCurDate();
		
		String curDateStr = DateUtils.formatDate(curDate);
		directory = FileUtils.packDirectoryPath(directory, curDateStr);
		
		String fileName = action.apply(directory);
		return Assist.join(curDateStr, FileUtils.FILE_SEPARATOR, fileName);
	}
	
	/**
	 * 下载
	 * @param filePath 文件路径
	 * @param outputStream
	 */
	default void download(String filePath, OutputStream outputStream) {
		download(null, filePath, outputStream);
	}
	
	/**
	 * 下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param response
	 */
	default void download(String directory, String fileName, HttpServletResponse response) {
		OutputStream outputStream = packDownloadResponse(response, fileName);
		download(directory, fileName, outputStream);
	}
	
	/**
	 * 下载
	 * @param filePath 文件路径
	 * @param response
	 */
	default void download(String filePath, HttpServletResponse response) {
		download(null, filePath, response);
	}
	
	/**
	 * 下载到HttpServletResponse
	 * @param directory 文件目录
	 * @param fileName 文件名
	 */
	default void downloadToResponse(String directory, String fileName) {
		download(directory, fileName, ServletUtils.getResponse());
	}
	
	/**
	 * 下载到HttpServletResponse
	 * @param filePath 文件路径
	 */
	default void downloadToResponse(String filePath) {
		download(filePath, ServletUtils.getResponse());
	}
	
	/**
	 * 下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 */
	default byte[] downloadByte(String directory, String fileName) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		download(directory, fileName, outputStream);
		return outputStream.toByteArray();
	}
	
	/**
	 * 下载
	 * @param filePath 文件路径
	 */
	default byte[] downloadByte(String filePath) {
		return downloadByte(null, filePath);
	}
	
	/**
	 * 下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 */
	default String downloadTxt(String directory, String fileName) {
		return new String(downloadByte(directory, fileName), StandardCharsets.UTF_8);
	}
	
	/**
	 * 下载
	 * @param filePath 文件路径
	 */
	default String downloadTxt(String filePath) {
		return downloadTxt(null, filePath);
	}
	
	/**
	 * 下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param outputStream
	 */
	default String downloadBase64(String directory, String fileName) {
		return Base64.getEncoder().encodeToString(downloadByte(directory, fileName));
	}
	
	/**
	 * 下载
	 * @param filePath 文件路径
	 * @param outputStream
	 */
	default String downloadBase64(String filePath) {
		return downloadBase64(null, filePath);
	}
	
	/**
	 * 下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 */
	default InputStream downloadInputStream(String directory, String fileName) {
		return new ByteArrayInputStream(downloadByte(directory, fileName));
	}
	
	/**
	 * 下载
	 * @param filePath 文件路径
	 */
	default InputStream downloadInputStream(String filePath) {
		return downloadInputStream(null, filePath);
	}
	
	/**
	 * 多文件下载，依次遍历，直到下载到
	 * @param filePathList 文件路径列表
	 * @param outputStream
	 */
	default void downloadEach(List<String> filePathList, OutputStream outputStream) {
		notEmpty(filePathList, "filePathList cannot be empty");
		
		try {
			for (String filePath : filePathList) {
				if (exist(filePath)) {
					download(filePath, outputStream);
					break;				
				}
			}
		} finally {
			Assist.close(outputStream);
		}
	}
	
	/**
	 * 多文件下载，依次遍历，直到下载到
	 * @param directoryList 文件目录列表
	 * @param fileName 文件名
	 * @param outputStream
	 */
	default void downloadEach(List<String> directoryList, String fileName, OutputStream outputStream) {
		notEmpty(directoryList, "directoryList cannot be empty");
		notBlank(fileName, "fileName cannot be blank");
		
		List<String> filePathList = Assist.forEachToList(directoryList, directory -> packFilePath(directory, fileName));
		downloadEach(filePathList, outputStream);
	}
	
	/**
	 * 多文件下载，依次遍历，直到下载到
	 * @param directoryList 文件目录列表
	 * @param fileName 文件名
	 * @param destOutputStream
	 */
	default void downloadEach(List<String> directoryList, String fileName, HttpServletResponse response) {
		notEmpty(directoryList, "directoryList cannot be empty");
		notBlank(fileName, "fileName cannot be blank");
		
		List<String> filePathList = Assist.forEachToList(directoryList, directory -> packFilePath(directory, fileName));
		downloadEach(filePathList, response);
	}
	
	/**
	 * 多文件下载，依次遍历，直到下载到
	 * @param filePathList 文件路径列表
	 * @param destOutputStream
	 */
	default void downloadEach(List<String> filePathList, HttpServletResponse response) {
		notEmpty(filePathList, "filePathList cannot be empty");
		notNull(response, "response cannot be null");
		
		String lastFilePath = filePathList.get(filePathList.size() - 1);
		OutputStream outputStream = packDownloadResponse(response, lastFilePath);
		downloadEach(filePathList, outputStream);
	}
	
	/**
	 * 多文件下载response，依次遍历，直到下载到
	 * @param directoryList 文件目录列表
	 * @param fileName 文件名
	 */
	default void downloadEachToResponse(List<String> directoryList, String fileName) {
		downloadEach(directoryList, fileName, ServletUtils.getResponse());
	}
	
	/**
	 * 多文件下载response，依次遍历，直到下载到
	 * @param filePathList 文件路径列表
	 */
	default void downloadEachToResponse(List<String> filePathList) {
		downloadEach(filePathList, ServletUtils.getResponse());
	}
	
	/**
	 * 文件删除
	 * @param filePath 文件路径
	 */
	default void delete(String filePath) {
		delete(null, filePath);
	}
	
	/**
	 * 复制文件
	 * @param srcFilePath 源文件路径
	 * @param destDirectory 目标文件目录
	 * @param destFileName 目标文件名
	 */
	default void copyFile(String srcFilePath, String destDirectory, String destFileName) {
		notBlank(srcFilePath, "srcFilePath cannot be blank");
		notBlank(destDirectory, "destDirectory cannot be blank");
		notBlank(destFileName, "destFileName cannot be blank");
		
		InputStream srcInputStream = null;
		try {
			//读取源文件输入流
			srcInputStream = downloadInputStream(srcFilePath);
			Assist.notNull(srcInputStream, "copy file srcInputStream cannot be null");
			
			//上传
			upload(destDirectory, destFileName, srcInputStream);
		} catch (Exception e) {
			throw Assist.transferException("copy file error", e);
		}
	}
	
	
	
	
	
	
	
	/**
	 * 封装目录路径，如果为空则为根路径，如：/file/
	 * @param pathArr
	 * @return
	 */
	default String packDirectoryPath(String...pathArr) {
		return unifyFileSeparator(FileUtils.packDirectoryPath(pathArr));
	}
	
	/**
	 * 封装文件路径，如：/file/abc.jpg
	 * @param pathArr
	 * @return
	 */
	default String packFilePath(String...pathArr) {
		return unifyFileSeparator(FileUtils.packFilePath(pathArr));
	}
	
	/**
	 * 统一文件分割符
	 * @param path
	 * @return
	 */
	default String unifyFileSeparator(String path) {
		return path;
	}
	
	/**
	 * 封装文件名，默认格式：UUID + 原文件后缀
	 * @param file
	 * @return
	 */
	default String packFileName(MultipartFile file) {
		String originalFilename = ifNotNullFn(file, MultipartFile::getOriginalFilename);
		return Assist.newId() + FileUtils.getFileSuffix(originalFilename);
	}
	
	/**
	 * 封装下载用的response
	 * @param response
	 * @param filePath
	 * @return
	 */
	default OutputStream packDownloadResponse(HttpServletResponse response, String filePath) {
		return FileUtils.getDownloadResponse(response, filePath);
	}
	
	/**
	 * 验证是否越级访问
	 * @param directory
	 * @param fileName
	 */
	default void validateFilePassLevelAccess(String directory, String fileName) {
		FileUtils.validateFilePassLevelAccess(directory, fileName);
	}
	
	/**
	 * 从multipartFile取得InputStream
	 * @param multipartFile
	 * @return
	 */
	default InputStream getInputStream(MultipartFile multipartFile) {
		notNull(multipartFile, "multipartFile cannot be null");
		try {
			return multipartFile.getInputStream();
		} catch (Exception e) {
			throw new RemexException("getInputStream error", e);
		}
	}
}
