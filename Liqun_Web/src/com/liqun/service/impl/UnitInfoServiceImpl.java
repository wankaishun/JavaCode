package com.liqun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liqun.dao.MManageMapper;
import com.liqun.entity.MManage;
import com.liqun.service.UnitInfoService;
@Service
@Transactional
public class UnitInfoServiceImpl implements UnitInfoService{
	@Autowired
	private MManageMapper mManageMapper;
	@Override
	public MManage getUnitInfo() {
		
		return mManageMapper.getMManage();
	}
	@Override
	public int saveMManage(MManage mManage) {
		try {
			mManageMapper.deleteMManage();
			mManageMapper.insertSelective(mManage);
		} catch (Exception e) {
		}
		return 0;
	}

}
