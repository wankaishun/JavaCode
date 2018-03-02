package com.liqun.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.javafaker.Faker;
import com.liqun.controller.advice.ConsoleControllerAdvisable;
import com.liqun.entity.BizMemberInfo;
import com.liqun.entity.SysPermission;
import com.liqun.entity.SysRole;
import com.liqun.entity.SysUser;
import com.liqun.service.BizMemberInfoService;
import com.liqun.service.ConsoleService;
import com.liqun.service.RoleService;
import com.liqun.service.SysUserService;

/**
 * @author
 *
 */
@Controller
@RequestMapping("/console")
@ConsoleControllerAdvisable
public class ConsoleController {

	@Autowired
	SysUserService sysUserService;
	@Autowired
	BizMemberInfoService bizMemberInfoService;
	@Autowired
	RoleService roleService;
	@Autowired
	ConsoleService consoleService;

	@GetMapping("")
	@RequiresAuthentication
	public String home(ModelMap model) {

		System.out.println(SecurityUtils.getSubject().isAuthenticated());
		// 获取当前用户的信息
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();

		Integer roleId = user.getRoleId();
		List<SysPermission> permissions = consoleService.findMenusByMenusId(roleId, 1);

		Document _menus = new Document();

		// 把所有的节点存储到Map集合里
		Map<Integer, Document> documents = new HashMap<>();
		for (SysPermission permission : permissions) {
			documents.put(permission.getPermissionId(),
					new Document("menuid", permission.getPermissionId()).append("icon", permission.getIcon())
							.append("menuname", permission.getPermissionName()).append("url", permission.getUrl()));
		}

		for (SysPermission permission : permissions) {

			// 取出当前节点
			Document current = documents.get(permission.getPermissionId());

			Document parent = (null != permission.getParent()) ? documents.get(permission.getParent()) : _menus;

			if (null == parent) {
				continue;
			}

			List<Document> parent_menus = (List<Document>) parent.get("menus");

			if (null == parent_menus) {
				parent_menus = new LinkedList<>();
				parent.put("menus", parent_menus);
			}

			parent_menus.add(current);
		}

		model.put("_menus", _menus.toJson());
		return "console/_home";
	}

	@GetMapping("/help")
	@RequiresAuthentication
	public String help() {
		return "console/help";
	}

	@GetMapping("/about")
	public String about() {
		return "console/about";
	}

	@GetMapping("/welcome")
	@RequiresAuthentication
	@RequiresPermissions("welcome")
	public String welcome() {
		return "console/welcome";
	}

	@GetMapping("/lorem")
	@RequiresAuthentication
	public String lorem(ModelMap model) {
		model.put("title", new Faker().lorem().sentence());
		model.put("sentences", new Faker().lorem().sentences(50));
		return "console/lorem";
	}

	@GetMapping("/modifyPwd")
	@RequiresAuthentication
	public String modifyPwd(ModelMap model) {
		model.put("title", new Faker().lorem().sentence());
		model.put("sentences", new Faker().lorem().sentences(50));
		return "console/personalSetting/modifyPwd";
	}

	@GetMapping("/userInfo")
	@RequiresAuthentication
	public String userInfo(ModelMap model) {
		model.put("title", new Faker().lorem().sentence());
		model.put("sentences", new Faker().lorem().sentences(50));
		// 获取当前用户信息
		SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
		SysUser userInfo = sysUserService.getSysUserInfo(currentUser.getId());
		model.put("currUser", userInfo);
		return "console/personalSetting/userInfo";
	}

	@GetMapping("/userlist")
	@RequiresAuthentication
	public String userlist(ModelMap model) {
		model.put("title", new Faker().lorem().sentence());
		model.put("sentences", new Faker().lorem().sentences(50));
		List<SysRole> rolelist = roleService.selectAll();
		model.put("roles", rolelist);

		return "console/sys/userlist";
	}

	@GetMapping("/memberInfo")
	@RequiresAuthentication
	public String memberInfo(ModelMap model) {
		model.put("title", new Faker().lorem().sentence());
		model.put("sentences", new Faker().lorem().sentences(50));
		// 获取当前用户信息
		BizMemberInfo currentMember = (BizMemberInfo) SecurityUtils.getSubject().getPrincipal();
		BizMemberInfo bizMemberInfo = bizMemberInfoService.getMemberInfoById(currentMember.getId());
		model.put("currMember", bizMemberInfo);
		return "console/memberManagement/memberInfo";
	}

}
