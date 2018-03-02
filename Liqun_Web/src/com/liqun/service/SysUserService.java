package com.liqun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.liqun.dao.SysUserDao;
import com.liqun.entity.SysUser;

@Service
public class SysUserService {
	@Autowired
	private SysUserDao sysUserDao;

	public SysUser getSysUserInfo(int id) {
		return sysUserDao.getSysUserInfo(id);
	}

	public int modifyPwd(int id, String password) {
		return sysUserDao.modifyPwd(id, password);
	}

	public int editUserInfo(int id, String email, String realName) {
		return sysUserDao.editUserInfo(id, email, realName);
	}
	
    public Page<SysUser> findAll(PageRequest pageRequest,String realName,String flag,String roleId)
    {
		List<SysUser> content = sysUserDao.findAll(pageRequest,realName,flag,roleId);
		long total = sysUserDao.countAll();
		
		return new PageImpl<>(content, pageRequest, total);	
	}
    
    public int addSysUser(SysUser user)
    {
    	return sysUserDao.addSysUser(user);
    }
    
    public int editSysUser(SysUser user)
    {
    	return sysUserDao.editSysUser(user);
    }
    
    public int delSysUser(int id,String flag)
    {
    	return sysUserDao.delSysUser(id,flag);
    }
    
    public int resetPwd(int id)
    {
    	return sysUserDao.resetPwd(id);
    	
    }
}
