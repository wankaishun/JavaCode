package com.liqun.aop;

import java.lang.annotation.ElementType;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) 
public @interface DbLoggable {
	 /** 
     * @func 操作类型：四种（INSERT, UPDATE, SELECT, DELETE） 
     */  
    public String optType() default "";  
  
    /** 
     * @func 描述 
     */  
    public String describe() default "";  
  
    /** 
     * @func 日志模块，不同模块的日志保存到不同的日志表中 
     */  
    public String module() default "";  
}
