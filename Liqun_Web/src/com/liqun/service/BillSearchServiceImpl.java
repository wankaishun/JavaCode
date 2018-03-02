package com.liqun.service;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htxx.pojo.FPQZReturnObj;
import com.htxx.pojo.i_billdel;
import com.htxx.pojo.i_billmain;
import com.htxx.pojo.i_inv;
import com.htxx.services.FPQZ;
import com.htxx.utils.TripleDESUtil;
import com.liqun.dao.IBilldelMapper;
import com.liqun.dao.IBillmainMapper;
import com.liqun.dao.IInvMapper;
import com.liqun.dto.lqinput.searchbill.Input;
import com.liqun.dto.lqoutput.searchbill.FpxtOutput;
import com.liqun.util.JaxbUtil;

@Service
@Transactional
public class BillSearchServiceImpl {
	@Autowired
	private IBilldelMapper iBilldelMapper;
	@Autowired
	private IBillmainMapper iBillmainMapper;
	@Autowired
	private IInvMapper iInvMapper;
	@Autowired
	private FPQZ fpqz;
	/**
	 * 用户发票查询 返回pdf
	 * @param xml
	 * @return
	 */
	public String searchBill(String xml) {
		try {
			Input input= JaxbUtil.converyToJavaBean(xml, Input.class);
			String fptqm=input.getTqm();
			String fplsh=input.getLsh();
			i_billmain billmain =iBillmainMapper.getBillmainBy(input);
			List<i_billdel> billdel =iBilldelMapper.getBilldelBy(input);
			i_inv inv=iInvMapper.getInvBy(input);
			FPQZReturnObj fpqz2 = fpqz.FPQZ(inv, "SJ", billmain,billdel);
			 FpxtOutput fpxtoutput=new FpxtOutput();
			fpxtoutput.setId("10003");//业务代码
			fpxtoutput.setMess(fpqz2.getReturnMessage());
			fpxtoutput.setCode(fpqz2.getReturnCode());
			fpxtoutput.setData(new String(TripleDESUtil.encode(fpqz2.getPDF_URL().getBytes("GBK")),"GBK"));
			String result=JaxbUtil.convertToXml(fpxtoutput);
			String message  = new String(TripleDESUtil.decode(fpqz2.getReturnMessage().getBytes("UTF-8")),"UTF-8");
			System.out.println(message);
			return result;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
