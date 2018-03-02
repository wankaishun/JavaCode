package com.liqun.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.liqun.entity.Customer;
import com.liqun.entity.CustomerDto;
import com.liqun.entity.IBillmain;
import com.liqun.entity.SysUser;


public interface CustomerDao {
	/**
	 * 获取用户数量，用于分页
	 * @return
	 */
	long countAll();
	
	/**
	
	 * @param pageRequest
	 * @param realName
	 * @param flag
	 * @param roleId
	 * @return
	 */
	List<CustomerDto> findAll(@Param("p") PageRequest pageRequest,@Param("khmc") String khmc, @Param("khsh") String khsh);
    int delete(int khbh);
    int addCustomer(Customer customer);  
    Customer getCustomerInfo(Integer khbh);
    int updateCustomer(Customer customer);
    //导出
    List<CustomerDto> getIBillmainForExport(@Param("khmc") String khmc,
    		@Param("khsh") String khsh);
   // int addcolumn(String addcolumn);
    int importExcel(List<Customer> list);
}
