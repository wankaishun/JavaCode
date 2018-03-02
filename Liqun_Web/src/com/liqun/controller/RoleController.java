package com.liqun.controller;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.javafaker.Faker;

//页面跳转类
@Controller
@RequestMapping("/console")
public class RoleController {
	
	@ExceptionHandler(Exception.class)
//	@ResponseBody
	public Object handleException(Exception e) {
//		if (e.getClass().equals(AuthenticationException.class)) {
//			
//		}
		return "console/error/not_authorized";// new Document("ok", 1);
	}
	
	
	//获取用户角色页面
	@GetMapping("/role")
//	@RequiresAuthentication
//	@RequiresPermissions("role:query")
	public String roleList(ModelMap model) {
		
		Subject subject = SecurityUtils.getSubject();
		
//		subject.isAuthenticated();
//		subject.isPermitted("role:add");
//		
//		subject.checkPermission("role:add");
//		
		model.put("title", new Faker().lorem().sentence());
		model.put("sentences", new Faker().lorem().sentences(50));
		
		return "console/sys/rolelist";
	}
	
	//获取用户角色列表
	@GetMapping("/roledata")
	@ResponseBody
	//@DbLoggable(describe="")  //操作日志
	public Object getRoleListData(ModelMap model) {
		model.put("title", new Faker().lorem().sentence());
		model.put("sentences", new Faker().lorem().sentences(50));
		return  new Document("ok", 1);
	}
	//获取根据用户ID获取用户角色
	
	
	

}
