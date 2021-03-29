package com.github.hqxqyly.remex.solution.fileserver.ftp.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import com.github.hqxqyly.remex.solution.fileserver.client.IFileServerClient;
import com.github.hqxqyly.remex.solution.fileserver.ftp.client.FileServerFtpClient;

/**
 * 文件服务器自动配置类 - ftp
 * 
 * @author Qiaoxin.Hong
 *
 */
public class FileServerFtpAutoConfiguration {

	/**
	 * 文件服务器处理器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public IFileServerClient createFileServerClient() {
		return new FileServerFtpClient();
	}
}
