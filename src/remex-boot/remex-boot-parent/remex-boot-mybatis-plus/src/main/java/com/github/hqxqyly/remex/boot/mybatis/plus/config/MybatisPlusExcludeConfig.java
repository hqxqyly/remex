package com.github.hqxqyly.remex.boot.mybatis.plus.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;

/**
 * mybatis plus排除配置类
 * 
 * @author Qiaoxin.Hong
 *
 */
@EnableAutoConfiguration(exclude = {RemexMybatisPlusAutoConfiguration.class, RemexCustomMybatisPlusAutoConfiguration.class
		, MybatisPlusAutoConfiguration.class})
public class MybatisPlusExcludeConfig {

}
