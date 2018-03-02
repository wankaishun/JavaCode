package com.liqun.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.liqun.entity.CommodityMaintenance;
import com.liqun.entity.IBillmain;
import com.liqun.entity.NewCommodityMaintenance;

/**
 * CommodityMaintenanceDao接口
 * @author hzy
 * 商品维护
 */
@Repository
public interface CommodityMaintenanceDao {
	
	//统计检索数量
	long countAll();
	
	//检索商品信息
	List<NewCommodityMaintenance> findAll(@Param("p") PageRequest pageRequest,@Param("realName") String realName, @Param("flag") String flag,@Param("roleId") String roleId);
	
	//新增商品信息
	int insert(CommodityMaintenance commodityMaintenance);
	
	//修改商品信息
	int update(CommodityMaintenance commodityMaintenance);
	
	//删除商品信息
	int delete(Map<String,Integer[]> ids);

	//根据商品id检索商品信息
	CommodityMaintenance get(int spid);
	
	//导出
	List<NewCommodityMaintenance> getIBillmainForExport(@Param("realName") String realName, @Param("flag") String flag,@Param("roleId") String roleId);
}
