package org.demo.spring.mvc.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service 
public class AsyncServiceImpl implements IAsyncService{

	@Async
	@Override
	public void asyncProcessor(String msg) {
		System.out.println("hello world !!!!!! " + msg);
	}
	
}
