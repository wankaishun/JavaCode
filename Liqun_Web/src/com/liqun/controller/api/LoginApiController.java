package com.liqun.controller.api;

import java.util.Date;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.ConcurrentAccessException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.liqun.service.MemberService;
import com.liqun.service.PassportService;
import com.liqun.util.ShiroUtils;
import com.liqun.web.MessageEncoder;
import com.liqun.web.SuccessfulAuthenticatedToken;
import com.liqun.web.WebConsts;

@Controller
@RequestMapping("/api")
public class LoginApiController {
	@Autowired MemberService memberService;
	@Autowired PassportService passportService;
	@Autowired SecurityManager securityManager;
	@Autowired MessageEncoder messageEncoder;
	/**
	 * 用户登录
	 * 
	 * 登陆成功返回sc=200,body={ok:1}
	 * 登录失败返回sc=400,body={errcode:'authentication_error', message:'错误原因'}
	 * 验证码错误,会报验证码错误,然后把存储到session的清空,验证码会失效
	 * @param form
	 * @param errors
	 * @param redirect_uri
	 * @param model
	 * @param captcha
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/login")
	public Object login(
			//@Valid UsernamePasswordLoginForm form, BindingResult errors,
			@RequestParam(required=false) String redirect_uri,
			ModelMap model,String captcha) throws Exception {

		
		String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
		if(!captcha.equalsIgnoreCase(kaptcha)){
			throw new ConcurrentAccessException("验证码不正确");
		}
		
		String passwordhash = null;//form.getPassword();

		UsernamePasswordToken authcToken =null; //new UsernamePasswordToken(form.getUsername(), passwordhash, form.getRemember_me());
		System.out.println("form=" ); //form
		
		Subject currentUser = SecurityUtils.getSubject();

		try {
			if (currentUser.isAuthenticated())
				throw new ConcurrentAccessException("登录失败。请勿重复登录，若要登录请先注销。");
			
			AuthenticationInfo authcInfo = securityManager.authenticate(authcToken);
			
			synchronized(WebConsts.getSessionLoginMutex(currentUser)) {
				
				if (WebConsts.isAuthenticatedInRealTime(currentUser))
					throw new ConcurrentAccessException("登录失败。请勿频繁登录，若要登录请先注销！");
				
				currentUser.login(new SuccessfulAuthenticatedToken(authcToken, authcInfo));
				currentUser.getSession().setAttribute(WebConsts.LOGGED_IN_TIME, new Date());
				currentUser.getSession().setAttribute(WebConsts.LOGGED_IN_FROM, "passportService v1.0 login");
				currentUser.getSession().setAttribute(WebConsts.ORIGINAL_AUTHENTICATION_TOKEN_TYPE, authcToken.getClass().getName());
				
			}

			Long uid = (Long) currentUser.getPrincipal();
			Document me = memberService.findMemberById(uid);
			
			model.addAttribute("me", me);
			return new Document("ok",1 );

		} catch (AuthenticationException ex) {
			return new ResponseEntity<>(
					new Document("errcode", "authentication_error")
					.append("message",ex.getMessage()), HttpStatus.BAD_REQUEST);		}
	}
	
	@ExceptionHandler({AuthenticationException.class})
	public ResponseEntity<?> handleAuthcException(Exception ex) {

		Document body = new Document("errcode", "authentication_error").append("message", ex.getMessage());
//		body.append("exception_class", ex.getClass()).append("exception", ex);
		
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}
