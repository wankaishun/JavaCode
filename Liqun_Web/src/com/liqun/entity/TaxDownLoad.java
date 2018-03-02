package com.liqun.entity;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="TaxDownLoad")
public class TaxDownLoad implements Serializable{
	
	@XmlElement(name="ICCardNo",required=true)
	private String ICCardNo;
	@XmlElement(name="MachineNumber",required=true)
	private String MachineNumber;
	@XmlElement(name="FPZL",required=true)
	private String FPZL;
	@XmlElement(name="FPTQM",required=true)
	private String FPTQM;
	@XmlElement(name="QSHM",required=true)
	private String QSHM;
	@XmlElement(name="FPFS",required=true)
	private String FPFS;
	@XmlElement(name="FPZT",required=true)
	private String FPZT;
	@XmlElement(name="GMRQ",required=true)
	private String GMRQ;
	public String getICCardNo() {
		return ICCardNo;
	}
	public void setICCardNo(String iCCardNo) {
		ICCardNo = iCCardNo;
	}
	public String getMachineNumber() {
		return MachineNumber;
	}
	public void setMachineNumber(String machineNumber) {
		MachineNumber = machineNumber;
	}
	public String getFPZL() {
		return FPZL;
	}
	public void setFPZL(String fPZL) {
		FPZL = fPZL;
	}
	public String getFPTQM() {
		return FPTQM;
	}
	public void setFPTQM(String fPTQM) {
		FPTQM = fPTQM;
	}
	public String getQSHM() {
		return QSHM;
	}
	public void setQSHM(String qSHM) {
		QSHM = qSHM;
	}
	public String getFPFS() {
		return FPFS;
	}
	public void setFPFS(String fPFS) {
		FPFS = fPFS;
	}
	public String getFPZT() {
		return FPZT;
	}
	public void setFPZT(String fPZT) {
		FPZT = fPZT;
	}
	public String getGMRQ() {
		return GMRQ;
	}
	public void setGMRQ(String gMRQ) {
		GMRQ = gMRQ;
	}

}
