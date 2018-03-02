package com.liqun.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.liqun.dao.SysLogInfoDao;
import com.liqun.entity.SysLogInfo;

/**
 * 日志查询操作类
 * @author LINN
 *
 */
@Service
public class SysLogInfoService {
	@Autowired
	private SysLogInfoDao sysLogInfoDao;

	/**
	 * 根据条件检索日志信息
	 * @param pageRequest 页码
	 * @param starttime 创建时间(开始)
	 * @param endtime 创建时间(终止)
	 * @param realname 操作用户
	 * @return 日志信息
	 */
	public Page<SysLogInfo> queryByConditions(@Param("p") PageRequest pageRequest, String starttime, String endtime,
			String realname) {
		List<SysLogInfo> content = sysLogInfoDao.queryByConditions(pageRequest, starttime, endtime, realname);
		long total = sysLogInfoDao.countAll(starttime, endtime, realname);
		return new PageImpl<>(content, pageRequest, total);
	}
}
