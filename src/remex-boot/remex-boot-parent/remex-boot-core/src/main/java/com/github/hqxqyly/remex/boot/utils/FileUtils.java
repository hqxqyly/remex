package com.github.hqxqyly.remex.boot.utils;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.github.hqxqyly.remex.boot.constant.BConst;
import com.github.hqxqyly.remex.boot.exception.RemexException;

/**
 * 文件工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class FileUtils {
	
	/** 操作系统文件分割符 */
	public final static String FILE_SEPARATOR = System.getProperty("file.separator");
	
	/**
	 * 取得文件后缀
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffix(String fileName) {
		if (fileName != null && fileName.lastIndexOf(BConst.PERIOD) != -1) {
			return BConst.PERIOD + fileName.substring(fileName.lastIndexOf(BConst.PERIOD) + 1);
		}
		return BConst.EMPTY;
	}

	/**
	 * 封装目录路径，如果为空则为根路径，如：/file/
	 * @param pathArr
	 * @return
	 */
	public static String packDirectoryPath(String...pathArr) {
		if (Assist.isEmpty(pathArr)) return FILE_SEPARATOR;
		
		return packFilePath(pathArr) + FILE_SEPARATOR;
	}
	
	/**
	 * 封装文件路径，如：/file/abc.jpg
	 * @param pathArr
	 * @return
	 */
	public static String packFilePath(String...pathArr) {
		Assist.notEmpty(pathArr, "pathArr cannot be empty");
		
		File file = null;
		for (String path : pathArr) {
			if (Assist.isBlank(path)) continue;
			//一级目录
			if (file == null)
				file = new File(path);
			else  //二级及以下目录
				file = new File(file, path);
		}
		return file.getPath();
	}
	
	/**
	 * 分割文件路径
	 * @param path
	 * @return
	 */
	public static List<String> splitFile(String path) {
		Assist.notBlank(path, "path cannot be blank");
		if (BConst.SLASH_RIGHT.equals(FILE_SEPARATOR))
			return Assist.toList(path.split("\\\\"));
		else
			return Assist.toList(path.split(FILE_SEPARATOR));
	}
	
	/**
	 * 从路径中提取文件名
	 * @param path
	 * @return
	 */
	public static String fetchFileName(String path) {
		Assist.notBlank(path, "path cannot be blank");
		
		String fileName = new File(path).getPath();
		int index = -1;
		if ((index = fileName.lastIndexOf(FILE_SEPARATOR)) != -1)
			fileName = fileName.substring(index + 1, fileName.length());
		return fileName;
	}
	
	/**
	 * 提取HttpServletResponse下载用的OutputStream，
	 * @param response
	 * @param fileName
	 * @return
	 */
	public static OutputStream getDownloadResponse(HttpServletResponse response, String fileName) {
		return getDownloadResponse(response, fileName, "application/octet-stream");
	}
	
	/**
	 * 提取HttpServletResponse下载用的OutputStream
	 * @param response
	 * @param fileName
	 * @return
	 */
	public static OutputStream getDownloadResponse(HttpServletResponse response, String fileName, String contentType) {
		Assist.notBlank(fileName, "fileName cannot be blank");
		Assist.notBlank(contentType, "contentType cannot be blank");
		
		try {
			//文件名
			String downloadFileName = fetchFileName(fileName);
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType(contentType);
			response.setHeader("Access-Control-Expose-Headers", "Content-Disposition,Access-Token,Uid");
			response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(downloadFileName, "UTF-8"));
			
			return response.getOutputStream();
		} catch (Exception e) {
			throw new RemexException("get download response error", e);
		}
	}
	
	/**
	 * 验证是否越级访问
	 * @param directory
	 * @param fileName
	 */
	public static void validateFilePassLevelAccess(String directory, String fileName) {
		Assist.notBlank(fileName, "fileName cannot be blank");
		
		//当前层级，根目录为0，如果为负则表示越级访问
		int curLevel = 0;
		String filePath = packFilePath(directory, fileName);
		if (Assist.isNotBlank(filePath)) {
			List<String> pathList = splitFile(filePath);
			if (Assist.isNotEmpty(pathList)) {
				//最后一级为文件
				for (String path : pathList) {
					//当前目录
					if (Assist.isBlank(path) || ".".equals(path)) continue;
					//上级目录
					if ("..".equals(path))
						curLevel -= 1;
					else  //子目录
						curLevel += 1;
				}
			}
		}
		

		//越级访问
		Assist.mustFalse(curLevel < 0, "file pass level access");
	}
}
