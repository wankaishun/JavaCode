package com.liqun.dao;

import java.util.List;

import com.liqun.entity.SysDict;

public interface SysDictMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SysDict record);

    int insertSelective(SysDict record);

    SysDict selectByPrimaryKey(Integer id);
    
    List<SysDict> selectBySysDict(Integer code);
    
    int updateByPrimaryKey(SysDict record);
}