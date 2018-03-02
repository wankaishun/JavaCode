package com.liqun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.liqun.entity.SysRolePermission;
@Repository
public interface SysRolePermissionDao {

	int insert(SysRolePermission sysRolePermission);

	int update(SysRolePermission sysRolePermission);

	int delete(Long id);
	
	int deleteByRoleId(int roleId);

	List<SysRolePermission> selectAll();
	
	List<SysRolePermission> findByRoleId(Integer roleId);

	long countAll();

	List<SysRolePermission> findAll(@Param("p") PageRequest pageRequest);

	default void save(SysRolePermission sysRolePermission) {
		if (null != sysRolePermission.getId()) update(sysRolePermission); else insert(sysRolePermission);
	}
}
