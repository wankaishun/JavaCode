package com.liqun.service;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Service;

import com.htxx.pojo.A9Param;
import com.htxx.services.A9Servcie;
import com.htxx.utils.TripleDESUtil;

@Service
public class TaxDownLoadService {
	public String findTax() throws UnsupportedEncodingException {
		String content = "";
		content = content + "<?xml version=\"1.0\" encoding=\"GBK\"?>";
		content = content + "<FPXT>";
		content = content + "<INPUT>";
		content = content + "</INPUT>";
		content = content + "</FPXT>";

		String xml = "";
		xml = xml + "<?xml version=\"1.0\" encoding=\"GBK\"?>";
		xml = xml + "<COM_INPUT>";
		xml = xml + "<ID>" + "0401" + "</ID>";
		xml = xml + "<DATA>" + new String(TripleDESUtil.encode(content.getBytes("GBK")), "GBK") + "</DATA>";
		xml = xml + "</COM_INPUT>";

		A9Param a9Param = new A9Param();
		a9Param.setBH("0");
		a9Param.setReqXml(xml);
		a9Param.setSH("370202999999066");
		String s = A9Servcie.requestA9(a9Param);
		return s;
	}
	public String downLoad() throws UnsupportedEncodingException {
		String content = "";
		content = content + "<?xml version=\"1.0\" encoding=\"GBK\"?>";
		content = content + "<FPXT>";
		content = content + "<INPUT>";
		content = content + "<FPZL>" + "0" + "</FPZL>";
		content = content + "<LBDM>" + "1234567890" + "</LBDM>";
		content = content + "<QSHM>" + "1234567890" + "</QSHM>";
		content = content + "<FPFS>" + "0" + "</FPFS>";
		content = content + "</INPUT>";
		content = content + "</FPXT>";

		String xml = "";
		xml = xml + "<?xml version=\"1.0\" encoding=\"GBK\"?>";
		xml = xml + "<COM_INPUT>";
		xml = xml + "<ID>" + "0205" + "</ID>";
		xml = xml + "<DATA>" + new String(TripleDESUtil.encode(content.getBytes("GBK")), "GBK") + "</DATA>";
		xml = xml + "</COM_INPUT>";

		A9Param a9Param = new A9Param();
		a9Param.setBH("0");
		a9Param.setReqXml(xml);
		a9Param.setSH("370202999999066");
		String ss = A9Servcie.requestA9(a9Param);
		return ss;

	}

}
