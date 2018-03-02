package com.liqun.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liqun.aop.DbLoggable;
import com.liqun.controller.advice.ApiControllerAdvisable;
import com.liqun.entity.BizMemberInfo;
import com.liqun.service.BizMemberInfoService;
import com.liqun.util.DataGridPage;

@Controller
@RequestMapping("/api")
@ApiControllerAdvisable
public class MemberManagementApiController {
	// log
	private static final Logger logger = LoggerFactory.getLogger(MemberManagementApiController.class);
	@Autowired
	BizMemberInfoService bizMemberInfoService;

	@GetMapping("/memberList")
	@ResponseBody
	@DbLoggable(describe = "查询：分页查询会员信息")
	public Object memberList(@RequestParam int page, @RequestParam int rows, String starttime, String endtime,
			String mobile) {
		if (logger.isInfoEnabled())
			logger.info("MemberManagementApiController.sysuserList START");
		// 数据库分页 当前页需要减一
		Page<BizMemberInfo> memberInfoList = bizMemberInfoService.queryByConditions(PageRequest.of(page - 1, rows),
				starttime, endtime, mobile);
		if (logger.isInfoEnabled())
			logger.info("MemberManagementApiController.sysuserList END");
		return DataGridPage.pageToGrid(memberInfoList);
	}

	/**
	 * 根据id获取会员详细数据
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/getMemberInfoById")
	@ResponseBody
	public BizMemberInfo getMemberInfoById(int id) {
		if (logger.isInfoEnabled())
			logger.info("MemberManagementApiController.getMemberInfoById START");
		if (logger.isInfoEnabled())
			logger.info("MemberManagementApiController.getMemberInfoById END");
		return bizMemberInfoService.getMemberInfoById(id);
	}
}
