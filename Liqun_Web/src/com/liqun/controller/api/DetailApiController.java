package com.liqun.controller.api;

import org.apache.ibatis.annotations.Param;
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
import com.liqun.service.BillHandingServiceImpl;
import com.liqun.service.DetailService;
import com.liqun.service.InvoiceInquiryService;
import com.liqun.util.DataGridPage;


@Controller
@RequestMapping("/api/Detail")
@ApiControllerAdvisable
public class DetailApiController {

	private static final Logger logger = LoggerFactory.getLogger(DetailApiController.class);
	@Autowired
	DetailService detailService;
	@Autowired
	private BillHandingServiceImpl  billHandingServiceImpl;
	@GetMapping("/DetailList")
	@ResponseBody
 
	public Object DetailList(@RequestParam int page, @RequestParam int rows,String fptqm,String fplsh,String gfmc,String gfsh,String starttime,String endtime,String xfsh,String xfmc,Integer djzt) {
		// 数据库分页 当前页需要减一
		/*InvoiceInquiry invoiceInquiry = new InvoiceInquiry();*/
		/*if(invoiceInquiry.getFplx()==0) {
			String s = Integer.toString(invoiceInquiry.getFplx());
			invoiceInquiry.set("专票");
		}*/
		
		if (logger.isInfoEnabled())
			logger.info("DetailController.sysInfoList START");
		Page<InvoiceDto> DetailList = detailService.findDetail(PageRequest.of(page - 1, rows),fptqm,fplsh,gfmc,gfsh,starttime,endtime,xfsh,xfmc,djzt);
		
		if (logger.isInfoEnabled())
			logger.info("DetailController.sysInfoList END");

		return DataGridPage.pageToGrid(DetailList);
	}
	
	
	
	
}
