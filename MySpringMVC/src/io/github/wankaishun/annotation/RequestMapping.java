package io.github.wankaishun.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD}) // �ڷ����ϵ�ע��  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface RequestMapping {  
    String value() default "";  
}  
