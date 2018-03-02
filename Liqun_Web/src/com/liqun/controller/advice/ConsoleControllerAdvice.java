package com.liqun.controller.advice;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.liqun.service.MemberService;
import com.liqun.service.PassportService;
import com.liqun.service.TimemachineRealm;
import com.liqun.web.WebConsts;

@ControllerAdvice(annotations = ConsoleControllerAdvisable.class)
public class ConsoleControllerAdvice {

	@ExceptionHandler({ AuthorizationException.class })
	public Object handleAuthzException(Exception ex) {
		

		Subject subject = SecurityUtils.getSubject();
		
		boolean authenticated = subject.isAuthenticated();
		if (authenticated) {
			return new ModelAndView("console/error/not_authorized", HttpStatus.UNAUTHORIZED);
		} else {
			return "redirect:/passport/login";
		}
		
		
	}

}
