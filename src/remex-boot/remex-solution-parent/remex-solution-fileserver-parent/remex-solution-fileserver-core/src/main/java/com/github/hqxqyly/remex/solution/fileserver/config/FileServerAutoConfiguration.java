package com.github.hqxqyly.remex.solution.fileserver.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.solution.fileserver.bean.FileServerThumbnailModelBean;
import com.github.hqxqyly.remex.solution.fileserver.client.FileServerThumbnailModelClient;
import com.github.hqxqyly.remex.solution.fileserver.properties.FileServerProperties;

/**
 * 文件服务器自动配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
public class FileServerAutoConfiguration {

	/**
	 * 文件服务器properties
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	@ConfigurationProperties(prefix = FileServerProperties.PREFIX)
	public FileServerProperties createFileServerProperties() {
		return new FileServerProperties();
	}
	
	/**
	 * 文件服务器缩略图模型处理器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public FileServerThumbnailModelClient createFileServerThumbnailModelClient(FileServerProperties properties) {
		FileServerThumbnailModelClient bean = new FileServerThumbnailModelClient();
		Assist.forEach(properties.getThumbnailModelMap(), (key, model) -> {
			FileServerThumbnailModelBean modelBo = new FileServerThumbnailModelBean(key);
			if (model != null) {
				Assist.ifNotNull(model.getSize(), modelBo::setSize);
				Assist.ifNotNull(model.getPrefix(), modelBo::setPrefix);
				Assist.ifNotNull(model.getSuffix(), modelBo::setSuffix);
			}
			bean.addThumbnailModel(modelBo);
		});
		return bean;
	}
}
