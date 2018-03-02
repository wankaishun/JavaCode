package com.liqun.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.mgt.SecurityManager;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liqun.controller.advice.ApiControllerAdvisable;
import com.liqun.service.MemberService;
import com.liqun.service.PassportService;
import com.liqun.service.TimemachineRealm;

@Controller
@RequestMapping("/console/example_apis")
@ApiControllerAdvisable
public class ConsoleExampleApiController {
	
	@Autowired MemberService memberService;
	@Autowired PassportService passport;
	
	@Autowired SecurityManager securityManager;
	@Autowired TimemachineRealm realm;
	
	
	public static class Hello {
		String message;
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public Hello(String message) {
			this.message = message;
		}
	}
	
	
	
	@GetMapping("/hello")
	@ResponseBody
	@RequiresPermissions("role:aaa")
	public List<Hello> helloworld() {
		return Arrays.asList(new Hello("hello world!"), new Hello("welcome!"));
	}
	
	
	@GetMapping("/clear_shiro_cache")
	@ResponseBody
	public Document clear_shiro_cache(@RequestParam Long uid) {
		passport.clearCache(uid);
		return new Document("ok", 1);
	}
	
	
}
