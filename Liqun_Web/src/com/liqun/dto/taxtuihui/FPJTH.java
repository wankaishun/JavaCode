package com.liqun.dto.taxtuihui;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="FPJTH")
public class FPJTH implements Serializable{
	@XmlElement(name="FPZL",required=true)
	private String fpzl;
	@XmlElement(name="LBDM",required=true)
	private String lbdm;
	@XmlElement(name="QSHM",required=true)
	private String qshm;
	@XmlElement(name="FPFS",required=true)
	private String fpfs;
	public String getFpzl() {
		return fpzl;
	}
	public void setFpzl(String fpzl) {
		this.fpzl = fpzl;
	}
	public String getLbdm() {
		return lbdm;
	}
	public void setLbdm(String lbdm) {
		this.lbdm = lbdm;
	}
	public String getQshm() {
		return qshm;
	}
	public void setQshm(String qshm) {
		this.qshm = qshm;
	}
	public String getFpfs() {
		return fpfs;
	}
	public void setFpfs(String fpfs) {
		this.fpfs = fpfs;
	}
	
}
