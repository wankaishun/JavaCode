package com.liqun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.liqun.entity.MenuItem;
import com.liqun.entity.SysPermission;
@Repository
public interface SysPermissionDao {

	int insert(SysPermission sysPermission);

	int update(SysPermission sysPermission);

	int delete(Long permissionId);

	List<SysPermission> selectAll();

	long countAll();

	List<SysPermission> findAll(@Param("p") PageRequest pageRequest);

	default void save(SysPermission sysPermission) {
		if (null != sysPermission.getPermissionId()) update(sysPermission); else insert(sysPermission);
	}
	
	List<MenuItem> getMenuTree(@Param("roleId") Integer roleId);
}
