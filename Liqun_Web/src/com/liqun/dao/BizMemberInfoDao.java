package com.liqun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import com.liqun.entity.BizMemberInfo;

public interface BizMemberInfoDao {
	/**
	 * 根据条件检索会员信息
	 * 
	 * @param startTime
	 *            绑定时间(开始)
	 * @param endTime
	 *            绑定时间(终止)
	 * @param mobile
	 *            手机号
	 * @param pageRequest
	 *            页码范围
	 * @return 会员管理信息
	 */
	List<BizMemberInfo> queryByConditions(@Param("p") PageRequest pageRequest, @Param("starttime") String starttime,
			@Param("endtime") String endtime, @Param("mobile") String mobile);

	/**
	 * 根据条件检索会员信息,统计记录个数
	 * 
	 * @param starttime
	 *            操作时间(开始)
	 * @param endtime
	 *            操作时间(终止)
	 * @param mobile
	 *            手机号
	 * @return 统计记录个数
	 */
	long countAll(@Param("starttime") String starttime, @Param("endtime") String endtime,
			@Param("mobile") String mobile);

	/**
	 * 获取会员信息
	 * 
	 * @param id
	 *            会员编号
	 * @return 会员信息
	 */
	BizMemberInfo getMemberInfoById(int id);
}
