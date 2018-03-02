package com.liqun.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.liqun.dao.DetailDao;
import com.liqun.dto.InvoiceDto;

@Service
public class DetailService {


	@Autowired
	private DetailDao detailDao;
	
	public Page<InvoiceDto> findDetail(@Param("p") PageRequest pageRequest,String fptqm,String fplsh,String gfmc,String gfsh,String starttime,String endtime,String xfsh,String xfmc,Integer djzt){
		List<InvoiceDto> content = detailDao.findDetail(pageRequest,fptqm, fplsh,gfmc,gfsh,starttime,endtime,xfsh,xfmc,djzt);
		long total = detailDao.countAll();
		return new PageImpl<>(content, pageRequest, total);	
		
	}
	
}
//@Param("p") PageRequest pageRequest,@Param("fptqm") String fptqm,@Param("fplsh") String fplsh,@Param("gfmc") String gfmc,@Param("gfsh") String gfsh,@Param("starttime") String starttime,@Param("endtime") String endtime,@Param("xfsh") String xfsh,@Param("xfmc") String xfmc,@Param("djzt") Integer djzt