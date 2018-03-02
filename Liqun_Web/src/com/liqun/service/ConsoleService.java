package com.liqun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liqun.dao.ConsoleDao;
import com.liqun.entity.SysPermission;

@Service
public class ConsoleService {
	@Autowired
	ConsoleDao consoleDao;

	public List<SysPermission> findMenusByMenusId(Integer roleId,Integer menuId) {
		List<SysPermission> sysPermission = consoleDao.findMenusByMenusId(roleId,menuId);
		return sysPermission;
	}
	
}
