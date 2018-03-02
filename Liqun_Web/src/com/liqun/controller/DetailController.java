package com.liqun.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/console")
public class DetailController {
	
	private static final Logger logger = LoggerFactory.getLogger(DetailController.class);

	// 获取用户角色页面
	@GetMapping("/Detail")
	// @RequiresAuthentication
	// @RequiresPermissions("role:query")
	public String sysInfoList(ModelMap model) {
		if (logger.isInfoEnabled()) {
			logger.info("DetailController.sysInfoList START");
			logger.info("DetailController.sysInfoList END");
		}
		return "console/query/Detail";
	}
}
