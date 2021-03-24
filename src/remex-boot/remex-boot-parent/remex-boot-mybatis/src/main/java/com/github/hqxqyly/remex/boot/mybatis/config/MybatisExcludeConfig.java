package com.github.hqxqyly.remex.boot.mybatis.config;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * mybatis排除配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@EnableAutoConfiguration(exclude = {RemexMybatisAutoConfiguration.class, MybatisAutoConfiguration.class})
public class MybatisExcludeConfig {

}
