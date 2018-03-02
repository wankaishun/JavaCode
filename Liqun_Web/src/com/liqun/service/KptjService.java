package com.liqun.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.liqun.dao.InvoiceInquiryDao;
import com.liqun.dao.KptjDao;
import com.liqun.dto.InvoiceDto;
import com.liqun.dto.KptjDto;

@Service
public class KptjService {

	
	
	@Autowired
	private KptjDao kptjDao;
	
	/*@Autowired
	private IBillmainMapper iBillmainMapper;*/
	
	
	
	public Page<KptjDto> findKptj(@Param("p") PageRequest pageRequest,String fptqm,String fplsh,String gfmc,String gfsh,String starttime,String endtime,String xfsh,String xfmc,String djzt){
		List<KptjDto> content = kptjDao.findKptj(pageRequest,fptqm, fplsh,gfmc,gfsh,starttime,endtime,xfsh,xfmc,djzt);
		long total = kptjDao.countAll();
		return new PageImpl<>(content, pageRequest, total);	
		
	}
	
	
	//导出
			public List<InvoiceDto> getIBillmainForExport(String fptqm,String fplsh,String gfmc,String gfsh,String starttime,String endtime,String xfsh,String xfmc,String djzt){
				return kptjDao.getIBillmainForExport(fptqm, fplsh, gfmc, gfsh, starttime, endtime,xfsh,xfmc,djzt);
			}
			
		
			
			
	
}
