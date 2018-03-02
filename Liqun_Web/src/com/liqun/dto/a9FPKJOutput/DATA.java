package com.liqun.dto.a9FPKJOutput;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="DATA")
public class DATA implements Serializable{
	
	@XmlElement(name="KPZDH",required=true)
	private String KPZDH;
	@XmlElement(name="FPLSH",required=true)
	private String FPLSH;
	@XmlElement(name="FPTQM",required=true)
	private String FPTQM;
	@XmlElement(name="KPMS",required=true)
	private String KPMS;
	@XmlElement(name="FPDM",required=true)
	private String FPDM;
	@XmlElement(name="FPHM",required=true)
	private String FPHM;
	@XmlElement(name="KPSJ",required=true)
	private String KPSJ;
	@XmlElement(name="JSHJ",required=true)
	private String JSHJ;
	@XmlElement(name="HJJE",required=true)
	private String HJJE;
	@XmlElement(name="HJSE",required=true)
	private String HJSE;
	@XmlElement(name="BZ",required=true)
	private String BZ;
	public String getKPZDH() {
		return KPZDH;
	}
	public void setKPZDH(String kPZDH) {
		KPZDH = kPZDH;
	}
	public String getFPLSH() {
		return FPLSH;
	}
	public void setFPLSH(String fPLSH) {
		FPLSH = fPLSH;
	}
	public String getFPTQM() {
		return FPTQM;
	}
	public void setFPTQM(String fPTQM) {
		FPTQM = fPTQM;
	}
	public String getKPMS() {
		return KPMS;
	}
	public void setKPMS(String kPMS) {
		KPMS = kPMS;
	}
	public String getFPDM() {
		return FPDM;
	}
	public void setFPDM(String fPDM) {
		FPDM = fPDM;
	}
	public String getFPHM() {
		return FPHM;
	}
	public void setFPHM(String fPHM) {
		FPHM = fPHM;
	}
	public String getKPSJ() {
		return KPSJ;
	}
	public void setKPSJ(String kPSJ) {
		KPSJ = kPSJ;
	}
	public String getJSHJ() {
		return JSHJ;
	}
	public void setJSHJ(String jSHJ) {
		JSHJ = jSHJ;
	}
	public String getHJJE() {
		return HJJE;
	}
	public void setHJJE(String hJJE) {
		HJJE = hJJE;
	}
	public String getHJSE() {
		return HJSE;
	}
	public void setHJSE(String hJSE) {
		HJSE = hJSE;
	}
	public String getBZ() {
		return BZ;
	}
	public void setBZ(String bZ) {
		BZ = bZ;
	}
	
	
}
