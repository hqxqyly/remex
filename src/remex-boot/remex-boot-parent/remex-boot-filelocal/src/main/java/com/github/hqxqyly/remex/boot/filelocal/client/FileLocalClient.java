package com.github.hqxqyly.remex.boot.filelocal.client;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import com.github.hqxqyly.remex.boot.io.utils.FileIoUtils;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.solution.fileserver.common.client.IFileServerOutClient;

/**
 * 本地文件处理器
 * 
 * @author Qiaoxin.Hong
 *
 */
public class FileLocalClient implements IFileServerOutClient {
	
	/** 默认文件根路径 */
	public final static String DEFAULT_ROOT_PATH = "./../fileserver/";
	
	/** 文件根路径 */
	protected String rootPath = DEFAULT_ROOT_PATH;
	
	/** 是否验证越级访问 */
	protected boolean isValidateFilePassLevelAccess = true;

	/**
	 * 上传
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param inputStream
	 * @return
	 */
	@Override
	public String upload(String directory, String fileName, InputStream inputStream) {
		notBlank(fileName, "fileName cannot be blank");
		notNull(inputStream, "inputStream cannot be blank");
		
		try {
			//验证是否越级访问
			localValidateFilePassLevelAccess(directory, fileName);
			
			//文件路径
			String filePath = packFilePath(rootPath, directory, fileName);
			FileIoUtils.writeByteArrayToFile(new File(filePath), inputStream);
			return fileName;
		} finally {
			Assist.close(inputStream);
		}
	}
	
	/**
	 * 文件是否存在
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @return
	 */
	@Override
	public boolean exist(String directory, String fileName) {
		notBlank(fileName, "fileName cannot be blank");

		//验证是否越级访问
		localValidateFilePassLevelAccess(directory, fileName);
		
		//文件路径
		String filePath = packFilePath(rootPath, directory, fileName);
		
		return new File(filePath).exists();
	}
	
	/**
	 * 下载
	 * @param directory 文件目录
	 * @param fileName 文件名
	 * @param outputStream
	 * @return
	 */
	@Override
	public void download(String directory, String fileName, OutputStream outputStream) {
		notBlank(fileName, "fileName cannot be blank");
		notNull(outputStream, "inputStream cannot be blank");
		
		try {
			//验证是否越级访问
			localValidateFilePassLevelAccess(directory, fileName);
			
			//验证文件是否存在
			validateExist(directory, fileName);
			
			//文件路径
			String filePath = packFilePath(rootPath, directory, fileName);
			
			FileIoUtils.copyFile(new File(filePath), outputStream);
		} catch (Exception e) {
			throw Assist.transferException("download error [directory : {}] [fileName : {}]", e, directory, fileName);
		} finally {
			Assist.close(outputStream);
		}
	}
	
	/**
	 * 文件删除
	 * @param directory 文件目录
	 * @param fileName 文件名
	 */
	@Override
	public void delete(String directory, String fileName) {
		notBlank(fileName, "fileName cannot be blank");
	
		//验证是否越级访问
		localValidateFilePassLevelAccess(directory, fileName);
		
		//文件路径
		String filePath = packFilePath(rootPath, directory, fileName);
		
		//验证文件是否存在
		validateExist(directory, fileName);
		
		FileIoUtils.deleteQuietly(new File(filePath));
	}
	
	
	
	
	
	
	
	/**
	 * 验证是否越级访问
	 * @param directory
	 * @param fileName
	 */
	public void localValidateFilePassLevelAccess(String directory, String fileName) {
		if (!isValidateFilePassLevelAccess) return;
		//验证是否越级访问
		validateFilePassLevelAccess(directory, fileName);
	}
	
	
	
	
	
	

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public boolean isValidateFilePassLevelAccess() {
		return isValidateFilePassLevelAccess;
	}

	public void setValidateFilePassLevelAccess(boolean isValidateFilePassLevelAccess) {
		this.isValidateFilePassLevelAccess = isValidateFilePassLevelAccess;
	}
}
