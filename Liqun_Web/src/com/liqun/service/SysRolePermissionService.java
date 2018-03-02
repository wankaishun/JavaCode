package com.liqun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.liqun.dao.SysRolePermissionDao;
import com.liqun.entity.SysRolePermission;

@Service
public class SysRolePermissionService {
	@Autowired SysRolePermissionDao sysRolePermissionDao;
	public List<SysRolePermission> findByRoleId(Integer roleId)
	{
		List<SysRolePermission> content = sysRolePermissionDao.findByRoleId(roleId);
		return content;	
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int deleteByRoleId(int roleId)
	{
		return sysRolePermissionDao.deleteByRoleId(roleId);
	}
	
	public 	int insert(SysRolePermission sysRolePermission)
	{
		return sysRolePermissionDao.insert(sysRolePermission);
	}
}
