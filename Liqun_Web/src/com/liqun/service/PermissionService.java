package com.liqun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liqun.dao.SysPermissionDao;
import com.liqun.entity.MenuItem;
import com.liqun.entity.SysPermission;

//权限控制
@Service
public class PermissionService {

	@Autowired SysPermissionDao sysPermissionDao;
    public List<SysPermission> selectAll()
    {
		List<SysPermission> content = sysPermissionDao.selectAll();
		return content;	
	}
    
	public List<MenuItem> getMenuTree(Integer roleId)
	{
		return sysPermissionDao.getMenuTree(roleId);	
	}
	
	public int insert(SysPermission sysPermission) {
		return sysPermissionDao.insert(sysPermission);
	}

	public int update(SysPermission sysPermission) {
		return sysPermissionDao.update(sysPermission);
	}
	
	public int delete(Long permissionId)
	{
		return sysPermissionDao.delete(permissionId);
	}
}
