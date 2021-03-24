package com.github.hqxqyly.remex.boot.thumbnailator.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import com.github.hqxqyly.remex.boot.exception.RemexException;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 缩略图工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class ThumbnailUtils {

	/**
	 * 将图等比缩放并转InputStream
	 * @param inputStream
	 * @param size
	 * @return
	 */
	public static InputStream thumbnail(InputStream inputStream, int size) {
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			Thumbnails.of(inputStream).size(size, size).toOutputStream(outputStream);
			return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (Exception e) {
			throw new RemexException("thumbnail error", e);
		}
	}
}
