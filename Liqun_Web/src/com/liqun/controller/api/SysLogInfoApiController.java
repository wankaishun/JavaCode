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

import com.liqun.entity.SysLogInfo;
import com.liqun.service.SysLogInfoService;
import com.liqun.util.DataGridPage;

/**
 * 日志查询操作类
 * 
 * @author LINN
 *
 */
@Controller
@RequestMapping("/api")
public class SysLogInfoApiController {
	// log
	private static final Logger logger = LoggerFactory.getLogger(SysLogInfoApiController.class);
	@Autowired
	SysLogInfoService sysLogInfoService;

	@GetMapping("/sysloginfo")
	@ResponseBody
	public Object sysLogInfo(@RequestParam int page, @RequestParam int rows, @RequestParam String starttime,
			String endtime, String realname) {
		if (logger.isInfoEnabled())
			logger.info("SysLogInfoApiController.sysLogInfo START");
		// 数据库分页 当前页需要减一
		Page<SysLogInfo> rolelist = sysLogInfoService.queryByConditions(PageRequest.of(page - 1, rows), starttime,
				endtime, realname);
		if (logger.isInfoEnabled())
			logger.info("SysLogInfoApiController.sysLogInfo END");

		return DataGridPage.pageToGrid(rolelist);
	}
}
