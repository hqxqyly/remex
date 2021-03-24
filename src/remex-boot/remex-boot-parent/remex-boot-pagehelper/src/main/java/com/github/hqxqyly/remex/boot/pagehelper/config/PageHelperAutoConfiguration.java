package com.github.hqxqyly.remex.boot.pagehelper.config;

import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.hqxqyly.remex.boot.pagehelper.interceptor.RemexPageInterceptor;
import com.github.hqxqyly.remex.boot.pagehelper.properties.PageHelperProperties;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * PageHelper自动配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@ConditionalOnBean(SqlSessionFactory.class)
@Configuration
public class PageHelperAutoConfiguration {

	@Autowired
    protected List<SqlSessionFactory> sqlSessionFactoryList;
	
	/**
	 * PageHelper配置属性文件
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	@ConfigurationProperties(prefix = PageHelperProperties.PREFIX)
	public PageHelperProperties createPageHelperProperties() {
		return new PageHelperProperties();
	}
	
	/**
	 * 将PageHelper加入到Mybatis拦截器中里
	 */
	@PostConstruct
    public void addPageInterceptor() {
		RemexPageInterceptor interceptor = new RemexPageInterceptor();
		Properties properties = new Properties();
		fillPageHelperProperties(properties, createPageHelperProperties());
        interceptor.setProperties(properties);
        Assist.forEach(sqlSessionFactoryList, sqlSessionFactory -> sqlSessionFactory.getConfiguration().addInterceptor(interceptor));
	}
	
	/**
	 * 填充PageHelper properties
	 * @param properties
	 * @param pageHelperProperties
	 */
	protected void fillPageHelperProperties(Properties properties, PageHelperProperties pageHelperProperties) {
		properties.put("offsetAsPageNum", pageHelperProperties.getOffsetAsPageNum());
		properties.put("rowBoundsWithCount", pageHelperProperties.getRowBoundsWithCount());
		properties.put("pageSizeZero", pageHelperProperties.getPageSizeZero());
		properties.put("reasonable", pageHelperProperties.getReasonable());
	}
}
