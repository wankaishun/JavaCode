package com.liqun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liqun.entity.BmSpflZtree;
import com.liqun.entity.MenuItem;
import com.liqun.entity.NewMenuItem;


/**
 * 税收分类编码属性菜单
 * @author lenovo
 * dao接口
 */
public interface BmSpflZtreeDao {
	
  	/**
	 * 查询树
	 * @param 
	 * @return
	 */
	List<NewMenuItem> getCmTree(@Param("ishide")int ishide);
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	BmSpflZtree selectKey(String bm);
	
	List<NewMenuItem> selectSubPrivileges(@Param("bm") Integer bm );
  	
}
