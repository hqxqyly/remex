package com.github.hqxqyly.remex.boot.swagger.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.google.common.base.Predicate;
import com.github.hqxqyly.remex.boot.constant.BConst;
import com.github.hqxqyly.remex.boot.swagger.properties.SwaggerProperties;
import com.github.hqxqyly.remex.boot.utils.Assist;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger自动配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@EnableSwaggerBootstrapUI
@EnableSwagger2
@Configuration
public class SwaggerAutoConfiguration {
	
	/** 项目名 */
	@Value("${spring.application.name:}")
	String applicationName;
	
	/**
	 * swagger配置属性文件
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	@ConfigurationProperties(prefix = SwaggerProperties.PREFIX)
	public SwaggerProperties createSwaggerProperties() {
		return new SwaggerProperties();
	}

	/**
	 * swagger api
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public Docket createRestApi(SwaggerProperties properties) {
		//未配置标题，则默认使用项目名
		String title = Assist.defaultString(properties.getTitle(), applicationName);
		
		ApiInfo apiInfo = new ApiInfoBuilder()
				.title(title)
				.description(properties.getDescription())
				.version(properties.getVersion())
				.contact(new Contact(properties.getName(), properties.getUrl(), properties.getEmail()))
				.build();
		
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.enable(Assist.defaultTrue(properties.getEnable()))
				.apiInfo(apiInfo)
				.select()
				.apis(predicatePasePackage(properties))  //支持多个扫描路径，以,号隔开
				.paths(PathSelectors.any())
				.build()
				
				//支持的通讯协议集合
//				.protocols(Assist.asSet("https", "http"))
				
				;
		
		return docket;
	}
	
	/**
	 * 判断包路径
	 * @param basePackage
	 * @return
	 */
	protected static Predicate<RequestHandler> predicatePasePackage(SwaggerProperties properties) {
		String basePackage = properties.getBasePackage();
		if (Assist.isBlank(basePackage))
			//未自定义路径，则默认添加有ApiOperation的类
			return RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class);
		
		String basePackageSplit = Assist.defaultString(properties.getBasePackageSplit(), BConst.SEMICOLON);
		List<String> basePackageList = Assist.splitTrimToList(basePackage, basePackageSplit);
		return input -> basePackageList.stream().anyMatch(input.groupName()::startsWith);
    }
}
