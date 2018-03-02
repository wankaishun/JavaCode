package com.liqun.controller;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.javafaker.Faker;
import com.liqun.dao.SysDictMapper;
import com.liqun.entity.SysDict;

//页面跳转类
@Controller
@RequestMapping("/console")
public class ModelManageController {
	@ExceptionHandler(Exception.class)
	public Object handleException(Exception e) {
		return "console/error/not_authorized";// new Document("ok", 1);
	}
	
	
	//获取品牌页面
	@GetMapping("/modelmanage")
	public String BrandManager(ModelMap model) {
		model.put("title", new Faker().lorem().sentence());
		model.put("sentences", new Faker().lorem().sentences(50));
		return "console/sys/modelmanage";
	}
}
