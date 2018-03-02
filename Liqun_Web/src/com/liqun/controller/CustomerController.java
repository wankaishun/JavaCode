package com.liqun.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liqun.controller.advice.ApiControllerAdvisable;
import com.liqun.entity.Customer;
import com.liqun.service.CustomerService;

@Controller
@RequestMapping("/console")
@ApiControllerAdvisable
public class CustomerController {
	// log
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	// 获取用户角色页面
	@GetMapping("/customer")
	// @RequiresAuthentication
	// @RequiresPermissions("role:query")
	public String customerList(ModelMap model) {
		if (logger.isInfoEnabled()) {
			logger.info("CustomerController.customerList START");
			logger.info("CustomerController.customerList END");
		}
		return "console/datamaintain/customer";
	}
}
