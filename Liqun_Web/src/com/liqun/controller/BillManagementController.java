package com.liqun.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.htxx.utils.HttpRequest;
import com.liqun.entity.SysDict;
@Controller
@RequestMapping("/console")
public class BillManagementController {
	
	
	/**
	 * 页面的跳转
	 * @param
	 * @return
	 * @author 
	 * @data
	 */
	@GetMapping("/billmanager")
	public String  asynchronousDrawBill(ModelMap model) {
		Subject subject = SecurityUtils.getSubject();
		List<SysDict> sysDictList = new ArrayList<>();
		model.put("sysDictList", sysDictList);
		return "console/billManager/billmanager";
	}
	
	/**
	 * 获取数据的列表
	 * @param
	 * @Return
	 * @Data
	 */
	@GetMapping("/billmanagerList")
	public Object getManagerList(ModelMap model) {
		
		Subject subject = SecurityUtils.getSubject();
		List<SysDict> sysDictList = new ArrayList<>();
		model.put("sysDictList", sysDictList);
		return "console/billManager/billmanagerList";
	}
	
}
