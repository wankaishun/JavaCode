package com.liqun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.liqun.entity.MProuct;

public interface MProuctDao {

	

	List<MProuct> findAll(@Param("p") PageRequest pageRequest,@Param("fptqm") String fptqm,@Param("fplsh") String fplsh,@Param("gfmc") String gfmc,@Param("gfsh") String gfsh,@Param("starttime") String starttime,@Param("endtime") String endtime,@Param("djzt") String djzt);
	
	
	long countAll();
}
