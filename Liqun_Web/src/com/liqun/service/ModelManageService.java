package com.liqun.service;

import java.util.List;

import com.liqun.entity.SysModel;

public interface ModelManageService {
	public int saveModel(SysModel record);
	public List<SysModel> getSysModelList();
	public SysModel getSysModelById(int id);
}
