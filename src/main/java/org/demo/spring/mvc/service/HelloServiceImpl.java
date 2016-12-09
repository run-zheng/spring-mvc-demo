package org.demo.spring.mvc.service;

import org.demo.spring.mvc.annotation.BizHandler;
import org.springframework.stereotype.Service;

@Service 
public class HelloServiceImpl implements IHelloService {
	
	@BizHandler
	@Override
	public Object sayHello(String name) {
		return "Hello "+ name; 
	}
}
