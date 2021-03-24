package com.github.hqxqyly.remex.boot.ftp.utils;

import org.apache.commons.net.ftp.FTPClient;

import com.github.hqxqyly.remex.boot.fileserver.common.utils.FileServerOutUtils;
import com.github.hqxqyly.remex.boot.ftp.client.FtpClient;
import com.github.hqxqyly.remex.boot.utils.ApplicationContextUtils;

/**
 * ftp工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class FtpUtils extends FileServerOutUtils {

	/**
	 * 取得ftp文件服务器处理器
	 * @return
	 */
	public static FtpClient getFtpClient() {
		return ApplicationContextUtils.getBean(FtpClient.class);
	}
	
	/**
	 * 连接ftp
	 * @return
	 */
	public static FTPClient connectFtp() {
		return getFtpClient().connectFtp();
	}
	
	/**
	 * 关闭FTPClient
	 * 
	 * @param client
	 */
	public static void closeFtp(FTPClient client) {
		getFtpClient().closeFtp(client);
	}
	
	/**
	 * 改变目录路径
	 * @param client ftpClient
	 * @param fileDirectory 文件路径
	 */
	public static void changeWorkingDirectory(FTPClient client, String fileDirectory) {
		getFtpClient().changeWorkingDirectory(client, fileDirectory);
	}
}
