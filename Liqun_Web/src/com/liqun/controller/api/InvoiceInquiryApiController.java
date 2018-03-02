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

import com.liqun.aop.DbLoggable;
import com.liqun.controller.advice.ApiControllerAdvisable;
import com.liqun.dto.InvoiceDto;
import com.liqun.entity.IBillmain;
import com.liqun.service.BillHandingServiceImpl;
import com.liqun.service.InvoiceInquiryService;
import com.liqun.service.SysLogService;
import com.liqun.util.DataGridPage;
import com.liqun.util.ExcelUtil;

@Controller
@RequestMapping("/api/InvoiceInquiry")

public class InvoiceInquiryApiController {
	
	private static final Logger logger = LoggerFactory.getLogger(InvoiceInquiryApiController.class);
	@Autowired
	private InvoiceInquiryService invoiceInquiryService;
	/*@Autowired
	private BillHandingServiceImpl  billHandingServiceImpl;*/
	
	@GetMapping("/InvoiceInquiryList")
	@ResponseBody
 
	public Object InvoiceInquiryList(@RequestParam int page, @RequestParam int rows,String fptqm,String fplsh,String gfmc,String gfsh,String starttime,String endtime,String djzt) {
		// 数据库分页 当前页需要减一
		/*InvoiceInquiry invoiceInquiry = new InvoiceInquiry();*/
		/*if(invoiceInquiry.getFplx()==0) {
			String s = Integer.toString(invoiceInquiry.getFplx());
			invoiceInquiry.set("专票");
		}*/
		System.out.println(djzt);
		if (logger.isInfoEnabled())
			logger.info("InvoiceInquiryController.InvoiceInquiryList START");
		Page<InvoiceDto> InvoiceInquiryList = invoiceInquiryService.findAll(PageRequest.of(page - 1, rows),fptqm,fplsh,gfmc,gfsh,starttime,endtime,djzt);
		
		if (logger.isInfoEnabled())
			logger.info("InvoiceInquiryController.InvoiceInquiryList END");

		return DataGridPage.pageToGrid(InvoiceInquiryList);
	}
	
	
	@GetMapping("/exportBill") 
	public Object exportBill(HttpServletResponse response,HttpServletRequest request) {
		 
		String fptqm=request.getParameter("fptqm");
		String fplsh=request.getParameter("fplsh");
		String gfmc=request.getParameter("gfmc");
		String gfsh=request.getParameter("gfsh");
		String starttime=request.getParameter("starttime");
		String endtime=request.getParameter("endtime");
		String djzt=request.getParameter("djzt");
		response.setContentType("application/binary;charset=UTF-8");
		try{
		     ServletOutputStream out=response.getOutputStream();
		     String fileName=new String((URLEncoder.encode("单据信息", "UTF-8")+ new SimpleDateFormat("yyyy-MM-dd").format(new Date())).getBytes(),"UTF-8");
		     response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
		     List<InvoiceDto> list = invoiceInquiryService.getIBillmainForExport(fptqm, fplsh, gfmc, gfsh, starttime, endtime,djzt);  
		     ExcelUtil<InvoiceDto> util = new ExcelUtil<InvoiceDto>(InvoiceDto.class);// 创建工具类.  
		        util.exportExcel(list, "单据信息", 65536, out);// 导出  
		        System.out.println(out);
		        System.out.println("----执行完毕----------");  
		 } catch(Exception e){
		     e.printStackTrace();
		     return "导出信息失败";
		 }
		return null;
	}
	
}
