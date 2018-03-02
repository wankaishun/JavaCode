package com.liqun.controller.api;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liqun.entity.MenuItem;
import com.liqun.entity.RoleDetialViewModel;
import com.liqun.entity.SysPermission;
import com.liqun.entity.SysRole;
import com.liqun.entity.SysRolePermission;
import com.liqun.entity.SysUser;
import com.liqun.service.PermissionService;
import com.liqun.service.RoleService;
import com.liqun.service.SysLogService;
import com.liqun.service.SysRolePermissionService;
import com.liqun.service.SysRoleService;
import com.liqun.util.DataGridPage;

//增删改操作类
@Controller
@RequestMapping("/api")
public class RoleApiController {
	@Autowired
	RoleService roleService;
	@Autowired
	PermissionService permissionService;
	@Autowired
	SysRolePermissionService sysRolePermissionService;
	@Autowired
	SysRoleService sysRoleService;
	@Autowired SysLogService sysLogService;

	// 获取角色列表
	@GetMapping("/roleList")
	@ResponseBody
	public Object roleList(@RequestParam int page, @RequestParam int rows) {
		// 数据库分页 当前页需要减一
		Page<SysRole> rolelist = roleService.findAll(PageRequest.of(page - 1, rows));

		return DataGridPage.pageToGrid(rolelist);
	}

	// 获取权限树Json
	@RequestMapping("/permissionTree")
	@ResponseBody
	public Object permissionJson() {
		// 数据库分页 当前页需要减一
		List<SysPermission> permissionlist = permissionService.selectAll();
		// 组装权限树

		return permissionlist;
	}

	// 获取菜单树Json
	/*
	 * .sorted((MenuItem o1, MenuItem o2) -> {//有小到大排序 return
	 * o1.getText().length()-o2.getText().length(); })
	 */
	@RequestMapping(value = "getMenuTree", method = RequestMethod.POST)
	@ResponseBody
	public Object getMenuTree(int roleId) {
		RoleDetialViewModel roleDetialViewModel = new RoleDetialViewModel();
		List<MenuItem> menuList = permissionService.getMenuTree(roleId);
		// 根节点
		List<MenuItem> parentList = menuList.stream().filter(e -> {
			e.setState("open");
			return true;
		}).filter(m -> m.getMenu() == 1).sorted((MenuItem o1, MenuItem o2) -> o1.getSort() - o2.getSort())
				.collect(Collectors.toList());

		for (MenuItem item : parentList) {
			item.setChecked(false);
			List<MenuItem> childList = menuList.stream().filter(m -> m.getParentId() == item.getId())
					.sorted((MenuItem o1, MenuItem o2) -> o1.getSort() - o2.getSort())
					.collect(Collectors.toList());
			item.setChildren(childList);
			for (MenuItem child : childList) {
				List<MenuItem> thirdList = menuList.stream().filter(m -> m.getParentId() == child.getId())
						.sorted((MenuItem o1, MenuItem o2) -> o1.getSort() - o2.getSort())
						.collect(Collectors.toList());
				List<MenuItem> thirdCheckedList = menuList.stream()
						.filter(m -> m.getParentId() == child.getId() && m.isChecked()).collect(Collectors.toList());
				if (thirdList.size() != thirdCheckedList.size()) {
					child.setChecked(false);
				}
				child.setChildren(thirdList);
			}
		}

		// 编辑时绑定已经选择的权限 0为新增
		if (0 != roleId) {
			roleDetialViewModel.setSysRole(sysRoleService.findByRoleId(roleId));
		}
		roleDetialViewModel.setMenuItemList(parentList);
		return roleDetialViewModel;
	}

	// 新增或编辑权限
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(SysRole sysRole) {
		try {
			int roleId = sysRole.getRoleId() == null ? 0 : sysRole.getRoleId();
			sysRole.setFlag("1");
			SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
			if (roleId != 0) {
				// edit
				// 1、根据roleId清空权限表的数据
				sysRolePermissionService.deleteByRoleId(roleId);
				// 2、编辑角色
				roleService.update(sysRole);
				// 3、重新绑定菜单权限
				if (sysRole.getMenuItems() != null && sysRole.getMenuItems() != "") {
					List<String> menuList = Arrays.asList(sysRole.getMenuItems().split(","));
					for (String menuId : menuList) {
						SysRolePermission permission = new SysRolePermission();
						permission.setRoleId(roleId);
						permission.setPermissionId(Integer.parseInt(menuId));
						permission.setFlag("1");
						permission.setUpdateUser(currentUser.getAccount());
						permission.setUpdateDate(new Date());
						sysRolePermissionService.insert(permission);
					}
				}
				sysLogService.insertLog(47,"修改了名称为：{"+sysRole.getRoleName()+"}的角色信息");

			} else {
				// add
				// 1、添加角色
				roleService.insert(sysRole);
				int autoRoleId = sysRole.getRoleId();
				// 2、绑定菜单权限
				if (sysRole.getMenuItems() != null && sysRole.getMenuItems() != "") {
					List<String> menuList = Arrays.asList(sysRole.getMenuItems().split(","));
					for (String menuId : menuList) {
						SysRolePermission permission = new SysRolePermission();
						permission.setRoleId(autoRoleId);
						permission.setPermissionId(Integer.parseInt(menuId));
						permission.setFlag("1");
						permission.setCreateUser(currentUser.getAccount());
						permission.setCreateDate(new Date());
						sysRolePermissionService.insert(permission);
					}
				}
				sysLogService.insertLog(46,"添加了名称为：{"+sysRole.getRoleName()+"}的角色信息");
			}
			return "1";
		} catch (Exception e) {
			// TODO: handle exception
			return "-1";
		}
	}
}
