package com.liqun.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.liqun.dao.BizProductBrandDao;
import com.liqun.entity.BizProductBrand;
import com.liqun.entity.BizProductCategoryBrandVW;

//后台角色service
@Service
public class BizProductBrandService {
	
	@Autowired BizProductBrandDao bizProductBrandDao;
    public Page<BizProductBrand> findAll(String productBrandName,String flag,PageRequest pageRequest)
    {
		List<BizProductBrand> content = bizProductBrandDao.findAll(productBrandName,flag,pageRequest);
		long total = bizProductBrandDao.countAll(productBrandName,flag);
		
		return new PageImpl<>(content, pageRequest, total);	
	}
    public Page<BizProductCategoryBrandVW> findBrandbyCategoryId(String productBrandName,Long categoryId,PageRequest pageRequest)
    {
		List<BizProductCategoryBrandVW> content = bizProductBrandDao.findBrandbyCategoryId(productBrandName,categoryId,pageRequest);
		long total = bizProductBrandDao.findBrandbyCategoryIdCount(productBrandName,categoryId);
		
		return new PageImpl<>(content, pageRequest, total);	
	}
    public Page<BizProductBrand> findBrandUnLinkedByCategoryId(String productBrandName,Long categoryId,PageRequest pageRequest)
    {
		List<BizProductBrand> content = bizProductBrandDao.findBrandUnLinkedByCategoryId(productBrandName,categoryId,pageRequest);
		long total = bizProductBrandDao.findBrandUnLinkedByCategoryIdCount(productBrandName,categoryId);		
		return new PageImpl<>(content, pageRequest, total);	
	}
    public List<BizProductBrand> selectAll()
    {
    	return bizProductBrandDao.selectAll();
    }
    
    public int AddBizProductBrand(BizProductBrand bizProductBrand)
    {
    	return bizProductBrandDao.insert(bizProductBrand);
    }
    
    public int UpdateBizProductBrand(BizProductBrand bizProductBrand)
    {
    	return bizProductBrandDao.update(bizProductBrand);
    }
    public long brandExist(String productBrandNo)
    {
    	return bizProductBrandDao.brandExist(productBrandNo);
    }
    
    
  
}