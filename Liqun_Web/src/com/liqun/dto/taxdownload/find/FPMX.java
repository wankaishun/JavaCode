package com.liqun.dto.taxdownload.find;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="FPMX")
public class FPMX implements Serializable{
	@XmlElement(name="FPZL",required=true)
	private String fpzl;
	@XmlElement(name="LBDM",required=true)
	private String lbdm;
	@XmlElement(name="QSHM",required=true)
	private String qshm;
	@XmlElement(name="FPFS",required=true)
	private String fpfs;
	@XmlElement(name="GMRQ",required=true)
	private String gmrq;
	private String fpzt;
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
	public String getGmrq() {
		return gmrq;
	}
	public void setGmrq(String gmrq) {
		this.gmrq = gmrq;
	}
	public String getFpzt() {
		return fpzt;
	}
	public void setFpzt(String fpzt) {
		this.fpzt = fpzt;
	}
	
	
}
