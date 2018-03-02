package com.liqun.dao;

import com.liqun.entity.SysLog;

public interface SysLogDao {

	/**
	 * 日志插入
	 * 
	 * @param sysLog
	 *            日志信息
	 * @return 0:插入失败;非0:插入成功
	 */
	int insert(SysLog sysLog);
}
