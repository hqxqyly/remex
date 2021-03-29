package com.github.hqxqyly.remex.solution.fileserver.local.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import com.github.hqxqyly.remex.solution.fileserver.client.IFileServerClient;
import com.github.hqxqyly.remex.solution.fileserver.local.client.FileServerLocalClient;

/**
 * 文件服务器自动配置类 - 本地文件
 * 
 * @author Qiaoxin.Hong
 *
 */
public class FileServerLocalAutoConfiguration {

	/**
	 * 文件服务器处理器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public IFileServerClient createFileServerClient() {
		return new FileServerLocalClient();
	}
}
