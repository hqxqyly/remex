package remexfastbuilderpackage.remexfastbuildertemplate.org.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * demo org api
 * 
 * @author Qiaoxin.Hong
 *
 */
@FeignClient("remexfastbuildertemplate-org-service")
@RequestMapping("/org/demo/")
public interface OrgDemoApi {
	
}
