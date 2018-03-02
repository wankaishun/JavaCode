package com.liqun.controller.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liqun.service.BillSearchServiceImpl;

@Controller
@RequestMapping("/api")
public class SearchBillController {
	@Autowired
	private BillSearchServiceImpl billSearchServiceImpl;
	/**
	 * 发票查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/FPCXService",method = RequestMethod.POST)
	@ResponseBody
	public String searchBill(HttpServletRequest request,HttpServletResponse response) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(),Charset.forName("UTF-8")));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			String result=billSearchServiceImpl.searchBill(sb.toString());
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
