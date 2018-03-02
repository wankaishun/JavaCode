package com.liqun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liqun.entity.SysPermission;

public interface ConsoleDao {
	/**
	 * 根据menuId 查询permission 数据
	 * @param menuId
	 * @return
	 */
	List<SysPermission> findMenusByMenusId(@Param("roleId") Integer roleId,@Param("menuId") Integer menuId);

}
