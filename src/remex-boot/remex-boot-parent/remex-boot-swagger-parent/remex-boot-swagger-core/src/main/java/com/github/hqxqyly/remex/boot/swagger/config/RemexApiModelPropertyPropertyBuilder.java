package com.github.hqxqyly.remex.boot.swagger.config;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.github.hqxqyly.remex.boot.exception.RemexException;
import com.github.hqxqyly.remex.boot.swagger.annotations.ApiModelPropertyHidden;

import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.spring.web.DescriptionResolver;
import springfox.documentation.swagger.schema.ApiModelPropertyPropertyBuilder;

/**
 * <pre>
 * 扩展swagger ApiModelProperty
 * 结合javax.validation，如果属性为NotNull、NotBlank、NotEmpty，则默认设置必填：ApiModelProperty.required = true
 * </pre>
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
public class RemexApiModelPropertyPropertyBuilder extends ApiModelPropertyPropertyBuilder {

	public RemexApiModelPropertyPropertyBuilder(DescriptionResolver descriptions) {
		super(descriptions);
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@Override
	public void apply(ModelPropertyContext context) {
		try {
			//是否重写属性
			boolean isOverwrite = false;
			//是否必填
			Boolean required = null;
			//是否隐藏
			Boolean hidden = null;
			
			if (context.getBeanPropertyDefinition().isPresent()) {
				BeanPropertyDefinition beanPropertyDefinition = context.getBeanPropertyDefinition().get();
				AnnotatedField annotatedField = beanPropertyDefinition.getField();
				
				if (annotatedField != null) {
					ApiModelProperty annotationApiModelProperty = annotatedField.getAnnotation(ApiModelProperty.class);
					
					if (annotationApiModelProperty != null) {
						NotNull annotationNotNull = annotatedField.getAnnotation(NotNull.class);
						NotBlank annotationNotBlank = annotatedField.getAnnotation(NotBlank.class);
						NotEmpty annotationNotEmpty = annotatedField.getAnnotation(NotEmpty.class);
						ApiModelPropertyHidden annotationApiModelPropertyHidden = annotatedField.getAnnotation(ApiModelPropertyHidden.class);
						
						if (annotatedField.getFullName().indexOf("RoleEntity") != -1) {
							System.out.println();
						}
						
//						context.getBuilder().extensions(toExtension(annotationApiModelProperty));
//						context.getBuilder().extensions(Assist.asList(new StringVendorExtension("x-order","aaa")));
						
						//字段非空
						if (annotationNotNull != null || annotationNotBlank != null || annotationNotEmpty != null) {
							isOverwrite = true;
							required = true;
						}
						
						//根据组标识来隐藏
						if (annotationApiModelPropertyHidden != null) {
//							Class<?>[] groupClassArr = annotationApiModelPropertyHidden.groups();
//							if (Assist.isNotEmpty(groupClassArr)) {
//								
//							}
						}
						
						//重写属性
						if (isOverwrite) {
							InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotationApiModelProperty);
							Field proxyField = invocationHandler.getClass().getDeclaredField("memberValues");
							proxyField.setAccessible(true);
							Map memberValues = (Map) proxyField.get(invocationHandler);
							
							//是否必填
							if (required != null)
								memberValues.put("required", required);
							if (hidden != null)
								memberValues.put("hidden", hidden);
						}
					}
				}
			}
			
			super.apply(context);
		} catch (Exception e) {
			throw new RemexException("ApiModelPropertyPropertyBuilder apply error", e);
		}
	}
	
//	protected List<VendorExtension> toExtension(ApiModelProperty annotation) {
//		Extension[] extensions = annotation.extensions();
//        List<VendorExtension> list = new ArrayList<>();
//        if (extensions != null && extensions.length > 0) {
//            for (int i = 0; i < extensions.length; i++) {
//
//                Extension extension = extensions[i];
//                String name = extension.name();
//                ObjectVendorExtension objectVendorExtension = new ObjectVendorExtension(name);
//                if (!name.equals("")) {
//                    for (int j = 0; j < extension.properties().length; j++) {
//                        ExtensionProperty extensionProperty = extension.properties()[j];
//                        StringVendorExtension stringVendorExtension = new StringVendorExtension(extensionProperty.name(), extensionProperty.value());
//                        objectVendorExtension.addProperty(stringVendorExtension);
//                    }
//                    list.add(objectVendorExtension);
//                }
//            }
//        }
//        return list;
//    }
}
