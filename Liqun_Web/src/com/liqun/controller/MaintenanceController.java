package com.liqun.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



//页面跳转类
@Controller
@RequestMapping("/console")
public class MaintenanceController {
	private static final Logger logger = LoggerFactory.getLogger(MaintenanceController.class);
	//获取用户角色页面
	@GetMapping("/maintenance")
	public String syscmList(ModelMap model) {
		if (logger.isInfoEnabled()) {
			logger.info("api.syscmList START");
			logger.info("api.syscmList END");
		}

		return "console/datamaintain/goodsmaintain/commodityMaintenance";
	}
}
