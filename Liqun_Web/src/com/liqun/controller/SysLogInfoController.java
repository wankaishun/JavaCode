package com.liqun.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 日志查询操作类
 * 
 * @author LINN
 *
 */
@Controller
@RequestMapping("/console")
public class SysLogInfoController {
	// log
	private static final Logger logger = LoggerFactory.getLogger(SysLogInfoController.class);

	// 获取用户角色页面
	@GetMapping("/sys")
	// @RequiresAuthentication
	// @RequiresPermissions("role:query")
	public String sysInfoList(ModelMap model) {
		if (logger.isInfoEnabled()) {
			logger.info("SysLogInfoController.sysInfoList START");
			logger.info("SysLogInfoController.sysInfoList END");
		}
		return "console/sys/sysloginfo";
	}
}
