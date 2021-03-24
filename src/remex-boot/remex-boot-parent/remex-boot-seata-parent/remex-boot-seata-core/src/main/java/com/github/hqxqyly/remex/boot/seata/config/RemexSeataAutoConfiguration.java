package com.github.hqxqyly.remex.boot.seata.config;

import static io.seata.common.Constants.BEAN_NAME_SPRING_APPLICATION_CONTEXT_PROVIDER;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.github.hqxqyly.remex.boot.seata.component.interceptor.RemexGlobalTransactionScanner;

import io.seata.spring.annotation.GlobalTransactionScanner;
import io.seata.spring.boot.autoconfigure.SeataAutoConfiguration;
import io.seata.spring.boot.autoconfigure.properties.SeataProperties;

@EnableConfigurationProperties({SeataProperties.class})
@AutoConfigureBefore({SeataAutoConfiguration.class})
@Configuration
public class RemexSeataAutoConfiguration {
	
	@Bean
    @DependsOn({BEAN_NAME_SPRING_APPLICATION_CONTEXT_PROVIDER})
    @ConditionalOnMissingBean(GlobalTransactionScanner.class)
	public GlobalTransactionScanner createRemexGlobalTransactionScanner(SeataProperties seataProperties) {
		return new RemexGlobalTransactionScanner(seataProperties.getApplicationId(), seataProperties.getTxServiceGroup());
	}
}
