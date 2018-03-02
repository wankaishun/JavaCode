package com.liqun.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.liqun.dao.InvoiceInquiryDao;
import com.liqun.dao.MProuctDao;
import com.liqun.dto.InvoiceDto;
import com.liqun.entity.MProuct;

@Service
public class MProuctService {
	@Autowired
	private MProuctDao mProuctDao;
	
	
	
	
	
	public Page<MProuct> findAll(@Param("p") PageRequest pageRequest,String fptqm,String fplsh,String gfmc,String gfsh, String starttime,String endtime,String djzt){
		List<MProuct> content = mProuctDao.findAll(pageRequest,fptqm,fplsh,gfmc,gfsh,starttime,endtime,djzt);
		long total = mProuctDao.countAll();
		return new PageImpl<>(content, pageRequest, total);	
	}
}
