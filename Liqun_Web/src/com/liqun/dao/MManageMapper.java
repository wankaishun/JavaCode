package com.liqun.dao;

import com.liqun.entity.MManage;

public interface MManageMapper {

    int deleteByPrimaryKey(Integer pzbh);

    int insert(MManage record);

    int insertSelective(MManage record);

    MManage selectByPrimaryKey(Integer pzbh);
    
    MManage getMManage();

    int updateByPrimaryKeySelective(MManage record);

    int updateByPrimaryKey(MManage record);
    
    int deleteMManage();
}