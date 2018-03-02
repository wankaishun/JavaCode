package com.liqun.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liqun.controller.advice.ApiControllerAdvisable;

@Controller
@RequestMapping("/console")
@ApiControllerAdvisable
public class TaxTuiHui {
private static final Logger logger = LoggerFactory.getLogger(TaxDownLoadController.class);
	
	// 获取用户角色页面
	@GetMapping("/fapiaotuihui")
	// @RequiresAuthentication
	// @RequiresPermissions("role:query")
	public String taxdownloadList(ModelMap model) {
		if (logger.isInfoEnabled()) {
			logger.info("TaxDownLoadController.taxdownloadList START");
			logger.info("TaxDownLoadController.taxdownloadList END");
		}
		return "console/taxBoard/management/taxtuihui";
	}
}
