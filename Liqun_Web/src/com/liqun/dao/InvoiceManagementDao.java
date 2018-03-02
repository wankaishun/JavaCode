package com.liqun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.liqun.dto.InvoiceDto;

public interface InvoiceManagementDao {
	long countAll();
	List<InvoiceDto> findAll(@Param("p") PageRequest pageRequest,@Param("fptqm") String fptqm,@Param("fplsh") String fplsh,@Param("gfmc") String gfmc,@Param("gfsh") String gfsh,@Param("starttime") String starttime,@Param("endtime") String endtime,@Param("djzt") String djzt);

}
