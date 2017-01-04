package org.demo.spring.mvc.composite;

import javax.annotation.PostConstruct;

import org.demo.spring.mvc.annotation.Config;
import org.demo.spring.mvc.annotation.BizFlowConfiger;

@BizFlowConfiger
public class BizFlowConfigureDemo {
	@PostConstruct
	public void test(){
		System.out.println(BizFlowConfigureDemo.class.getName()+" post construct");
	}
	
	@Config
	public void config(){
		
	}
}
