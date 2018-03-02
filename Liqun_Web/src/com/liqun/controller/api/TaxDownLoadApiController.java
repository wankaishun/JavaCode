package com.liqun.controller.api;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.htxx.pojo.A9Param;
import com.htxx.services.A9Servcie;
import com.htxx.utils.TripleDESUtil;
import com.liqun.controller.advice.ApiControllerAdvisable;
import com.liqun.dto.taxdownload.OUTPUT;
import com.liqun.dto.taxdownload.find.OUTPUT1;
import com.liqun.dto.taxdownload.find.XIAOKK;
import com.liqun.service.TaxDownLoadService;
import com.liqun.util.Base64Util;
import com.liqun.util.JaxbUtil;

@Controller
@RequestMapping("/api/taxBoard")
@ApiControllerAdvisable
public class TaxDownLoadApiController {
	@Autowired
	private TaxDownLoadService taxDownLoadService;
    
	@GetMapping("/findTax")
	@ResponseBody
	public Object findTax(ModelMap model) throws UnsupportedEncodingException {
		String s=taxDownLoadService.findTax();
		System.out.println(s);
        Document document=null;
		  try {
			document = DocumentHelper.parseText(s);
		  } catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
        Element root=document.getRootElement();
        Element DATA=root.element("DATA");
        //ystem.out.println(DATA.getData());
        String data=DATA.getData().toString();  
        String datebuf=Base64Util.getFromBase64(data, "GBK");
		  try {
			document = DocumentHelper.parseText(datebuf);
		  } catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
        Element root2=document.getRootElement();
        Element OUTPUT=root2.element("OUTPUT");
        Element DATA2=OUTPUT.element("DATABUF");
        String databuf2=DATA2.getText().toString();
        String ss=Base64Util.getFromBase64(databuf2, "GBK");
        OUTPUT1 tdl=JaxbUtil.converyToJavaBean(ss,OUTPUT1.class);
        XIAOKK xkk= new XIAOKK();
        xkk.setNsrsbh(tdl.getData().getNsrsbh());
        xkk.setKpjh(tdl.getData().getKpjh());
        xkk.setFpzl(tdl.getData().getFpmx().getFpzl());
        xkk.setLbdm(tdl.getData().getFpmx().getLbdm());
        xkk.setQshm(tdl.getData().getFpmx().getQshm());
        xkk.setLbdm(tdl.getData().getFpmx().getLbdm());
        xkk.setGmrq(tdl.getData().getFpmx().getGmrq());
        xkk.setFpfs(tdl.getData().getFpmx().getFpfs());
        xkk.setFpzt("未下载");
        List<XIAOKK> listoutput=new ArrayList<XIAOKK>();
        listoutput.add(xkk);
        model.put("tdl", tdl);
        return model;

	}

	@GetMapping("/downLoad")
	@ResponseBody
	public void downLoad(ModelMap model) throws UnsupportedEncodingException {
		String s=taxDownLoadService.downLoad();
		System.out.println(s);
        Document document=null;
		  try {
			document = DocumentHelper.parseText(s);
		  } catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
        Element root=document.getRootElement();
        Element DATA=root.element("DATA");
        //ystem.out.println(DATA.getData());
        String data=DATA.getData().toString();
        String fpjxz=Base64Util.getFromBase64(data, "GBK");
		
	}
}
