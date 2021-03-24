package com.github.hqxqyly.remex.boot.seata.backup;

import org.springframework.context.annotation.Configuration;

//@AutoConfigureBefore({SeataAutoConfiguration.class})
//@ComponentScan(basePackages = "io.seata.spring.boot.autoconfigure.properties")
//@ConditionalOnProperty(prefix = StarterConstants.SEATA_PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@Configuration
//@EnableConfigurationProperties({SeataProperties.class})
public class RemexSeataAutoConfiguration {

//	@Bean
//    @DependsOn({BEAN_NAME_SPRING_APPLICATION_CONTEXT_PROVIDER, BEAN_NAME_FAILURE_HANDLER})
//    @ConditionalOnMissingBean(GlobalTransactionScanner.class)
//    public GlobalTransactionScanner globalTransactionScanner(SeataProperties seataProperties, FailureHandler failureHandler) {
//        return new RemexGlobalTransactionScanner(seataProperties.getApplicationId(), seataProperties.getTxServiceGroup(), failureHandler);
//    }
}
