package com.liqun.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.liqun.dao.BizMemberInfoDao;
import com.liqun.entity.BizMemberInfo;

/**
 * 会员信息管理操作类
 * 
 * @author LINN
 *
 */
@Service
public class BizMemberInfoService {
	@Autowired
	private BizMemberInfoDao memberInfoDao;

	/**
	 * 根据条件检索会员信息
	 * 
	 * @param pageRequest
	 *            页码
	 * @param starttime
	 *            绑定时间(开始)
	 * @param endtime
	 *            绑定时间(终止)
	 * @param mobile
	 *            手机号码
	 * @return 会员信息
	 */
	public Page<BizMemberInfo> queryByConditions(@Param("p") PageRequest pageRequest, String starttime, String endtime,
			String mobile) {
		List<BizMemberInfo> content = memberInfoDao.queryByConditions(pageRequest, starttime, endtime, mobile);
		long total = memberInfoDao.countAll(starttime, endtime, mobile);
		return new PageImpl<>(content, pageRequest, total);
	}

	/**
	 * 根据Id获取会员信息
	 * 
	 * @param id
	 * @return
	 */
	public BizMemberInfo getMemberInfoById(int id) {
		return memberInfoDao.getMemberInfoById(id);
	}
}
