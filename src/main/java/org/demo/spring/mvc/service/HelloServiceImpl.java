package org.demo.spring.mvc.service;

import org.springframework.stereotype.Service;

@Service 
public class HelloServiceImpl implements IHelloService {
	@Override
	public Object sayHello(String name) {
		return "Hello "+ name; 
	}
}
