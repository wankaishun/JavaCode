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

import com.liqun.dao.SysDictMapper;
import com.liqun.entity.SysDict;

@Controller
@RequestMapping("/console")
public class ForinvoiceController {
	
	@Autowired
	private SysDictMapper  sysDictMapper;
	@ExceptionHandler(Exception.class)
//	@ResponseBody
	public Object handleException(Exception e) {
//		if (e.getClass().equals(AuthenticationException.class)) {
//			
//		}
		return "console/error/not_authorized";// new Document("ok", 1);
	}
	/**
	 * 发票的换开
	 */
	@GetMapping("/forInvoice")
	public String  getForInvoice(ModelMap model) {
		Subject subject = SecurityUtils.getSubject();
		List<SysDict> sysDictList=sysDictMapper.selectBySysDict(1002);
		List<SysDict> sysDList = sysDictMapper.selectBySysDict(1003);
		model.put("sysDList",sysDList);
		model.put("sysDictList", sysDictList); 
		return "console/invoiceManager/forInvoiceManagement";
	}
	
	
}
