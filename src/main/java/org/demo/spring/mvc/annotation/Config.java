package org.demo.spring.mvc.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention (RUNTIME)
@Target(METHOD)
public @interface Config {
    String value() default "";
    
    String description() default "" ; 
}
