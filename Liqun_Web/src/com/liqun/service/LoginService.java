package com.liqun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liqun.dao.LoginDao;
import com.liqun.entity.SysPermission;
import com.liqun.entity.SysRole;
import com.liqun.entity.SysUser;

@Service
public class LoginService {
	@Autowired LoginDao loginDao;
	/**
	 * 根据uid 查询用户信息
	 * @param uid
	 * @return
	 */
	
	public SysUser findUserById(Integer uid) {
		return loginDao.findUserById(uid);
	}
	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	
	public SysUser findUserByUserName(String username) {
		
		return loginDao.findUserByUserName(username);
	}	
	
	/**
	 * 根据角色id 查询角色信息
	 * @param roleId
	 * @return
	 */
	public SysRole findRoleByRoleId(Integer roleId) {
		return loginDao.findRoleByRoleId(roleId);
	}
	/**
	 * 根据角色id查询permission信息
	 * @param roleId
	 * @return
	 */
	public List<SysPermission> findPermissionByRoleId(Integer roleId) {
		return loginDao.findPermissionByRoleId(roleId);
	}
	
}
