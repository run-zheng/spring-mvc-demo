package org.demo.spring.mvc.composite;

import javax.annotation.PostConstruct;

import org.demo.spring.mvc.annotation.BizFlowConfig;
import org.demo.spring.mvc.annotation.BizFlowConfiger;

@BizFlowConfiger
public class BizFlowConfigureDemo {
	@PostConstruct
	public void test(){
		System.out.println(BizFlowConfigureDemo.class.getName()+" post construct");
	}
	
	@BizFlowConfig
	public void config(){
		
	}
}
