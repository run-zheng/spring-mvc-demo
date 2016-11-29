package org.demo.spring.mvc.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;

@Controller 
@Component
public class MyApplicationContextAware implements ApplicationContextAware{
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("ApplicationName:"+applicationContext.getApplicationName());
		System.out.println("BeanNames:"+JSON.toJSONString(applicationContext.getBeanDefinitionNames()));
	}
}
