package com.liqun.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.htxx.pojo.i_billdel;
import com.liqun.dto.lqinput.searchbill.Input;
import com.liqun.entity.IBilldel;

public interface IBilldelMapper {

    int insert(IBilldel iBilldel);

    int insertSelective(IBilldel iBilldel);
    
    List<i_billdel> getBilldelBy(Input input);
    List<IBilldel> getIBilldel(@Param("p") PageRequest pageRequest, @Param("fplsh") String fplsh );
    long countAll(@Param("fplsh") String fplsh);
    
    List<IBilldel> getBilldelMerge(Map<String,Object> map);
    
    int insertBilldelList(Map<String,Object> map);
    
    List<IBilldel> getBilldeList(Map<String,Object> map);
}