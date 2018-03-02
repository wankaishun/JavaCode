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
public class TimeTaskController {
	
	@ExceptionHandler(Exception.class)
	public Object handleException(Exception e) {
		return "console/error/not_authorized";// new Document("ok", 1);
	}
	
	
	//打开定时开票任务界面
	@GetMapping("/timeticket")
	public String roleList(ModelMap model) {
		 
		return "console/sys/timeticket";
	}
	
 

}
