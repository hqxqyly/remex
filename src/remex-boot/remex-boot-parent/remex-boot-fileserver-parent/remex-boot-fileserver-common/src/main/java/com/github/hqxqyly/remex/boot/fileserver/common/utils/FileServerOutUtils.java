package com.github.hqxqyly.remex.boot.fileserver.common.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.github.hqxqyly.remex.boot.fileserver.common.client.IFileServerOutClient;
import com.github.hqxqyly.remex.boot.utils.ApplicationContextUtils;

/**
 * 文件服务器外部工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class FileServerOutUtils {

	/**
	 * 取得处理器
	 * @return
	 */
	public static IFileServerOutClient getClient() {
		return ApplicationContextUtils.getBean(IFileServerOutClient.class);
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
}
