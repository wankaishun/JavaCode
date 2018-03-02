package com.liqun.entity;

import java.io.Serializable;
import java.util.List;
//角色详情页面视图模型
public class RoleDetialViewModel implements Serializable {
	private List<MenuItem> menuItemList;
	private SysRole sysRole;
	public List<MenuItem> getMenuItemList() {
		return menuItemList;
	}
	public void setMenuItemList(List<MenuItem> menuItemList) {
		this.menuItemList = menuItemList;
	}
	public SysRole getSysRole() {
		return sysRole;
	}
	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}
	public RoleDetialViewModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
