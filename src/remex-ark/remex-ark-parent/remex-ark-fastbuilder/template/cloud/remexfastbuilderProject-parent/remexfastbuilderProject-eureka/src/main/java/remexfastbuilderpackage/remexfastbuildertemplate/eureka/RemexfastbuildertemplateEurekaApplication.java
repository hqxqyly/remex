package remexfastbuilderpackage.remexfastbuildertemplate.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 启动类
 * 
 * @author Qiaoxin.Hong
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class RemexfastbuildertemplateEurekaApplication {

	/**
	 * 启动方法
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(RemexfastbuildertemplateEurekaApplication.class, args);
	}
}
