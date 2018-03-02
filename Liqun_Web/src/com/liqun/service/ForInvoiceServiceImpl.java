package com.liqun.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.javassist.runtime.Inner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liqun.dao.IInvMapper;
import com.liqun.dao.InvoiceManagementDao;
import com.liqun.dto.InvoiceDto;
import com.liqun.entity.IInv;
@Service
@Transactional
public class ForInvoiceServiceImpl {
	@Autowired
	private InvoiceManagementDao invoiceManagementDao;

	public Page<InvoiceDto> findAll(@Param("p") PageRequest pageRequest,String fptqm,String fplsh,String gfmc,String gfsh, String starttime,String endtime,String djzt){
		List<InvoiceDto> content = invoiceManagementDao.findAll(pageRequest,fptqm,fplsh,gfmc,gfsh,starttime,endtime,djzt);
		long total = invoiceManagementDao.countAll();
		return new PageImpl<>(content, pageRequest, total);	
	}
}

