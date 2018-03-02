package com.liqun.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.liqun.entity.MenuItem;
import com.liqun.entity.SysOrganization;

public interface OrgManagementDao {
	/**
	 * 查询树
	 * @param subSystemId
	 * @return
	 */
	List<MenuItem> readAllPrivileges(@Param("subSystemId")int subSystemId);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	SysOrganization selectByPrimaryKey(Integer id);
	/**
	 * 增加一条记录
	 * @param record
	 * @return
	 */
	int insert(SysOrganization record);
	/**
	 * 根据传入的值增加
	 * @param record
	 * @return
	 */
	int insertSelective(SysOrganization record);
	
	List<MenuItem> selectSubPrivileges(@Param("id")int id );
	
	int deleteByPrimaryKey(Map<String,Integer[]> ids);
	
	
}
