package com.liqun.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/console")
public class MProuctController {

	
	private static final Logger logger = LoggerFactory.getLogger(MProuctController.class);
	//@Autowired
	//private InvoiceInquiryService InvoiceInquiryService;
	@GetMapping("/MProuct")
	/*public String sysuserList(@RequestParam int page, @RequestParam int rows, String realName, String flag,
			String roleId) {
		// 数据库分页 当前页需要减一
	//	Page<SysUser> sysUserlist = sysUserService.findAll(PageRequest.of(page - 1, rows), realName, flag, roleId);

		return "query/InvoiceInquiry";
	}*/
	public String sysInfoList(ModelMap model) {
		if (logger.isInfoEnabled()) {
			logger.info("MProuctController.sysInfoList START");
			logger.info("MProuctController.sysInfoList END");
		}
		return "console/query/MProuct";
	}
	
}
