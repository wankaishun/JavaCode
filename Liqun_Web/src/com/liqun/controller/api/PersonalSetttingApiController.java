package com.liqun.controller.api;

import org.apache.shiro.SecurityUtils;
import org.bson.Document;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liqun.controller.advice.ApiControllerAdvisable;
import com.liqun.entity.SysUser;
import com.liqun.service.SysLogService;
import com.liqun.service.SysUserService;
import com.liqun.web.MessageEncoder;

@Controller
@RequestMapping("/api/PersonalSettting")
@ApiControllerAdvisable
public class PersonalSetttingApiController {

	@Autowired
	SysUserService sysUserService;
	@Autowired
	MessageEncoder messageEncoder;
	@Autowired
	SysLogService sysLogService;

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<?> handleException(Exception ex) {
		Document body = new Document("errcode", "authorization_error").append("errmsg", "修改密码报错，请联系管理员");
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "modifyPwd", method = RequestMethod.POST)
	@ResponseBody
	/**
	 * 修改密码
	 * 
	 * @param password
	 * @param newPwd
	 * @return -1:您输入的原密码不正确； 1：密码设置成功
	 */
	public Object modifyPwd(String password, String newPwd) {
		// 获取当前用户信息
		SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
		SysUser userInfo = sysUserService.getSysUserInfo(currentUser.getId());

		if (!BCrypt.checkpw(password, userInfo.getPassword())) {
			return "-1";
		}
		String hashed = BCrypt.hashpw(newPwd, BCrypt.gensalt());
		sysLogService.insertLog(3, "修改了姓名为：{" + userInfo.getRealName() + "}的密码");
		return sysUserService.modifyPwd(currentUser.getId(), hashed);
	}

	@RequestMapping(value = "editUserInfo", method = RequestMethod.POST)
	@ResponseBody
	/**
	 * 变更个人信息
	 * 
	 * @param email
	 *            邮箱
	 * @param realName
	 *            真实姓名
	 * @return
	 */
	public Object editUserInfo(String email, String realName) {
		// 获取当前用户信息
		SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
		sysLogService.insertLog(2, "变更姓名为：{" + currentUser.getRealName() + "}的个人信息");
		return sysUserService.editUserInfo(currentUser.getId(), email, realName);
	}
}
