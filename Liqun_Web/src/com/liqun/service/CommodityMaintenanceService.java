package com.liqun.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.liqun.dao.BmSpflZtreeDao;
import com.liqun.dao.CommodityMaintenanceDao;
import com.liqun.entity.BmSpflZtree;
import com.liqun.entity.CommodityMaintenance;
import com.liqun.entity.NewCommodityMaintenance;
import com.liqun.entity.NewMenuItem;

/**
 * CommodityMaintenanceService
 * @author lenovo
 * 商品维护
 */
@Service
public class CommodityMaintenanceService {
	@Autowired
	private CommodityMaintenanceDao commodityMaintenanceDao;
	
	@Autowired
	private BmSpflZtreeDao bmSpflZtreeDao;
	
	//检索商品信息
    public Page<NewCommodityMaintenance> findAll(PageRequest pageRequest,String realName,String flag,String roleId)
    {
		List<NewCommodityMaintenance> content = commodityMaintenanceDao.findAll(pageRequest,realName,flag,roleId);
		long total = commodityMaintenanceDao.countAll();
		
		return new PageImpl<>(content, pageRequest, total);	
	}
    
    //新增商品信息
    public int insert(CommodityMaintenance commodityMaintenance)
    {
    	return commodityMaintenanceDao.insert(commodityMaintenance);
    }
    
    //修改商品信息
    public int update(CommodityMaintenance commodityMaintenance)
    {
    	return commodityMaintenanceDao.update(commodityMaintenance);
    }
    //批量删除商品信息
    public int delete(Integer[] spid) 
    {
    	Map<String,Integer[]> map=new HashMap<String,Integer[]>();
    	map.put("ids", spid);
    	return commodityMaintenanceDao.delete(map);
    }
    
    //根据商品id检索商品信息
    public CommodityMaintenance get(int spid) 
    {
    	return commodityMaintenanceDao.get(spid);
    }
    
    //根据属性菜单检索税收信息
    public List<NewMenuItem> getCmTree(int ishide) {
        List<NewMenuItem> menuItem = bmSpflZtreeDao.getCmTree(ishide);
        return menuItem;
    }
    
    //根据主键查询树形菜单信息
    public BmSpflZtree getCmInfo(String bm) {
    	return bmSpflZtreeDao.selectKey(bm);
    }
    
    //导出
    public List<NewCommodityMaintenance> getIBillmainForExport(String realName,String flag,String roleId){
		return commodityMaintenanceDao.getIBillmainForExport(realName, flag, roleId);
	}
}
