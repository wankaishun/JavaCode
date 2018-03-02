package com.liqun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.liqun.entity.BizProductCategory;;
@Repository
public interface BizProductCategoryDao {

	int insert(BizProductCategory bizProductCategory);

	int update(BizProductCategory bizProductCategory);

	int delete(Long ProductCategoryId);

	List<BizProductCategory> selectAll();

	long countAll(@Param("productCategoryName") String productCategoryName,@Param("flag") String flag);

	List<BizProductCategory> findAll(@Param("productCategoryName") String productCategoryName,@Param("flag") String flag,@Param("p") PageRequest pageRequest);

	default void save(BizProductCategory bizProductCategory) {
		if (null != bizProductCategory.getId()) 
			update(bizProductCategory); 
		else insert(bizProductCategory);
	}
	long categoryExist(@Param("productCategoryNo") String productCategoryNo);

	
}
