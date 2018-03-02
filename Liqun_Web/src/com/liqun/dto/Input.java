package com.liqun.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 利群传过来
 * @author wks
 *
 */
@XmlRootElement(name="INPUT")
@XmlAccessorType(XmlAccessType.FIELD)
public class Input {
	@XmlElement(name="QYBH",required=true)
	private String QYBH;//企业ID
	@XmlElement(name="KPZDH",required=true)
	private String KPZDH;//开票终端号
	@XmlElement(name="FPLSH",required=true)
	private String FPLSH;//发票流水号
	@XmlElement(name="FPTQM",required=true)
	private String FPTQM;//发票提取码
	@XmlElement(name="FPZL",required=true)
	private String FPZL;//发票种类
	@XmlElement(name="KPLX",required=true)
	private String KPLX;//开票类型
	@XmlElement(name="KPMS",required=true)
	private String KPMS;//开票模式
	@XmlElement(name="KPLY",required=true)
	private String KPLY;//开票来源
	@XmlElement(name="XFSH",required=true)
	private String XFSH;//销方税号
	@XmlElement(name="XFMC",required=true)
	private String XFMC;//销方名称
	@XmlElement(name="XFDZDH",required=true)
	private String XFDZDH;//销方地址电话
	@XmlElement(name="XFYHZH",required=true)
	private String XFYHZH;//销方银行账号
	@XmlElement(name="GFSH",required=true)
	private String GFSH;//购方税号
	@XmlElement(name="GFMC",required=true)
	private String GFMC;//购方名称
	@XmlElement(name="GFDZDH",required=true)
	private String GFDZDH;//购方地址电话
	@XmlElement(name="GFYHZH",required=true)
	private String GFYHZH;//购方银行账号
	@XmlElement(name="KPR",required=true)
	private String KPR;//开票人
	@XmlElement(name="SKR",required=true)
	private String SKR;//收款人
	@XmlElement(name="FHR",required=true)
	private String FHR;//复核人
	@XmlElement(name="YFPDM",required=true)
	private String YFPDM;//原发票代码
	@XmlElement(name="YFPHM",required=true)
	private String YFPHM;//原发票号码
	@XmlElement(name="HPTZDBH",required=true)
	private String HPTZDBH;//红票通知单编号
	@XmlElement(name="JSHJ",required=true)
	private String JSHJ;//价税合计
	@XmlElement(name="HJJE",required=true)
	private String HJJE;//合计金额(不含税)
	@XmlElement(name="BZ",required=true)
	private String BZ;//备注
	@XmlElementWrapper(name = "COMMON_NODES")
	@XmlElement(name="COMMON_NODE",required=true)
	private List<CommonNode> COMMON_NODES;//<COMMON_NODES size="3"><!--推送方式信息节点-->
	@XmlElementWrapper(name = "FPXMXX")
	@XmlElement(name="FPXM",required=true)
	private List<FPXM> FPXMXX;
	public String getQYBH() {
		return QYBH;
	}
	public void setQYBH(String qYBH) {
		QYBH = qYBH;
	}
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
	public String getFPZL() {
		return FPZL;
	}
	public void setFPZL(String fPZL) {
		FPZL = fPZL;
	}
	public String getKPLX() {
		return KPLX;
	}
	public void setKPLX(String kPLX) {
		KPLX = kPLX;
	}
	public String getKPMS() {
		return KPMS;
	}
	public void setKPMS(String kPMS) {
		KPMS = kPMS;
	}
	public String getKPLY() {
		return KPLY;
	}
	public void setKPLY(String kPLY) {
		KPLY = kPLY;
	}
	public String getXFSH() {
		return XFSH;
	}
	public void setXFSH(String xFSH) {
		XFSH = xFSH;
	}
	public String getXFMC() {
		return XFMC;
	}
	public void setXFMC(String xFMC) {
		XFMC = xFMC;
	}
	public String getXFDZDH() {
		return XFDZDH;
	}
	public void setXFDZDH(String xFDZDH) {
		XFDZDH = xFDZDH;
	}
	public String getXFYHZH() {
		return XFYHZH;
	}
	public void setXFYHZH(String xFYHZH) {
		XFYHZH = xFYHZH;
	}
	public String getGFSH() {
		return GFSH;
	}
	public void setGFSH(String gFSH) {
		GFSH = gFSH;
	}
	public String getGFMC() {
		return GFMC;
	}
	public void setGFMC(String gFMC) {
		GFMC = gFMC;
	}
	public String getGFDZDH() {
		return GFDZDH;
	}
	public void setGFDZDH(String gFDZDH) {
		GFDZDH = gFDZDH;
	}
	public String getGFYHZH() {
		return GFYHZH;
	}
	public void setGFYHZH(String gFYHZH) {
		GFYHZH = gFYHZH;
	}
	public String getKPR() {
		return KPR;
	}
	public void setKPR(String kPR) {
		KPR = kPR;
	}
	public String getSKR() {
		return SKR;
	}
	public void setSKR(String sKR) {
		SKR = sKR;
	}
	public String getFHR() {
		return FHR;
	}
	public void setFHR(String fHR) {
		FHR = fHR;
	}
	public String getYFPDM() {
		return YFPDM;
	}
	public void setYFPDM(String yFPDM) {
		YFPDM = yFPDM;
	}
	public String getYFPHM() {
		return YFPHM;
	}
	public void setYFPHM(String yFPHM) {
		YFPHM = yFPHM;
	}
	public String getHPTZDBH() {
		return HPTZDBH;
	}
	public void setHPTZDBH(String hPTZDBH) {
		HPTZDBH = hPTZDBH;
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
	public String getBZ() {
		return BZ;
	}
	public void setBZ(String bZ) {
		BZ = bZ;
	}
	public List<CommonNode> getCOMMON_NODES() {
		return COMMON_NODES;
	}
	public void setCOMMON_NODES(List<CommonNode> cOMMON_NODES) {
		COMMON_NODES = cOMMON_NODES;
	}
	public List<FPXM> getFPXMXX() {
		return FPXMXX;
	}
	public void setFPXMXX(List<FPXM> fPXMXX) {
		FPXMXX = fPXMXX;
	}
}
