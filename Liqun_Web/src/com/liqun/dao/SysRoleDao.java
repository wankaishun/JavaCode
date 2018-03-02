package com.liqun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.liqun.entity.SysRole;
@Repository
public interface SysRoleDao {

	int insert(SysRole sysRole);

	int update(SysRole sysRole);

	int delete(Long roleId);

	List<SysRole> selectAll();

	SysRole findByRoleId(int roleId);
	
	long countAll();

	List<SysRole> findAll(@Param("p") PageRequest pageRequest);

	default void save(SysRole sysRole) {
		if (null != sysRole.getRoleId()) update(sysRole); else insert(sysRole);
	}
	
}
