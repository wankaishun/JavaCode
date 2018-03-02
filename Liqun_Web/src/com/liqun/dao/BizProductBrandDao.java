package com.liqun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.liqun.entity.BizProductBrand;
import com.liqun.entity.BizProductCategoryBrandVW;
@Repository
public interface BizProductBrandDao {

	int insert(BizProductBrand bizProductBrand);

	int update(BizProductBrand bizProductBrand);

	int delete(String id);

	List<BizProductBrand> selectAll();

	long countAll(@Param("productBrandName") String productBrandName,@Param("flag") String flag);

	List<BizProductBrand> findAll(@Param("productBrandName") String productBrandName,@Param("flag") String flag,@Param("p") PageRequest pageRequest);
	
	List<BizProductCategoryBrandVW> findBrandbyCategoryId(@Param("productBrandName") String productBrandName, @Param("categoryId") Long categoryId , @Param("p") PageRequest pageRequest);
	long findBrandbyCategoryIdCount(@Param("productBrandName") String productBrandName,@Param("categoryId") Long categoryId);
	
	List<BizProductBrand> findBrandUnLinkedByCategoryId(@Param("productBrandName") String productBrandName, @Param("categoryId") Long categoryId , @Param("p") PageRequest pageRequest);
	long findBrandUnLinkedByCategoryIdCount(@Param("productBrandName") String productBrandName,@Param("categoryId") Long categoryId);
	
	
	
	
	default void save(BizProductBrand bizProductBrand) {
		if (null != bizProductBrand.getId()) 
			update(bizProductBrand); 
		else insert(bizProductBrand);
		
	}	
	long brandExist(@Param("productBrandNo") String productBrandNo);
}
