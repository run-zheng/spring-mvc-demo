package org.demo.spring.mvc.controller;

import org.demo.spring.mvc.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/hello")
public class HelloController {

	@Autowired
	private IHelloService helloService; 
	
	@RequestMapping(value="/sayHello", method=RequestMethod.GET)
	@ResponseBody
	public Object sayHello(@RequestParam("name") String name){
		return helloService.sayHello(name); 
	}
}
