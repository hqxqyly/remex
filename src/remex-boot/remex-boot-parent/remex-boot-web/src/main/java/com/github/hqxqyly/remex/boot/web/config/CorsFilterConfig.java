package com.github.hqxqyly.remex.boot.web.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.github.hqxqyly.remex.boot.interfaces.assist.IAssistFilterConfig;

/**
 * 跨域配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration  
public class CorsFilterConfig implements IAssistFilterConfig {
	
	
	@Bean
	public FilterRegistrationBean<CorsFilter> createCorsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();  
        source.registerCorsConfiguration("/**", buildConfig()); // 4  
        CorsFilter filter = new CorsFilter(source);
        
        return createFilterRegistration(filter, 0);
	}
	
	
	
	
	protected CorsConfiguration buildConfig() {  
		CorsConfiguration corsConfiguration = new CorsConfiguration();  
		corsConfiguration.addAllowedOrigin("*"); // 1允许任何域名使用
		corsConfiguration.addAllowedHeader("*"); // 2允许任何头
		corsConfiguration.addAllowedMethod("*"); // 3允许任何方法（post、get等） 
		return corsConfiguration;  
	}
}
