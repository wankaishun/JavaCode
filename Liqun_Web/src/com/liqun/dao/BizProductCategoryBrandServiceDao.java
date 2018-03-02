package com.liqun.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.liqun.entity.BizProductCategoryBrandService;;
@Repository
public interface BizProductCategoryBrandServiceDao {

	int insert(BizProductCategoryBrandService bizProductCategoryBrandService);

	int updateCanInstall(BizProductCategoryBrandService bizProductCategoryBrandService);
	int updateCanRepair(BizProductCategoryBrandService bizProductCategoryBrandService);
	
	int delete(@Param("ids") String ids);

	

	
}
