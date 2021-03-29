package com.github.hqxqyly.remex.solution.fileserver.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.github.hqxqyly.remex.boot.utils.ApplicationContextUtils;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.solution.fileserver.client.IFileServerClient;
import com.github.hqxqyly.remex.solution.fileserver.properties.FileServerProperties;

/**
 * 文件服务器工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class FileServerUtils {

	/**
	 * 取得处理器
	 * @return
	 */
	public static IFileServerClient getClient() {
		return ApplicationContextUtils.getBean(IFileServerClient.class);
	}
	
	/**
	 * 取得文件服务器properties
	 * @return
	 */
	public static FileServerProperties getFileServerProperties() {
		return ApplicationContextUtils.getBean(FileServerProperties.class);
	}
	
	/**
	 * 提取文件枚举路径
	 * @param fileEnum
	 * @return
	 */
	public static String fetchFileEnumPath(String fileEnum) {
		Assist.notBlank(fileEnum, "fileEnum cannot be blank");
		return getFileServerProperties().findPath(fileEnum);
	}
	
	
	
	
	
	/**
	 * 文件是否存在
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @return
	 */
	public static boolean exist(String directory, String fileName) {
		return getClient().exist(directory, fileName);
	}
	
	/**
	 * 上传
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param inputStream
	 * @return
	 */
	public static String upload(String directory, String fileName, InputStream inputStream) {
		return getClient().upload(directory, fileName, inputStream);
	}
	
	/**
	 * 下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param outputStream
	 * @return
	 */
	public static void download(String directory, String fileName, OutputStream outputStream) {
		getClient().download(directory, fileName, outputStream);
	}
	
	/**
	 * 文件删除
	 * @param directory 文件目录
	 * @param fileName 文件名
	 */
	public static void delete(String directory, String fileName) {
		getClient().delete(directory, fileName);
	}
	
	/**
	 * 文件是否存在
	 * @param filePath 文件路径
	 * @return
	 */
	public static boolean exist(String filePath) {
		return getClient().exist(filePath);
	}
	
	/**
	 * 验证文件是否存在，不存在则抛出异常
	 * @param directory
	 * @param fileName
	 */
	public static void validateExist(String directory, String fileName) {
		getClient().validateExist(directory, fileName);
	}
	
	/**
	 * 上传
	 * @param filePath 文件路径
	 * @param inputStream 
	 */
	public static String upload(String filePath, InputStream inputStream) {
		return getClient().upload(filePath, inputStream);
	}
	
	/**
	 * 上传
	 * @param directory 文件目录
	 * @param file 文件
	 */
	public static String upload(String directory, MultipartFile file) {
		return getClient().upload(directory, file);
	}
	
	/**
	 * 上传
	 * @param directory 文件目录
	 * @param file 文件
	 */
	public static String upload(String directory, MultipartFile file, String fileName) {
		return getClient().upload(directory, file, fileName);
	}
	
	/**
	 * 批量上传
	 * @param directory 文件目录
	 * @param file 文件
	 */
	public static List<String> uploadBatch(String directory, MultipartFile[] fileList) {
		return getClient().uploadBatch(directory, fileList);
	}
	
	/**
	 * 上传，以日期分目录
	 * @param directory 文件目录
	 * @param file 文件
	 */
	public static String uploadForDate(String directory, MultipartFile file) {
		return getClient().uploadForDate(directory, file);
	}
	
	/**
	 * 上传，以日期分目录
	 * @param directory 文件目录
	 * @param file 文件
	 * @param curDate 日期，null则为当前日期
	 */
	public static String uploadForDate(String directory, MultipartFile file, Date curDate) {
		return getClient().uploadForDate(directory, file, curDate);
	}
	
	/**
	 * 上传，以日期分目录
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param inputStream
	 * @param curDate 日期，null则为当前日期
	 * @return
	 */
	public static String uploadForDate(String directory, String fileName, InputStream inputStream) {
		return getClient().uploadForDate(directory, fileName, inputStream);
	}
	
	/**
	 * 上传，以日期分目录
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param inputStream
	 * @param curDate 日期，null则为当前日期
	 * @return
	 */
	public static String uploadForDate(String directory, String fileName, InputStream inputStream, Date curDate) {
		return getClient().uploadForDate(directory, fileName, inputStream, curDate);
	}
	
	/**
	 * 批量上传，以日期分目录
	 * @param directory 文件目录
	 * @param file 文件
	 */
	public static List<String> uploadBatchForDate(String directory, MultipartFile[] fileList) {
		//文件上传
		return getClient().uploadBatchForDate(directory, fileList);
	}
	
	/**
	 * 下载
	 * @param filePath 文件路径
	 * @param outputStream
	 */
	public static void download(String filePath, OutputStream outputStream) {
		getClient().download(filePath, outputStream);
	}
	
	/**
	 * 下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param response
	 */
	public static void download(String directory, String fileName, HttpServletResponse response) {
		download(directory, fileName, response);
	}
	
	/**
	 * 下载
	 * @param filePath 文件路径
	 * @param response
	 */
	public static void download(String filePath, HttpServletResponse response) {
		getClient().download(filePath, response);
	}
	
	/**
	 * 下载到HttpServletResponse
	 * @param directory 文件目录
	 * @param fileName 文件名
	 */
	public static void downloadToResponse(String directory, String fileName) {
		getClient().downloadToResponse(directory, fileName);
	}
	
	/**
	 * 下载到HttpServletResponse
	 * @param filePath 文件路径
	 */
	public static void downloadToResponse(String filePath) {
		getClient().downloadToResponse(filePath);
	}
	
	/**
	 * 下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 */
	public static byte[] downloadByte(String directory, String fileName) {
		return getClient().downloadByte(directory, fileName);
	}
	
	/**
	 * 下载
	 * @param filePath 文件路径
	 */
	public static byte[] downloadByte(String filePath) {
		return getClient().downloadByte(filePath);
	}
	
	/**
	 * 下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 */
	public static String downloadTxt(String directory, String fileName) {
		return getClient().downloadTxt(directory, fileName);
	}
	
	/**
	 * 下载
	 * @param filePath 文件路径
	 */
	public static String downloadTxt(String filePath) {
		return getClient().downloadTxt(filePath);
	}
	
	/**
	 * 下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param outputStream
	 */
	public static String downloadBase64(String directory, String fileName) {
		return getClient().downloadBase64(directory, fileName);
	}
	
	/**
	 * 下载
	 * @param filePath 文件路径
	 * @param outputStream
	 */
	public static String downloadBase64(String filePath) {
		return getClient().downloadBase64(filePath);
	}
	
	/**
	 * 下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 */
	public static InputStream downloadInputStream(String directory, String fileName) {
		return getClient().downloadInputStream(directory, fileName);
	}
	
	/**
	 * 下载
	 * @param filePath 文件路径
	 */
	public static InputStream downloadInputStream(String filePath) {
		return getClient().downloadInputStream(filePath);
	}
	
	/**
	 * 多文件下载，依次遍历，直到下载到
	 * @param filePathList 文件路径列表
	 * @param outputStream
	 */
	public static void downloadEach(List<String> filePathList, OutputStream outputStream) {
		getClient().downloadEach(filePathList, outputStream);
	}
	
	/**
	 * 多文件下载，依次遍历，直到下载到
	 * @param directoryList 文件目录列表
	 * @param fileName 文件名
	 * @param outputStream
	 */
	public static void downloadEach(List<String> directoryList, String fileName, OutputStream outputStream) {
		getClient().downloadEach(directoryList, fileName, outputStream);
	}
	
	/**
	 * 多文件下载，依次遍历，直到下载到
	 * @param directoryList 文件目录列表
	 * @param fileName 文件名
	 * @param destOutputStream
	 */
	public static void downloadEach(List<String> directoryList, String fileName, HttpServletResponse response) {
		getClient().downloadEach(directoryList, fileName, response);
	}
	
	/**
	 * 多文件下载，依次遍历，直到下载到
	 * @param filePathList 文件路径列表
	 * @param destOutputStream
	 */
	public static void downloadEach(List<String> filePathList, HttpServletResponse response) {
		getClient().downloadEach(filePathList, response);
	}
	
	/**
	 * 多文件下载response，依次遍历，直到下载到
	 * @param directoryList 文件目录列表
	 * @param fileName 文件名
	 */
	public static void downloadEachToResponse(List<String> directoryList, String fileName) {
		getClient().downloadEachToResponse(directoryList, fileName);
	}
	
	/**
	 * 多文件下载response，依次遍历，直到下载到
	 * @param filePathList 文件路径列表
	 */
	public static void downloadEachToResponse(List<String> filePathList) {
		getClient().downloadEachToResponse(filePathList);
	}
	
	/**
	 * 文件删除
	 * @param filePath 文件路径
	 */
	public static void delete(String filePath) {
		getClient().delete(filePath);
	}
	
	/**
	 * 复制文件
	 * @param srcFilePath 源文件路径
	 * @param destDirectory 目标文件目录
	 * @param destFileName 目标文件名
	 */
	public static void copyFile(String srcFilePath, String destDirectory, String destFileName) {
		getClient().copyFile(srcFilePath, destDirectory, destFileName);
	}
	
	
	
	
	
	/**
	 * 缩略图模式，上传
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param inputStream
	 */
	public static String thumbnailUpload(String directory, String fileName, InputStream inputStream) {
		return getClient().thumbnailUpload(directory, fileName, inputStream);
	}
	
	/**
	 * 缩略图模式，上传
	 * @param directory 文件目录
	 * @param file 文件
	 */
	public static String thumbnailUpload(String directory, MultipartFile file) {
		return getClient().thumbnailUpload(directory, file);
	}
	
	/**
	 * 缩略图模式，批量上传
	 * @param directory 文件目录
	 * @param file 文件
	 */
	public static List<String> thumbnailUploadBatch(String directory, MultipartFile[] fileList) {
		return getClient().thumbnailUploadBatch(directory, fileList);
	}
	
	/**
	 * 缩略图模式，上传，以日期分目录
	 * @param directory 文件目录
	 * @param file 文件
	 */
	public static String thumbnailUploadForDate(String directory, MultipartFile file) {
		return getClient().thumbnailUploadForDate(directory, file);
	}
	
	/**
	 * 缩略图模式，上传，以日期分目录
	 * @param directory 文件目录
	 * @param file 文件
	 */
	public static List<String> thumbnailUploadBatchForDate(String directory, MultipartFile[] fileList) {
		return getClient().thumbnailUploadBatchForDate(directory, fileList);
	}
	
	/**
	 * 缩略图模式，下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param outputStream
	 * @param modelKey 缩略图模型key
	 */
	public static void thumbnailDownload(String directory, String fileName, OutputStream outputStream, String modelKey) {
		getClient().thumbnailDownload(directory, fileName, outputStream, modelKey);
	}
	
	/**
	 * 缩略图模式，下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param response
	 * @param modelKey 缩略图模型key
	 */
	public static void thumbnailDownload(String directory, String fileName, HttpServletResponse response, String modelKey) {
		getClient().thumbnailDownload(directory, fileName, response, modelKey);
	}
	
	/**
	 * 缩略图模式，下载到HttpServletResponse
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param modelKey 缩略图模型key
	 */
	public static void thumbnailDownloadToResponse(String directory, String fileName, String modelKey) {
		getClient().thumbnailDownloadToResponse(directory, fileName, modelKey);
	}
	
	/**
	 * 缩略图模式，下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param modelKey 缩略图模型key
	 */
	public static byte[] thumbnailDownloadByte(String directory, String fileName, String modelKey) {
		return getClient().thumbnailDownloadByte(directory, fileName, modelKey);
	}
	
	/**
	 * 缩略图模式，下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param modelKey 缩略图模型key
	 */
	public static String thumbnailDownloadBase64(String directory, String fileName, String modelKey) {
		return getClient().thumbnailDownloadBase64(directory, fileName, modelKey);
	}

	/**
	 * 缩略图模式，下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param modelKey 缩略图模型key
	 */
	public static InputStream thumbnailDownloadInputStream(String directory, String fileName, String modelKey) {
		return getClient().thumbnailDownloadInputStream(directory, fileName, modelKey);
	}
	
	/**
	 * 缩略图模式，多文件下载，依次遍历，直到下载到
	 * @param directoryList 文件目录列表
	 * @param fileName 文件名
	 * @param outputStream
	 */
	public static void thumbnailDownloadEach(List<String> directoryList, String fileName, OutputStream outputStream, String modelKey) {
		getClient().thumbnailDownloadEach(directoryList, fileName, outputStream, modelKey);
	}
	
	/**
	 * 缩略图模式，多文件下载，依次遍历，直到下载到
	 * @param directoryList 文件目录列表
	 * @param fileName 文件名
	 * @param destOutputStream
	 */
	public static void thumbnailDownloadEach(List<String> directoryList, String fileName, HttpServletResponse response, String modelKey) {
		getClient().thumbnailDownloadEach(directoryList, fileName, response, modelKey);
	}
	
	/**
	 * 缩略图模式，多文件下载response，依次遍历，直到下载到
	 * @param directoryList 文件目录列表
	 * @param fileName 文件名
	 */
	public static void thumbnailDownloadEachToResponse(List<String> directoryList, String fileName, String modelKey) {
		getClient().thumbnailDownloadEachToResponse(directoryList, fileName, modelKey);
	}
}
