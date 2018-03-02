package com.liqun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.liqun.entity.SysLogInfo;

public interface SysLogInfoDao {
	/**
	 * 根据条件检索日志
	 * 
	 * @param startTime
	 *            操作时间(开始)
	 * @param endTime
	 *            操作时间(终止)
	 * @param realname
	 *            操作用户
	 * @param pageRequest
	 *            页码范围
	 * @return 日志信息
	 */
	List<SysLogInfo> queryByConditions(@Param("p") PageRequest pageRequest, @Param("starttime") String starttime,
			@Param("endtime") String endtime, @Param("realname") String realname);

	/**
	 * 根据条件检索日志,统计记录个数
	 * 
	 * @param starttime
	 *            操作时间(开始)
	 * @param endtime
	 *            操作时间(终止)
	 * @param realname
	 *            操作用户
	 * @return 统计记录个数
	 */
	long countAll(@Param("starttime") String starttime, @Param("endtime") String endtime,
			@Param("realname") String realname);
}
