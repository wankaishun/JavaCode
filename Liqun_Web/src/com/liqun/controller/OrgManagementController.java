package com.liqun.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//页面跳转类
@Controller
@RequestMapping("/console")
public class OrgManagementController {
	
	@ExceptionHandler(Exception.class)
//	@ResponseBody
	public Object handleException(Exception e) {
		return "console/error/not_authorized";// new Document("ok", 1);
	}
	@GetMapping("/orgmanagement")
	public String roleList() {
		return "console/sys/orgmanagement";
	}

}
