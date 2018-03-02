package com.liqun.dto.taxdownload.find;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="DATA")
public class DATA implements Serializable{
	@XmlElement(name="NSRSBH",required=true)
	private String nsrsbh;
	@XmlElement(name="KPJH",required=true)
	private String kpjh;
	@XmlElement(name="SBBH",required=true)
	private String sbbh;
	@XmlElement(name="FPMX",required=true)
	private FPMX fpmx;
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getKpjh() {
		return kpjh;
	}
	public void setKpjh(String kpjh) {
		this.kpjh = kpjh;
	}
	public String getSbbh() {
		return sbbh;
	}
	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
	}
	public FPMX getFpmx() {
		return fpmx;
	}
	public void setFpmx(FPMX fpmx) {
		this.fpmx = fpmx;
	}
	
}
