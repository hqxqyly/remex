package remexfastbuilderpackage.remexfastbuildertemplate.resources;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.hqxqyly.remex.fast.framework.utils.ApplicationUtils;

import remexfastbuilderpackage.remexfastbuildertemplate.resources.config.RemexfastbuildertemplateResourcesApiAutoConfiguration;

/**
 * 启动类
 *
 * @author Qiaoxin.Hong
 *
 */
@SpringBootApplication(exclude = RemexfastbuildertemplateResourcesApiAutoConfiguration.class)
public class RemexfastbuildertemplateResourcesApplication {

	/**
	 * 启动方法
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationUtils.run(RemexfastbuildertemplateResourcesApplication.class, args);
	}
}
