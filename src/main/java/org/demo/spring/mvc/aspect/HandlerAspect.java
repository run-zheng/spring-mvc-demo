package org.demo.spring.mvc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.demo.spring.mvc.annotation.BizHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service 
@Aspect 
public class HandlerAspect {
	private static final Logger LOG = LoggerFactory.getLogger(HandlerAspect.class);
	
	@Pointcut(value = "execution(public * *(..))")
    public void anyPublicMethod() {
    }
	
	@Around("anyPublicMethod() && @annotation(bizHandler)")
	public Object decideAccess(ProceedingJoinPoint pjp, BizHandler bizHandler) throws Throwable{
		LOG.info(pjp.getClass().toString() + " " + pjp.toString());
		Object retObj = null; 
		try{
			retObj = pjp.proceed(); 
		}catch(Throwable t ){
			throw t; 
		}
		return retObj; 
	}
	
}
