package com.liqun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liqun.dao.BizProductCategoryBrandServiceDao;
import com.liqun.entity.BizProductCategoryBrandService;

//后台角色service
@Service
public class BizProductCategoryBrandServiceService {
	
	@Autowired BizProductCategoryBrandServiceDao bizProductCategoryBrandServiceDao;   
	public int insert(BizProductCategoryBrandService bizProductCategoryBrandService)
	 {
		 return bizProductCategoryBrandServiceDao.insert(bizProductCategoryBrandService);
	 }
	 public int updateCanInstall(BizProductCategoryBrandService bizProductCategoryBrandService)
	 {
		 return bizProductCategoryBrandServiceDao.updateCanInstall(bizProductCategoryBrandService);
	 }
	 public int updateCanRepair(BizProductCategoryBrandService bizProductCategoryBrandService)
	 {
		 return bizProductCategoryBrandServiceDao.updateCanRepair(bizProductCategoryBrandService);
	 }
	 public int delete(String ids)
	 {
		 return bizProductCategoryBrandServiceDao.delete(ids);
	 }
	 
}