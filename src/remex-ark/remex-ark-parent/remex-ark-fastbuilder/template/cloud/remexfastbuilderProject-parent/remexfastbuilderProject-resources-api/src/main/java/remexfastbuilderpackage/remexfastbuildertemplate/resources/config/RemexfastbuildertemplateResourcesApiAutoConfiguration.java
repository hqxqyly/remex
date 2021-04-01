package remexfastbuilderpackage.remexfastbuildertemplate.resources.config;

import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * api自动配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@EnableFeignClients({"remexfastbuilderpackage.remexfastbuildertemplate.resources.api"})
public class RemexfastbuildertemplateResourcesApiAutoConfiguration {

}
