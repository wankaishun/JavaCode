package com.liqun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.liqun.dto.InvoiceDto;
import com.liqun.dto.KptjDto;

public interface KptjDao {

	
List<KptjDto> findKptj (@Param("p") PageRequest pageRequest,@Param("fptqm") 
	String fptqm,@Param("fplsh") String fplsh,@Param("gfmc") 
	String gfmc,@Param("gfsh") String gfsh,@Param("starttime") 
	String starttime,@Param("endtime") String endtime,@Param("xfsh") 
	String xfsh,@Param("xfmc") String xfmc,@Param("djzt") String djzt);
	
	long countAll();
	
	
	List<InvoiceDto> getIBillmainForExport(@Param("fptqm") String fptqm, @Param("fplsh") String fplsh,
    		@Param("gfmc") String gfmc, @Param("gfsh") String gfsh, 
    		@Param("starttime") String starttime,@Param("endtime") String endtime,@Param("xfsh") String xfsh,@Param("xfmc") String xfmc,@Param("djzt") String djzt);
}
