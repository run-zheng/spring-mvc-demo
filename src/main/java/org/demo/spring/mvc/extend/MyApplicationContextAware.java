package org.demo.spring.mvc.extend;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component("MyApplicationContextAware1")
public class MyApplicationContextAware implements ApplicationContextAware{
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("ApplicationName:"+applicationContext.getApplicationName());
		System.out.println("BeanNames:"+JSON.toJSONString(applicationContext.getBeanDefinitionNames()));
	}
}
