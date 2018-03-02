package com.liqun.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liqun.dao.CustomerDao;
import com.liqun.entity.Customer;
import com.liqun.entity.CustomerDto;
import com.liqun.entity.IBillmain;

@Service
@Transactional
public class CustomerService {
	@Autowired
	private CustomerDao customerDao;

	public Page<CustomerDto> findAll(PageRequest pageRequest, String khmc, String khsh) {
		List<CustomerDto> content = customerDao.findAll(pageRequest, khmc, khsh);
		long total = customerDao.countAll();

		return new PageImpl<>(content, pageRequest, total);
	}

	public int delete(Integer khbh) {
		return customerDao.delete(khbh);

	}
	 public int addCustomer(Customer customer)
	    {
	    	return customerDao.addCustomer(customer);
	    }
	    
	    //修改客户信息
	    public int updateCustomer(Customer customer)
	    {
	    	return customerDao.updateCustomer(customer);
	    }
	    //根据客户id检索客户信息
	    public Customer getCustomerInfo(Integer khbh) 
	    {
	    	return customerDao.getCustomerInfo(khbh);
	    }
	    /**
		 * 获得导出的所有数据list
		 * @param fptqm
		 * @param fplsh
		 * @param gfmc
		 * @param gfsh
		 * @param djzt
		 * @param starttime
		 * @param endtime
		 * @return
		 */
		public List<CustomerDto> getIBillmainForExport(String khmc,String khsh){
			return customerDao.getIBillmainForExport(khmc, khsh);
		}
		/*public int addcolumn(String addcolumn) {
			return customerDao.addcolumn(addcolumn);
		}*/
		public int importExcel(List<Customer> list){
			return customerDao.importExcel(list);
			}
}
