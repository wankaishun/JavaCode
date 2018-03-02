package com.liqun.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.liqun.entity.SysPermission;
import com.liqun.entity.SysRole;
import com.liqun.entity.SysUser;

import org.springframework.stereotype.Repository;
@Repository
public interface LoginDao {
	/**
	 * 根据roleId查询role信息
	 * @param roleId
	 * @return
	 */
	SysRole findRoleByRoleId(Integer roleId);

	/**
	 * 根据角色的Id 查询permission的信息
	 * @param roleId
	 * @return
	 */
	List<SysPermission> findPermissionByRoleId(Integer roleId);
	
	/**
	 * 根据uid 查询用户信息
	 * @param uid
	 * @return
	 */
	SysUser findUserById(Integer uid);
	
	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	SysUser findUserByUserName(String username);

	
	
	
}
