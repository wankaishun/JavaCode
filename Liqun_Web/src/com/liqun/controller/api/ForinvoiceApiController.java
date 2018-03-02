package com.liqun.controller.api;

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

import com.liqun.dto.InvoiceDto;
import com.liqun.entity.IInv;
import com.liqun.service.ForInvoiceServiceImpl;
import com.liqun.util.DataGridPage;

@Controller
@RequestMapping("/api/forInv")
public class ForinvoiceApiController {
	private static final Logger logger = LoggerFactory.getLogger(ForinvoiceApiController.class);

	@Autowired
	private ForInvoiceServiceImpl forInvoiceServiceImpl;
	
	/**
	 * 发票换开
	 */
	@GetMapping("/forInvoiceList")
	@ResponseBody
 
	public Object getForInvoiceList(@RequestParam int page, @RequestParam int rows,String fptqm,String fplsh,String gfmc,String gfsh,String starttime,String endtime,String djzt) {
		
		System.out.println(djzt);
		if (logger.isInfoEnabled())
			logger.info("ForinvoiceApiController.forInvoiceList START");
		Page<InvoiceDto> forInvoiceList = forInvoiceServiceImpl.findAll(PageRequest.of(page - 1, rows),fptqm,fplsh,gfmc,gfsh,starttime,endtime,djzt);
		
		if (logger.isInfoEnabled())
			logger.info("ForinvoiceApiController.forInvoiceList END");

		return DataGridPage.pageToGrid(forInvoiceList);
	}
	
}
