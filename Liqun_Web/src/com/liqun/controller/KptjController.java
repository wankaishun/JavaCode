package com.liqun.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/console")
public class KptjController {

	private static final Logger logger = LoggerFactory.getLogger(InvoiceInquiryController.class);
	@GetMapping("/Kptj")
	public String sysInfoList(ModelMap model) {
		if (logger.isInfoEnabled()) {
			logger.info("KptjController.sysInfoList START");
			logger.info("KptjController.sysInfoList END");
		}
		return "console/query/Kptj";
	}
	
}
