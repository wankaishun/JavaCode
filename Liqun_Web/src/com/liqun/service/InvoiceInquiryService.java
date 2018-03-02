package com.liqun.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.liqun.dao.IBilldelMapper;
import com.liqun.dao.IBillmainMapper;
import com.liqun.dao.InvoiceInquiryDao;
import com.liqun.dto.InvoiceDto;
import com.liqun.entity.IBillmain;


@Service
public class InvoiceInquiryService {
	
	@Autowired
	private InvoiceInquiryDao invoiceInquiryDao;
	
	/*@Autowired
	private IBillmainMapper iBillmainMapper;*/
	
	
	
	public Page<InvoiceDto> findAll(@Param("p") PageRequest pageRequest,String fptqm,String fplsh,String gfmc,String gfsh, String starttime,String endtime,String djzt){
		List<InvoiceDto> content = invoiceInquiryDao.findAll(pageRequest,fptqm,fplsh,gfmc,gfsh,starttime,endtime,djzt);
		/*List<InvoiceDto> content1 = invoiceInquiryDao.findList(pageRequest, fptqm, fplsh, gfmc, gfsh, starttime, endtime);*/
		long total = invoiceInquiryDao.countAll();
		return new PageImpl<>(content, pageRequest, total);	
		
	}
	
	
	public List<InvoiceDto> getIBillmainForExport(String fptqm,String fplsh,String gfmc,String gfsh,String starttime,String endtime,String djzt){
		return invoiceInquiryDao.getIBillmainForExport(fptqm, fplsh, gfmc, gfsh, starttime, endtime,djzt);
	}
}
