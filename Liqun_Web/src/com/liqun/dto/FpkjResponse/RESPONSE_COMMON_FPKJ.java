package com.liqun.dto.FpkjResponse;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="RESPONSE_COMMON_FPKJ")
@XmlAccessorType(XmlAccessType.FIELD)
public class RESPONSE_COMMON_FPKJ implements Serializable{
	@XmlAttribute(name="class")
	private String classes;
	@XmlElement(name="FPQQLSH",required=true)
	private String FPQQLSH;//发票请求流水号
	@XmlElement(name="JQBH",required=true)
	private String JQBH;//税控设备编号</JQBH>
	@XmlElement(name="FP_DM",required=true)
	private String FP_DM;//发票代码</FP_DM>
	@XmlElement(name="FP_HM",required=true)
	private String FP_HM;//发票号码</FP_HM>
	@XmlElement(name="KPRQ",required=true)
	private String KPRQ;//开票日期</KPRQ>
	@XmlElement(name="FP_MW",required=true)
	private String FP_MW;//发票密文</FP_MW>
	@XmlElement(name="JYM",required=true)
	private String JYM;//校验码</JYM>
	@XmlElement(name="EWM",required=true)
	private String EWM;//二维码</EWM>
	@XmlElement(name="RETURNCODE",required=true)
	private String RETURNCODE;//返回代码</RETURNCODE>
	@XmlElement(name="RETURNMSG",required=true)
	private String RETURNMSG;//返回信息</RETURNMSG>
	
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getFPQQLSH() {
		return FPQQLSH;
	}
	public void setFPQQLSH(String fPQQLSH) {
		FPQQLSH = fPQQLSH;
	}
	public String getJQBH() {
		return JQBH;
	}
	public void setJQBH(String jQBH) {
		JQBH = jQBH;
	}
	public String getFP_DM() {
		return FP_DM;
	}
	public void setFP_DM(String fP_DM) {
		FP_DM = fP_DM;
	}
	public String getFP_HM() {
		return FP_HM;
	}
	public void setFP_HM(String fP_HM) {
		FP_HM = fP_HM;
	}
	public String getKPRQ() {
		return KPRQ;
	}
	public void setKPRQ(String kPRQ) {
		KPRQ = kPRQ;
	}
	public String getFP_MW() {
		return FP_MW;
	}
	public void setFP_MW(String fP_MW) {
		FP_MW = fP_MW;
	}
	public String getJYM() {
		return JYM;
	}
	public void setJYM(String jYM) {
		JYM = jYM;
	}
	public String getEWM() {
		return EWM;
	}
	public void setEWM(String eWM) {
		EWM = eWM;
	}
	public String getRETURNCODE() {
		return RETURNCODE;
	}
	public void setRETURNCODE(String rETURNCODE) {
		RETURNCODE = rETURNCODE;
	}
	public String getRETURNMSG() {
		return RETURNMSG;
	}
	public void setRETURNMSG(String rETURNMSG) {
		RETURNMSG = rETURNMSG;
	}
	@Override
	public String toString() {
		return "RESPONSE_COMMON_FPKJ [classes=" + classes + ", FPQQLSH=" + FPQQLSH + ", JQBH=" + JQBH + ", FP_DM="
				+ FP_DM + ", FP_HM=" + FP_HM + ", KPRQ=" + KPRQ + ", FP_MW=" + FP_MW + ", JYM=" + JYM + ", EWM=" + EWM
				+ ", RETURNCODE=" + RETURNCODE + ", RETURNMSG=" + RETURNMSG + "]";
	}

}
