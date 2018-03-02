package com.liqun.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liqun.dao.OrgManagementDao;
import com.liqun.entity.MenuItem;
import com.liqun.entity.SysOrganization;

/**
 * 会员信息管理操作类
 * 
 * @author LINN
 *
 */
@Service
public class OrgManagementServiceImpl {
	 @Autowired
	    private OrgManagementDao orgManagementDao;
	 
	    public List<MenuItem> readAllPrivileges(int subSystemId) {
	        List<MenuItem> menuItem = orgManagementDao.readAllPrivileges(subSystemId);

	        return menuItem;
	    }
	    
	    public SysOrganization getOrgInfo(int id) {
	    	return orgManagementDao.selectByPrimaryKey(id);
	    }
	    /**
	     * 新增组织架构
	     * @param sysOrganization
	     * @return
	     */
	    public int saveOrgInfo(SysOrganization sysOrganization) {
	    	int id=sysOrganization.getId();
	    	sysOrganization.setId(null);
	    	sysOrganization.setParentId(id);
	    	sysOrganization.setSubsystemId(2);
	    	return orgManagementDao.insertSelective(sysOrganization);
	    }
	    /**
	     * 根据主键删除 
	     * @param id
	     * @return
	     */
	    public int deleteOrgInfo(Integer[] id) {
	    	Map<String,Integer[]> map=new HashMap<String,Integer[]>();
	    	map.put("ids", id);
	    	orgManagementDao.deleteByPrimaryKey(map);
	    	 return 0;
	    }
}
