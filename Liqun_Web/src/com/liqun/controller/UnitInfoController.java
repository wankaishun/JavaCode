package com.liqun.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/console")
public class UnitInfoController {
	private static final Logger logger = LoggerFactory.getLogger(UnitInfoController.class);
	
	@GetMapping("/unitinfo")
	public String unitInfo(ModelMap model) {
		if (logger.isInfoEnabled()) {
			logger.info("UnitInfoController.unitInfo START");
			logger.info("UnitInfoController.unitInfo END");
		}
		return "console/sys/unitinfo";
	}
}
