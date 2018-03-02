package com.liqun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.liqun.entity.SysUser;

public interface SysUserDao {
	/**
	 * 修改密码
	 * @param id
	 * @param password
	 * @return
	 */
	int modifyPwd(@Param("id") int id, @Param("password") String password);

	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	SysUser getSysUserInfo(int id);

	/**
	 * 修改用户信息
	 * @param id
	 * @param email
	 * @param realName
	 * @return
	 */
	int editUserInfo(@Param("id") int id, @Param("email") String email, @Param("realName") String realName);

	/**
	 * 获取用户数量，用于分页
	 * @return
	 */
	long countAll();
	
	/**
	 * 模糊查询sys_user用户列表
	 * @param pageRequest
	 * @param realName
	 * @param flag
	 * @param roleId
	 * @return
	 */
	List<SysUser> findAll(@Param("p") PageRequest pageRequest,@Param("realName") String realName, @Param("flag") String flag,@Param("roleId") String roleId);

	int addSysUser(SysUser user);
	
	int editSysUser(SysUser user);
	
	int delSysUser(@Param("id") int id,@Param("flag") String flag);
	
	int resetPwd(int id);
}
