package com.liqun.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.liqun.dto.InvoiceDto;
public interface DetailDao {

List<InvoiceDto> findDetail (@Param("p") PageRequest pageRequest,@Param("fptqm") String fptqm,@Param("fplsh") String fplsh,@Param("gfmc") String gfmc,@Param("gfsh") String gfsh,@Param("starttime") String starttime,@Param("endtime") String endtime,@Param("xfsh") String xfsh,@Param("xfmc") String xfmc,@Param("djzt") Integer djzt);
	
	long countAll();
	
}
