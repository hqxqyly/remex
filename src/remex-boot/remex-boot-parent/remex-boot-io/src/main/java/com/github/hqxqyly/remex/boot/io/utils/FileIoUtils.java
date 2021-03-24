package com.github.hqxqyly.remex.boot.io.utils;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;

import com.github.hqxqyly.remex.boot.exception.RemexException;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * 文件工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class FileIoUtils extends FileUtils {

	/**
	 * 文件写入
	 * @param file
	 * @param inputStream
	 */
	public static void writeByteArrayToFile(File file, InputStream inputStream) {
		Assist.notNull(file, "file cannot be null");
		Assist.notNull(inputStream, "inputStream cannot be null");
		
		OutputStream outputStream = null; 
		try {
			outputStream = openOutputStream(file);
			writeByteArrayToOutputStream(outputStream, inputStream);
		} catch (Exception e) {
			throw new RemexException("write byte array to file", e);
		} finally {
			//做层保险，防止writeByteArrayToOutputStream没释放掉资源
			Assist.close(inputStream, outputStream);
		}
	}
	
	/**
	 * 文件写入
	 * @param outputStream
	 * @param inputStream
	 */
	public static void writeByteArrayToOutputStream(OutputStream outputStream, InputStream inputStream) {
		Assist.notNull(outputStream, "outputStream cannot be null");
		Assist.notNull(inputStream, "inputStream cannot be null");
		
		try { 
			int len = -1;
			byte[] buffer = new byte[1024];
			while ((len = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
		} catch (Exception e) {
			throw new RemexException("write byte array to output stream", e);
		} finally {
			Assist.close(inputStream, outputStream);
		}
	}
}
