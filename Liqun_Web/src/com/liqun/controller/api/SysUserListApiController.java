package com.liqun.controller.api;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liqun.controller.advice.ApiControllerAdvisable;
import com.liqun.entity.SysUser;
import com.liqun.service.SysLogService;
import com.liqun.service.SysUserService;
import com.liqun.util.DataGridPage;

@Controller
@RequestMapping("/api/sysuser")
@ApiControllerAdvisable
public class SysUserListApiController {

	@Autowired
	SysUserService sysUserService;
	@Autowired
	SysLogService sysLogService;

	@GetMapping("/sysuserList")
	@ResponseBody
	public Object sysuserList(@RequestParam int page, @RequestParam int rows, String realName, String flag,
			String roleId) {
		// 数据库分页 当前页需要减一
		Page<SysUser> sysUserlist = sysUserService.findAll(PageRequest.of(page - 1, rows), realName, flag, roleId);

		return DataGridPage.pageToGrid(sysUserlist);
	}

	/**
	 * 保存用户信息
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "saveSysUser", method = RequestMethod.POST)
	@ResponseBody
	public Object saveSysUser(SysUser user) {
		SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
		if (user.getId() != null && user.getId() != 0) {
			// edit
			user.setUpdateUser(currentUser.getAccount());
			user.setUpdateDate(new Date());
			user.setSex("男");
			user.setFlag("1");
			sysLogService.insertLog(41, "修改了名称为：{" + user.getRealName() + "}的用户信息");
			return sysUserService.editSysUser(user);
		} else {
			// add
			user.setCreateUser(currentUser.getAccount());
			user.setCreateDate(new Date());
			user.setSex("男");
			user.setFlag("1");
			String pwd = user.getPassword();
			String hashed = BCrypt.hashpw(pwd, BCrypt.gensalt());
			user.setPassword(hashed);
			sysLogService.insertLog(40, "添加了名称为：{" + user.getRealName() + "}的用户信息");
			return sysUserService.addSysUser(user);
		}
	}

	/**
	 * 根据id获取sys_user数据
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/getSysUser")
	@ResponseBody
	public Object getSysUser(int id) {
		return sysUserService.getSysUserInfo(id);
	}

	/**
	 * 操作sys_user表（禁用、启用、重置密码）
	 * 
	 * @param id
	 *            用户id
	 * @param type
	 *            1：禁用或启用用户，2:重置密码
	 * @param flag
	 *            根据flag判断当前用户是禁用还是启用
	 * @return
	 */
	@RequestMapping(value = "opeSysUser", method = RequestMethod.POST)
	@ResponseBody
	public Object opeSysUser(int id,String realName, int type, String flag) {
		if (type == 1) {
			if (flag == "1") {
				sysLogService.insertLog(44, "启用了姓名为：{" + realName + "}的用户信息");
			} else {
				sysLogService.insertLog(43, "禁用了姓名为：{" + realName + "}的用户信息");
			}
			return sysUserService.delSysUser(id, flag);
		} else if (type == 2) {
			sysLogService.insertLog(50, "为姓名为：{" + realName + "}的用户重置密码");
			return sysUserService.resetPwd(id);
		}
		return "";
	}
}
