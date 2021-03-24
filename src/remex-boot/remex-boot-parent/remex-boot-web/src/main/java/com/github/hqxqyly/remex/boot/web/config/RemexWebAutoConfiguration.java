package com.github.hqxqyly.remex.boot.web.config;

import java.util.TimeZone;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * remex web自动配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
public class RemexWebAutoConfiguration {

	/**
	 * json时区
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
    public Jackson2ObjectMapperBuilderCustomizer createJackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder ->
                jacksonObjectMapperBuilder.timeZone(TimeZone.getTimeZone("GMT+8"));
    }
}
