package com.liqun.controller.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.liqun.service.BillServiceImpl;

/**
 * 开发票
 * @author wks
 *
 */
@Controller
@RequestMapping("/api")
public class DrawBillController {
	@Autowired
	private BillServiceImpl billServiceImpl;
	@RequestMapping(value="/FPKJService_",method = RequestMethod.POST)
	@ResponseBody
	public String drawBill(HttpServletRequest request,HttpServletResponse response)  {
		String xml=request.getParameter("parameter");
		try {
			billServiceImpl.addBill(xml);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
/*	*//**
	 *	同步立即开票
	 * @param request
	 * @param response
	 * @return
	 *//*
	@RequestMapping(value="/FPKJService",method = RequestMethod.POST)
	@ResponseBody
	public String immediatelyDrawBill(HttpServletRequest request,HttpServletResponse response)  {
		String immediatelyDrawBill="";
		try {
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(),Charset.forName("GBK")));
		StringBuilder sb = new StringBuilder();
		String line = null;
			while ((line = in.readLine()) != null) {
			sb.append(line);
			 }
			 immediatelyDrawBill = billServiceImpl.immediatelyDrawBill(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return immediatelyDrawBill;
		}
}*/
	
	
	/**
	 *	同步立即开票
	 *调用webService的方式
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/FPKJService",method = RequestMethod.POST)
	@ResponseBody
	public String immediatelyDrawBill(HttpServletRequest request,HttpServletResponse response)  {
		
		String immediatelyDrawBill="";
		try {
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(),Charset.forName("GBK")));
		StringBuilder sb = new StringBuilder();
		String line = null;
			while ((line = in.readLine()) != null) {
			sb.append(line);
			 }
			 try {
				immediatelyDrawBill = billServiceImpl.immediatelyDrawBill(sb.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return immediatelyDrawBill;
		}
}
	
	
	
