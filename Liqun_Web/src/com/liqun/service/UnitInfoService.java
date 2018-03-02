package com.liqun.service;

import com.liqun.entity.MManage;  
  
/**
 * 用户开票单据存储
 * 
 * @author wks
 *
 */
public interface UnitInfoService {
	public MManage getUnitInfo() ;
	public int saveMManage(MManage mManage);
}