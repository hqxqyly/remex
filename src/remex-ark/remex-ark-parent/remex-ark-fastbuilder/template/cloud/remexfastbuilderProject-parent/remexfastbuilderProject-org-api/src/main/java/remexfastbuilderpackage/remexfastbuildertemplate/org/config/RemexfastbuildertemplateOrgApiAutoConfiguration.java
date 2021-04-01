package remexfastbuilderpackage.remexfastbuildertemplate.org.config;

import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * api自动配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@EnableFeignClients({"remexfastbuilderpackage.remexfastbuildertemplate.org.api", "com.github.hqxqyly.remex.crude.org.feign"})
public class RemexfastbuildertemplateOrgApiAutoConfiguration {
	
}
