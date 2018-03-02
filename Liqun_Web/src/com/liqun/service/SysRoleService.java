package com.liqun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liqun.dao.SysRoleDao;
import com.liqun.entity.SysRole;

@Service
public class SysRoleService {
	@Autowired
	SysRoleDao sysRoleDao;

	public SysRole findByRoleId(int roleId) {
		SysRole content = sysRoleDao.findByRoleId(roleId);
		return content;
	}

	public int insert(SysRole sysRole) {
		return sysRoleDao.insert(sysRole);
	}

	public int update(SysRole sysRole) {
		return sysRoleDao.update(sysRole);
	}

}
