//package com.github.hqxqyly.remex.boot.swagger.config;
//
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import com.google.common.collect.Lists;
//
//import springfox.documentation.service.StringVendorExtension;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.OperationBuilderPlugin;
//import springfox.documentation.spi.service.contexts.OperationContext;
//
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE+100)
//public class RemexOperationBuilderPlugin implements OperationBuilderPlugin {
//
//	@Override
//	public boolean supports(DocumentationType delimiter) {
//		return true;
//	}
//
//	@Override
//	public void apply(OperationContext context) {
//		context.operationBuilder().extensions(Lists.newArrayList(new StringVendorExtension("x-order","1")));
//	}
//
//}
