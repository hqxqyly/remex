package remexfastbuilderpackage.remexfastbuildertemplate.media;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.hqxqyly.remex.fast.framework.utils.ApplicationUtils;

import remexfastbuilderpackage.remexfastbuildertemplate.media.config.RemexfastbuildertemplateMediaApiAutoConfiguration;

/**
 * 启动类
 *
 * @author Qiaoxin.Hong
 *
 */
@SpringBootApplication(exclude = RemexfastbuildertemplateMediaApiAutoConfiguration.class)
public class RemexfastbuildertemplateMediaApplication {

	/**
	 * 启动方法
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationUtils.run(RemexfastbuildertemplateMediaApplication.class, args);
	}
}
