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
public class BillHandingController {
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
	
	
	//获取品牌页面
	@GetMapping("/brand")
//	@RequiresAuthentication
//	@RequiresPermissions("role:query")
	public String BrandManager(ModelMap model) {
		
		//Subject subject = SecurityUtils.getSubject();
		
//		subject.isAuthenticated();
//		subject.isPermitted("role:add");
//		
//		subject.checkPermission("role:add");
//		
		model.put("title", new Faker().lorem().sentence());
		model.put("sentences", new Faker().lorem().sentences(50));
		return "console/billHanding/BrandManager";
	}
	
	//获取合并单据页面
	@GetMapping("/billmerge")
 //	@RequiresAuthentication
//	@RequiresPermissions("role:query")
	public String billmerge(ModelMap model) {
		
		Subject subject = SecurityUtils.getSubject();
		
 //		subject.isAuthenticated();
//		subject.isPermitted("role:add");
//		
//		subject.checkPermission("role:add");
		List<SysDict> sysDictList=sysDictMapper.selectBySysDict(1001);
		model.put("sysDictList", sysDictList); 
		return "console/billHanding/billmerge";
	}
	@GetMapping("/billsplit")
		public String billsplit(ModelMap model) {
			Subject subject = SecurityUtils.getSubject();
			List<SysDict> sysDictList=sysDictMapper.selectBySysDict(1001);
			model.put("sysDictList", sysDictList); 
			return "console/billHanding/billsplit";
		}
	//获取品类页面
	@GetMapping("/categorybrand")
//	@RequiresAuthentication
//	@RequiresPermissions("role:query")
	public String CategoryBrand(ModelMap model) {
		model.put("title", new Faker().lorem().sentence());
		model.put("sentences", new Faker().lorem().sentences(50));
		return "console/billHanding/CategoryBrand";
	}
}
