package org.demo.spring.mvc.framework;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import org.demo.spring.mvc.annotation.Config;
import org.demo.spring.mvc.annotation.BizFlowConfiger;
import org.demo.spring.mvc.aspect.HandlerAspect;
import org.demo.spring.mvc.composite.BizFlowConfigureDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.MethodCallback;

import com.alibaba.fastjson.JSON;

@Component
public class MyBeanPostProcessor implements ApplicationContextAware, BeanPostProcessor{
	private static final Logger LOG = LoggerFactory.getLogger(MyBeanPostProcessor.class);

	private CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();

	private ApplicationContext ctx ; 

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ctx = applicationContext; 
	}
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("beanName: " + beanName + "  bean:"+JSON.toJSONString(bean));
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("beanName: " + beanName + "  bean:"+JSON.toJSONString(bean));
		
		if(bean != null){
			MetadataReader metadataReader;
			try {
				metadataReader = factory.getMetadataReader(bean.getClass().getName());
				AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
		        System.out.println(annotationMetadata.isAnnotated(BizFlowConfiger.class.getName()));
		        Set<MethodMetadata> annotatedMethods = annotationMetadata.getAnnotatedMethods(Config.class.getName());
		        for (MethodMetadata methodMetaData : annotatedMethods ) {
		            System.out.println(methodMetaData.getMethodName()
		            + " " + methodMetaData.isAnnotated(Config.class.getName())
		            + "\r\n       "
		            + JSON.toJSONString(methodMetaData.getAllAnnotationAttributes(Config.class.getName())));
		            
		            methodMetaData.getAllAnnotationAttributes(Config.class.getName());
		            
		            Method configMethod = ReflectionUtils.findMethod(bean.getClass(), methodMetaData.getMethodName()); 
		            Object result = configMethod.invoke(bean, new Object[0]);
		            if(!(result instanceof BizFlowConfiger)){
		            	
		            }
		        }
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
	        
		}
		
		return bean;
	}

}
