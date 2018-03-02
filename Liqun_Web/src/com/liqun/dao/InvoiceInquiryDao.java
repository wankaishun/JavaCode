package com.liqun.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.liqun.dto.InvoiceDto;
import com.liqun.entity.IBillmain;
import com.liqun.entity.InvoiceInquiry;
import com.liqun.entity.SysUser;

public interface InvoiceInquiryDao {

	List<InvoiceDto> findAll(@Param("p") PageRequest pageRequest,@Param("fptqm") String fptqm,@Param("fplsh") String fplsh,@Param("gfmc") String gfmc,@Param("gfsh") String gfsh,@Param("starttime") String starttime,@Param("endtime") String endtime,@Param("djzt") String djzt);
	
	
	long countAll();
	
	/*List<InvoiceDto> findList(@Param("p") PageRequest pageRequest,@Param("fptqm") String fptqm,@Param("fplsh") String fplsh,@Param("gfmc") String gfmc,@Param("gfsh") String gfsh,@Param("starttime") String starttime,@Param("endtime") String endtime);*/
	
	
	List<InvoiceDto> getIBillmainForExport(@Param("fptqm") String fptqm, @Param("fplsh") String fplsh,
    		@Param("gfmc") String gfmc, @Param("gfsh") String gfsh, 
    		@Param("starttime") String starttime,@Param("endtime") String endtime,@Param("djzt") String djzt);
	IBillmain getIBillmain(Map<String,Object> map);
}
