package com.liqun.controller.api;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.liqun.controller.advice.ApiControllerAdvisable;
import com.liqun.dto.InvoiceDto;
import com.liqun.entity.MProuct;
import com.liqun.service.InvoiceInquiryService;
import com.liqun.service.MProuctService;
import com.liqun.util.DataGridPage;
import com.liqun.util.ExcelUtil;


@Controller
@RequestMapping("/api/MProuct")
@ApiControllerAdvisable
public class MProuctApiController {

	
	private static final Logger logger = LoggerFactory.getLogger(MProuctApiController.class);
	@Autowired
	private MProuctService mProuctService;
	@GetMapping("/MProuctList")
	@ResponseBody
 
	public Object MProuctList(@RequestParam int page, @RequestParam int rows,String fptqm,String fplsh,String gfmc,String gfsh,String starttime,String endtime,String djzt) {
		// 数据库分页 当前页需要减一
		/*InvoiceInquiry invoiceInquiry = new InvoiceInquiry();*/
		/*if(invoiceInquiry.getFplx()==0) {
			String s = Integer.toString(invoiceInquiry.getFplx());
			invoiceInquiry.set("专票");
		}*/
		System.out.println("0000000000000000");
		System.out.println(djzt);
		if (logger.isInfoEnabled())
			logger.info("MProuctController.MProuctList START");
		Page<MProuct> MProuctList = mProuctService.findAll(PageRequest.of(page - 1, rows),fptqm,fplsh,gfmc,gfsh,starttime,endtime,djzt);
		
		if (logger.isInfoEnabled())
			logger.info("MProuctController.MProuctList END");

		return DataGridPage.pageToGrid(MProuctList);
	}
	
	
	
	
	
}
