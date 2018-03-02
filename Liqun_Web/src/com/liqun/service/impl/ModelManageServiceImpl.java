package com.liqun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liqun.dao.SysModelMapper;
import com.liqun.entity.SysModel;
import com.liqun.service.ModelManageService;
@Service
public class ModelManageServiceImpl implements ModelManageService{
	@Autowired
	private SysModelMapper sysModelMapper;
	public int saveModel(SysModel record) {
		sysModelMapper.insertSelective(record);
		return 1;
	}
	@Override
	public List<SysModel> getSysModelList() {
		// TODO Auto-generated method stub
		return sysModelMapper.getSysModelList();
	}
	@Override
	public SysModel getSysModelById(int id) {
		// TODO Auto-generated method stub
		return sysModelMapper.selectByPrimaryKey(id);
	};
}
