package com.liqun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.liqun.dao.SysRoleDao;
import com.liqun.entity.SysRole;

//后台角色service
@Service
public class RoleService {
	
	@Autowired SysRoleDao sysRoleDao;
    public Page<SysRole> findAll(PageRequest pageRequest)
    {
		List<SysRole> content = sysRoleDao.findAll(pageRequest);
		long total = sysRoleDao.countAll();
		
		return new PageImpl<>(content, pageRequest, total);
		
	}
    public List<SysRole> selectAll()
    {
		return sysRoleDao.selectAll();
	}
	
    public int insert(SysRole sysRole)
    {
    	return sysRoleDao.insert(sysRole);
    }
    
    public int update(SysRole sysRole)
    {
    	return sysRoleDao.update(sysRole);
    }
}
