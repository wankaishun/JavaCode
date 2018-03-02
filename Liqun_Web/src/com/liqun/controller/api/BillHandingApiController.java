package com.liqun.controller.api;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.javafaker.Faker;
import com.liqun.aop.DbLoggable;
import com.liqun.entity.IBilldel;
import com.liqun.entity.IBillmain;
import com.liqun.entity.SysLogInfo;
import com.liqun.entity.SysOption;
import com.liqun.service.BillHandingServiceImpl;
import com.liqun.service.SysLogInfoService;
import com.liqun.util.DataGridPage;
import com.liqun.util.ExcelUtil;
import com.liqun.util.StudentVO;

//页面跳转类
@Controller
@RequestMapping("/api")
public class BillHandingApiController {
	// log
		private static final Logger logger = LoggerFactory.getLogger(BillHandingApiController.class);
		@Autowired
		private BillHandingServiceImpl  billHandingServiceImpl;
		@GetMapping("/billlist")
		@ResponseBody
		public Object billlist(@RequestParam int page, @RequestParam int rows,String fptqm,String fplsh, String gfmc,
				String gfsh, String djzt,String starttime,String endtime) {
			if (logger.isInfoEnabled())
				logger.info("BillHandingApiController.billlist START");
			// 数据库分页 当前页需要减一
			Page<IBillmain> iBillmain=billHandingServiceImpl.getBillList(PageRequest.of(page - 1, rows), fptqm, fplsh, gfmc, gfsh, djzt, starttime, endtime);
			if (logger.isInfoEnabled())
				logger.info("BillHandingApiController.billlist END");
			return DataGridPage.pageToGrid(iBillmain);
		}
		@GetMapping("/billdel")
		@ResponseBody
		public Object billdel(@RequestParam int page, @RequestParam int rows,String fplsh) {
			if (logger.isInfoEnabled())
				logger.info("BillHandingApiController.billdel START");
			// 数据库分页 当前页需要减一
			Page<IBilldel> iBilldel=billHandingServiceImpl.getBilldel(PageRequest.of(page - 1, rows), fplsh);
			if (logger.isInfoEnabled())
				logger.info("BillHandingApiController.billdel END");
			return DataGridPage.pageToGrid(iBilldel);
		}
		/**
		 * 单据合并
		 * @param id 	合并单据id
		 * @param type 合并类型
		 * @return
		 */
		@GetMapping("/merge") 
		@ResponseBody
		public Object merge(@RequestParam(value="fplsh[]") String[] fplsh,int type) {
			if (logger.isInfoEnabled())
				logger.info("BillHandingApiController.merge START");
			 int result =billHandingServiceImpl.mergeBill(fplsh,type );
			if (logger.isInfoEnabled())
				logger.info("BillHandingApiController.merge END");
			return null;
		}
		 
		@GetMapping("/split") 
		@ResponseBody
		public Object split(@RequestParam(value="fplsh[]") String[] fplsh,int row,int type,@RequestParam(value="typeArray[]") String[] typeArray) {
			if (logger.isInfoEnabled())
				logger.info("BillHandingApiController.split START");
			if(billHandingServiceImpl.checkSplit(fplsh,row)) {
				int result =billHandingServiceImpl.splitBill(fplsh,type,typeArray,row);
			}else {
				return 0;//不能进行拆分
			}
			if (logger.isInfoEnabled())
				logger.info("BillHandingApiController.split END");
			return 1;
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
			     List<IBillmain> list = billHandingServiceImpl.getIBillmainForExport(fptqm, fplsh, gfmc, gfsh, djzt, starttime, endtime);  
			     ExcelUtil<IBillmain> util = new ExcelUtil<IBillmain>(IBillmain.class);// 创建工具类.  
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
