package com.liqun.dao;

import java.util.List;

import com.liqun.entity.SysModel;

public interface SysModelMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SysModel record);

    int insertSelective(SysModel record);

    SysModel selectByPrimaryKey(Integer id);
    
    List<SysModel> getSysModelList();

    int updateByPrimaryKeySelective(SysModel record);

    int updateByPrimaryKey(SysModel record);
}