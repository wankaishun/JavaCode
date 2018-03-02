package com.liqun.controller;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.ConcurrentAccessException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.bson.Document;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.liqun.entity.SysUser;
import com.liqun.service.LoginService;
import com.liqun.service.PassportService;
import com.liqun.web.MessageEncoder;
import com.liqun.web.SuccessfulAuthenticatedToken;
import com.liqun.web.WebConsts;

@Controller
@RequestMapping("/passport")
public class LoginController {
	/*@Autowired MemberService memberService;*/
	@Autowired 
	PassportService passportService;
	@Autowired 
	SecurityManager securityManager;
	@Autowired 
	MessageEncoder messageEncoder;
	@Autowired 
	LoginService loginService;
	
	
	public static class UsernamePasswordLoginForm {
		
		@NotNull @Length(min=3,max=32) String account;
		@NotNull @Length(min=6, max=20) String password;
		Boolean remember_me = false;
		
		public String getAccount() {
			return account;
		}
		public void setAccount(String account) {
			this.account = account;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		public Boolean getRemember_me() {
			return remember_me;
		}
		public void setRemember_me(Boolean remember_me) {
			this.remember_me = remember_me;
		}
		@Override
		public String toString() {
			return "UsernamePasswordLoginForm [username=" + account + ", password=" + "***" + ", remember_me="
					+ remember_me + "]";
		}
		
	}
	
	
	@GetMapping("/login")
	public String login_input( @RequestParam(required=false) String redirect_uri, ModelMap model) {
		
		Subject currentUser = SecurityUtils.getSubject();
		
		boolean authenticated = currentUser.isAuthenticated();
		System.out.println(authenticated);
		if (authenticated) {
			return "redirect:/console";
		}
		
		return "passport/login_input";
	}
	
	/**
	 * 用户登录
	 * 用户不存在 ,body={errcode:'authentication_error', message:'错误原因'}
	 * 账号正常,flag=1,body={ok:1}
	 * 号被禁用,flag=0,body={errcode:'authentication_error', message:'错误原因'}
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
	@ResponseBody
	public Object login(
			@Valid UsernamePasswordLoginForm form, BindingResult errors,
			@RequestParam(required=false) String redirect_uri,
			ModelMap model,String captcha) throws Exception {
		//FIXME
		/*
		String kaptcha =(String) SecurityUtils.getSubject().getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if("".equals(captcha)) {
			return  new ResponseEntity<>(
					new Document("errcode", "authentication_error")
					.append("message","验证码不能为空"), HttpStatus.BAD_REQUEST);
		}
		if(kaptcha == null){
			return  new ResponseEntity<>(
					new Document("errcode", "authentication_error")
					.append("message","验证码失效"), HttpStatus.BAD_REQUEST);
		}
		
		SecurityUtils.getSubject().getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);
		
		if(!captcha.equalsIgnoreCase(kaptcha)){
			return  new ResponseEntity<>(
					new Document("errcode", "authentication_error")
					.append("message","验证码不正确"), HttpStatus.BAD_REQUEST);
		}
		*/
		String account = form.getAccount();
		String passwordhash = form.getPassword();

		UsernamePasswordToken authcToken = new UsernamePasswordToken(form.getAccount(), passwordhash, form.getRemember_me());
		System.out.println("form=" + form);
		
		Subject currentUser = SecurityUtils.getSubject();
		
		
		try {
			if (currentUser.isAuthenticated())
				throw new ConcurrentAccessException("登录失败。请勿重复登录，若要登录请先注销。");
			
			AuthenticationInfo authcInfo = securityManager.authenticate(authcToken);
			
			synchronized(WebConsts.getSessionLoginMutex(currentUser)) {
				
				if (WebConsts.isAuthenticatedInRealTime(currentUser)) {
					throw new ConcurrentAccessException("登录失败。请勿频繁登录，若要登录请先注销！");
				}
				
				currentUser.login(new SuccessfulAuthenticatedToken(authcToken, authcInfo));
				currentUser.getSession().setAttribute(WebConsts.LOGGED_IN_TIME, new Date());
				currentUser.getSession().setAttribute(WebConsts.LOGGED_IN_FROM, "passportService v1.0 login");
				currentUser.getSession().setAttribute(WebConsts.ORIGINAL_AUTHENTICATION_TOKEN_TYPE, authcToken.getClass().getName());
				
			}
			
			return new Document("ok",1 );

		}catch(UnknownAccountException ex) {
			return new ResponseEntity<>(
					new Document("errcode", "authentication_error")
					.append("message","用户不存在"), HttpStatus.BAD_REQUEST);	
		}catch(LockedAccountException ex) {
			return new ResponseEntity<>(
					new Document("errcode", "authentication_error")
					.append("message","账号被禁用"), HttpStatus.BAD_REQUEST);
		}catch (AuthenticationException ex) {
			return new ResponseEntity<>(
					new Document("errcode", "authentication_error")
					.append("message","密码错误"), HttpStatus.BAD_REQUEST);		
		} 
	}
	
	
	/**
	 * 退出登录信息
	 * @param redirect_uri
	 * @param model
	 * @return
	 */
	@PostMapping("/logout")
	@ResponseBody
	public Object loginOut22(@RequestParam(required=false) String redirect_uri, ModelMap model) {
		Subject currentUser = SecurityUtils.getSubject();
		boolean authenticated = currentUser.isAuthenticated();

		if(authenticated) {
			currentUser.logout();
		}
		return new Document("ok" ,1 );
	}
}
