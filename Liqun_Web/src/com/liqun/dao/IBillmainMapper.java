package com.liqun.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.htxx.pojo.i_billmain;
import com.liqun.dto.lqinput.searchbill.Input;
import com.liqun.entity.IBillmain;

public interface IBillmainMapper {

    int insert(IBillmain iBillmain);

    int insertSelective(IBillmain iBillmain);

    i_billmain getBillmainBy(Input input);
    
    List<IBillmain> getIBillmainList(@Param("p") PageRequest pageRequest,@Param("fptqm") String fptqm, @Param("fplsh") String fplsh,
    		@Param("gfmc") String gfmc, @Param("gfsh") String gfsh, @Param("djzt") String djzt,
    		@Param("starttime") String starttime,@Param("endtime") String endtime);
    long countAll(@Param("fptqm") String fptqm, @Param("fplsh") String fplsh,
    		@Param("gfmc") String gfmc, @Param("gfsh") String gfsh,@Param("djzt") String djzt,
    		@Param("starttime") String starttime,@Param("endtime") String endtime);
    List<IBillmain> getIBillmainForExport(@Param("fptqm") String fptqm, @Param("fplsh") String fplsh,
    		@Param("gfmc") String gfmc, @Param("gfsh") String gfsh, @Param("djzt") String djzt,
    		@Param("starttime") String starttime,@Param("endtime") String endtime);
    int updateDjzt(Map<String,Object> map);
    /*
     * 查询出合并之后的数据
     */
    IBillmain getIBillmain(Map<String,Object> map);
    /**
     * 根据发票流水号进行查询
     * @param map
     * @return
     */
    List<IBillmain> getIBillmainByFPLSH(Map<String,Object> map);
    
}