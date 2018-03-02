package com.liqun.dao;

import com.htxx.pojo.i_inv;
import com.liqun.dto.lqinput.searchbill.Input;
import com.liqun.entity.IInv;

public interface IInvMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(IInv record);

    int insertSelective(IInv record);

    IInv selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IInv record);

    int updateByPrimaryKey(IInv record);
    
    i_inv getInvBy(Input input);
}