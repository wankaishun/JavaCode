package com.liqun.aop;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.liqun.entity.SysLog;
import com.liqun.entity.SysUser;
import com.liqun.service.SysLogService;

/**
 * 记录用户操作的切面。
 * 将后台管理系统使用者的操作内容记录到数据库日志表中
 * 只有使用Dbloggable注解的才能有用
 * 
 * @author haier
 *
 */
@Component
@Aspect
public class DbOperationLogAspect {
	@Autowired
	private SysLogService sysLogService;
	@Pointcut("execution(* com.liqun.controller..*.*(..))")  
	//@Pointcut("@annotation(com.annotation.SystemServiceLog)")
    public void invoke(){}  
  
    @Before(value="invoke()")
    public void invokeBefore(JoinPoint point){
    	
    }  
      
    @AfterReturning(value="invoke()")
    public void invokeAfter(JoinPoint point){
        
        //判断只有使用了DbLoggable注解的方法，才把把操作日志写到数据库
        MethodSignature methodSignature = (MethodSignature) point.getSignature();  
        Method method = methodSignature.getMethod();  
        DbLoggable dbLoggable = method.getAnnotation(DbLoggable.class);  
        if (dbLoggable == null) {  
            return;  
        }
        
        //获取session中的信息
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        ServletWebRequest servletWebRequest=new ServletWebRequest(request);
        HttpServletResponse response=servletWebRequest.getResponse();
        
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal(); 
        String ipAddress = request.getLocalAddr();
        String content = dbLoggable.describe(); 
        
        SysLog sysLog = new SysLog();
        sysLog.setUserId( sysUser.getId());
        sysLog.setIpAddress(ipAddress);
        sysLog.setContent(content);
        sysLog.setOperationTime(new Date());
        sysLog.setCreateDate(new Date());
        sysLog.setCreateUser(sysUser.getRealName());
        
        sysLogService.insertLog(sysLog);
        
    }  
}
