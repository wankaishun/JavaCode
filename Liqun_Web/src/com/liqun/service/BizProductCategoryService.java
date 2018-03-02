package com.liqun.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.liqun.dao.BizProductCategoryDao;
import com.liqun.entity.BizProductCategory;

//后台角色service
@Service
public class BizProductCategoryService {
	
	@Autowired BizProductCategoryDao bizProductCategoryDao;
  public Page<BizProductCategory> findAll(String productCategoryName,String flag,PageRequest pageRequest)
  {
		List<BizProductCategory> content = bizProductCategoryDao.findAll(productCategoryName,flag,pageRequest);
		long total = bizProductCategoryDao.countAll(productCategoryName,flag);
		
		return new PageImpl<>(content, pageRequest, total);	
	}
  
  public List<BizProductCategory> selectAll()
  {
  	return bizProductCategoryDao.selectAll();
  }
  
  public int AddBizProductCategory(BizProductCategory bizProductCategory)
  {
  	return bizProductCategoryDao.insert(bizProductCategory);
  }
  
  public int updateBizProductCategory(BizProductCategory bizProductCategory)
  {
  	return bizProductCategoryDao.update(bizProductCategory);
  }
  public long categoryExist(String productCategoryNo)
  {
  	return bizProductCategoryDao.categoryExist(productCategoryNo);
  }

}