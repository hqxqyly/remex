package com.github.hqxqyly.remex.boot.io.wrapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import com.github.hqxqyly.remex.boot.exception.RemexException;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * InputStream包装器，用于重复获取InputStream
 * 
 * @author Qiaoxin.Hong
 *
 */
public class InputStreamWrapper {
	
	/** 数据byte数组缓存 */
	protected byte[] byteCache;

	public InputStreamWrapper(InputStream inputStream) {
		Assist.notNull(inputStream, "inputStream cannot be null");
		
		ByteArrayOutputStream outputStream = null;
		try {
			int len = -1;
			outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			while ((len = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
			byteCache = outputStream.toByteArray();
		} catch (Exception e) {
			throw new RemexException("input stream wrapper error", e);
		} finally {
			Assist.close(inputStream, outputStream);
		}
	}
	
	/**
	 * 取得InputStream
	 * @return
	 */
	public InputStream getInputStream() {
		Assist.notEmpty(byteCache, "byte cache cannot be empty");
		return new ByteArrayInputStream(byteCache);
	}
}
