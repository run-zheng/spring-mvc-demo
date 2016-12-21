package org.demo.spring.mvc.service;

import javax.annotation.Resource;

import org.demo.spring.mvc.annotation.BizHandler;
import org.springframework.stereotype.Service;

@Service 
public class HelloServiceImpl implements IHelloService {
	@Resource
	private IAsyncService asyncService; 
	
	@BizHandler
	@Override
	public Object sayHello(String name) {
		asyncService.asyncProcessor(name);
		return "Hello "+ name; 
	}
}
