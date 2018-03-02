package com.liqun.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liqun.controller.advice.ApiControllerAdvisable;
import com.liqun.entity.SysUser;
import com.liqun.util.DataGridPage;



@Controller
@RequestMapping("/console")

public class InvoiceInquiryController {
	private static final Logger logger = LoggerFactory.getLogger(InvoiceInquiryController.class);
	//@Autowired
	//private InvoiceInquiryService InvoiceInquiryService;
	@GetMapping("/InvoiceInquiry")
	/*public String sysuserList(@RequestParam int page, @RequestParam int rows, String realName, String flag,
			String roleId) {
		// 数据库分页 当前页需要减一
	//	Page<SysUser> sysUserlist = sysUserService.findAll(PageRequest.of(page - 1, rows), realName, flag, roleId);

		return "query/InvoiceInquiry";
	}*/
	public String sysInfoList(ModelMap model) {
		if (logger.isInfoEnabled()) {
			logger.info("InvoiceInquiryController.sysInfoList START");
			logger.info("InvoiceInquiryController.sysInfoList END");
		}
		return "console/query/InvoiceInquiry";
	}
}
