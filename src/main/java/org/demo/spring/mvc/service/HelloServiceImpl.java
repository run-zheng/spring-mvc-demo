package org.demo.spring.mvc.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service 
public class HelloServiceImpl implements IHelloService {
	@Resource
	private IAsyncService asyncService; 
	
	@Override
	public Object sayHello(String name) {
		asyncService.asyncProcessor(name);
		return "Hello "+ name; 
	}
}
