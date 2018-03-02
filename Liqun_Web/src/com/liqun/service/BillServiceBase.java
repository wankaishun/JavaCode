package com.liqun.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.htxx.utils.TripleDESUtil;
import com.liqun.dto.CommonNode;
import com.liqun.dto.FPKJInput;
import com.liqun.dto.FPXM;
import com.liqun.dto.FPXTInput;
import com.liqun.dto.Input;
import com.liqun.entity.IBilldel;
import com.liqun.entity.IBillmain;
import com.liqun.util.JaxbUtil;

public class BillServiceBase {
	public IBillmain getWholeIBillmain(String xml) throws UnsupportedEncodingException {
		FPXTInput FPXTInput=JaxbUtil.converyToJavaBean(xml, FPXTInput.class);
		byte[] data=TripleDESUtil.decode(FPXTInput.getData().replace(' ','+').getBytes());
		String a=new String(data,"GBK");
		FPKJInput FPKJInput=JaxbUtil.converyToJavaBean(a, FPKJInput.class);
		/*System.out.println(JaxbUtil.convertToXml(FPKJInput));*/
		Input input=FPKJInput.getInput();
		List<FPXM> FPXMXX=input.getFPXMXX();
		IBillmain iBillmain=getIBillMain(input);
		if(FPXMXX.size()>8) {//清单大于8行
			iBillmain.setQdbz(1);//有清单
		}else {
			iBillmain.setQdbz(0);//无清单
		}
		return iBillmain;
	}
	public List<IBilldel> getWholeIBilldelList (String xml) throws UnsupportedEncodingException {
		FPXTInput FPXTInput=JaxbUtil.converyToJavaBean(xml, FPXTInput.class);
		byte[] data=TripleDESUtil.decode(FPXTInput.getData().replace(' ','+').getBytes());
		String a=new String(data,"GBK");
		FPKJInput FPKJInput=JaxbUtil.converyToJavaBean(a, FPKJInput.class);
		/*System.out.println(JaxbUtil.convertToXml(FPKJInput));*/
		Input input=FPKJInput.getInput();
		List<FPXM> FPXMXX=input.getFPXMXX();
		List<IBilldel> iBilldelList=new ArrayList<IBilldel>();
		for (int i=0;i<FPXMXX.size();i++) {
			FPXM fpxm3=FPXMXX.get(i);
			FPXM fpxm4=null;
			IBilldel iBilldel3=getFPXM(fpxm3,input);
			String fphxz=fpxm3.getFPHXZ();//获得发票行性质
			if("3".equals(fphxz)) {
				fpxm4=FPXMXX.get(i+1);
				BigDecimal zkje=new BigDecimal(fpxm4.getXMJE());
				BigDecimal zkhsje=new BigDecimal(fpxm4.getHSJE());
				iBilldel3.setZkje(zkje);//折扣行金额对应折扣金额
				iBilldel3.setZkhsje(zkhsje);//折扣行含税金额对应折扣后含税金额
				iBilldel3.setZkse(zkhsje.subtract(zkje));
				BigDecimal hsje=new BigDecimal(fpxm3.getHSJE());
				BigDecimal xmje=new BigDecimal(fpxm3.getXMJE());
				BigDecimal se=new BigDecimal(fpxm3.getSE());
				iBilldel3.setZkhhsje(hsje.add(zkhsje));
				iBilldel3.setZkhje(xmje.add(zkje));
				iBilldel3.setZkhse(se.add(zkhsje.subtract(zkje)));
				FPXMXX.remove(i+1);
			}
			iBilldelList.add(iBilldel3);
		}
		for(int i=0;i<iBilldelList.size();i++) {
			IBilldel iBilldel =iBilldelList.get(i);
			iBilldel.setPfhh(i+1);//设置行号
		}
		return iBilldelList;
	}
	/**
	 * 异步开票
	 * @return
	 */
	public int drawBll(String xml) {
		return 1;
	}
	protected IBilldel getFPXM(FPXM fpxm,Input input) {
		IBilldel iBilldel=new IBilldel();
		iBilldel.setFplsh(input.getFPLSH());
		iBilldel.setFptqm(input.getFPTQM());
		iBilldel.setGgxh(fpxm.getGGXH());
		BigDecimal hsdj=null;
		BigDecimal hsje=null;
		if("".equals(fpxm.getHSDJ())) {
			hsdj=new BigDecimal("0");
		}else {
			hsdj=new BigDecimal(fpxm.getHSDJ());//含税单价
		}
		iBilldel.setHsdj(hsdj);//含税单价
		if("".equals(fpxm.getHSJE())) {
			hsje=new BigDecimal("0");
		}else {
			hsje=new BigDecimal(fpxm.getHSJE());//含税金额
		}
		iBilldel.setHsje(hsje);//含税金额
		iBilldel.setJldw(fpxm.getJLDW());
		//iBilldel.setLslbs("0");//零税率标识
		//iBilldel.setPfhh(65465464);//行号
		iBilldel.setQybh(input.getQYBH());
		iBilldel.setSe(new BigDecimal(fpxm.getSE()));
		//iBilldel.setSfyhzc(1);//是否优惠政策
		iBilldel.setSl(new BigDecimal(fpxm.getSL()));//数量
		iBilldel.setSpbh("1212");//企业商品自编码
		iBilldel.setSpbm(fpxm.getSPBM());
		BigDecimal xmdj=null;
		if("".equals(fpxm.getXMDJ())) {
			xmdj=new BigDecimal("0");
			iBilldel.setXmdj(xmdj);
		}else {
			xmdj=new BigDecimal(fpxm.getXMDJ());
			iBilldel.setXmdj(xmdj);
		}
		BigDecimal xmje=null;
		if("".equals(fpxm.getXMJE())) {
			xmje=new BigDecimal("0");
			iBilldel.setXmje(xmje);
		}else {
			xmje=new BigDecimal(fpxm.getXMJE());
			iBilldel.setXmje(xmje);
		}
		iBilldel.setXmmc(fpxm.getXMMC());
		if("".equals(fpxm.getXMSL())) {
			BigDecimal xmsl=new BigDecimal("0");
			iBilldel.setXmsl(xmsl);
		}else {
			BigDecimal xmsl=new BigDecimal(fpxm.getXMSL());
			iBilldel.setXmsl(xmsl);
		}
		
		iBilldel.setYhzcnr("优惠政策内容");//优惠政策内容
		iBilldel.setZkhhsje(hsje);//折扣后含税金额
		iBilldel.setZkhje(xmje);//折扣后金额
		iBilldel.setZkhse(new BigDecimal(fpxm.getSE()));//折扣后税额
		iBilldel.setZkhsje(new BigDecimal("0"));//折扣含税金额
		iBilldel.setZkse(new BigDecimal("0"));//折扣税额
		iBilldel.setZkje(new BigDecimal("0"));
		return iBilldel;
	}
	protected IBillmain getIBillMain(Input input) {
		IBillmain iBillmain=new IBillmain();
		//iBillmain.setBmbbh();
		iBillmain.setBz(input.getBZ());
		iBillmain.setCzsj(new Date());
		iBillmain.setDjlx(Integer.parseInt(input.getKPMS()));// 开票模式即单据类型
		iBillmain.setDjly(Integer.parseInt(input.getKPLY()));//开票来源即单据来源
		iBillmain.setDjzt(0);//未开发票
		List<CommonNode> common=input.getCOMMON_NODES();
		for (CommonNode commonNode : common) {
			if(commonNode.getName().equals("SJ")) {
				iBillmain.setSj(commonNode.getValue());
			}
			if(commonNode.getName().equals("EMAIL")) {
				iBillmain.setEmail(commonNode.getValue());
			}
		}
		iBillmain.setFhr(input.getFHR());
		iBillmain.setFjh("1");
		iBillmain.setFplsh(input.getFPLSH());
		iBillmain.setFptqm(input.getFPTQM());
		iBillmain.setFpzl(Integer.parseInt(input.getFPZL()));
		iBillmain.setGfdzdh(input.getGFDZDH());
		iBillmain.setGfmc(input.getGFMC());
		iBillmain.setGfsh(input.getGFSH());
		iBillmain.setGfyhzh(input.getGFYHZH());
		BigDecimal hjje=new BigDecimal(input.getHJJE());//合计金额
		BigDecimal jshj=new BigDecimal(input.getJSHJ());//合计金额
		iBillmain.setHjje(hjje);//合计金额
		iBillmain.setHjse(jshj.subtract(hjje));//合计税额
		iBillmain.setHptzdbh(input.getHPTZDBH());
		//iBillmain.setJfzt(1212);允许空值
		iBillmain.setJshj(jshj);//加税合计
		iBillmain.setKplx(Integer.parseInt(input.getKPLX()));
		iBillmain.setKpr(input.getKPR());
		iBillmain.setKpzdh(input.getKPZDH());
		iBillmain.setNsrsbh(input.getXFSH());//税号存放销方税号
		//iBillmain.setQdbz(0);//清单标志
		iBillmain.setQybh(input.getQYBH());
		iBillmain.setSkr(input.getSKR());
		iBillmain.setXfdzdh(input.getXFDZDH());
		iBillmain.setXfmc(input.getXFMC());
		iBillmain.setXfsh(input.getXFSH());
		iBillmain.setXfyhzh(input.getXFYHZH());
		iBillmain.setYfpdm(input.getYFPDM());
		iBillmain.setYfphm(input.getYFPHM());
		return iBillmain;
	}

}
