package com.liqun.dto.FpkjRequest;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="REQUEST_COMMON_FPKJ")
public class REQUEST_COMMON_FPKJ implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@XmlElement(name="COMMON_FPKJ_FPT",required=true)
	private COMMON_FPKJ_FPT commonFPKJFPT;
	
	@XmlElement(name="COMMON_FPKJ_XMXXS",required=true)
	private COMMON_FPKJ_XMXXS commonFPKJXMXXS;
	
	public COMMON_FPKJ_XMXXS getCommonFPKJXMXXS() {
		return commonFPKJXMXXS;
	}

	public void setCommonFPKJXMXXS(COMMON_FPKJ_XMXXS commonFPKJXMXXS) {
		this.commonFPKJXMXXS = commonFPKJXMXXS;
	}

	@XmlAttribute(name = "class")
	private String classs;

	public COMMON_FPKJ_FPT getCommonFPKJFPT() {
		return commonFPKJFPT;
	}

	public void setCommonFPKJFPT(COMMON_FPKJ_FPT commonFPKJFPT) {
		this.commonFPKJFPT = commonFPKJFPT;
	}

	
	public String getClasss() {
		return classs;
	}

	public void setClasss(String classs) {
		this.classs = classs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "REQUEST_COMMON_FPKJ [commonFPKJFPT=" + commonFPKJFPT + ", commonFPKJXMXXS=" + commonFPKJXMXXS
				+ ", classs=" + classs + "]";
	}

	
	
}
