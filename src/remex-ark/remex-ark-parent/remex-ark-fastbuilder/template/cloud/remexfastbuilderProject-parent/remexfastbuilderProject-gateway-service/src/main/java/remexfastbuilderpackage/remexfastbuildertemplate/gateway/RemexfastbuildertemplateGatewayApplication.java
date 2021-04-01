package remexfastbuilderpackage.remexfastbuildertemplate.gateway;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.hqxqyly.remex.fast.framework.utils.ApplicationUtils;

/**
 * 启动类
 *
 * @author Qiaoxin.Hong
 *
 */
@SpringBootApplication
public class RemexfastbuildertemplateGatewayApplication {

	/**
	 * 启动方法
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationUtils.run(RemexfastbuildertemplateGatewayApplication.class, args);
	}
}
