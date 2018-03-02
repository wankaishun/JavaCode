package com.liqun.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 利群传过来
 * @author wks
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="FPXM")
public class FPXM {
	@XmlElement(name="FPHXZ",required=true)
	private String FPHXZ;//发票行性质
	@XmlElement(name="XMMC",required=true)
	private String XMMC;//项目名称
	@XmlElement(name="GGXH",required=true)
	private String GGXH;//规格型号
	@XmlElement(name="JLDW",required=true)
	private String JLDW;//单位
	@XmlElement(name="XMSL",required=true)
	private String XMSL;//项目数量
	@XmlElement(name="XMDJ",required=true)
	private String XMDJ;//不含税单价
	@XmlElement(name="XMJE",required=true)
	private String XMJE;//不含税金额
	@XmlElement(name="SL",required=true)
	private String SL;//税率
	@XmlElement(name="SE",required=true)
	private String SE;//税额
	@XmlElement(name="HSDJ",required=true)
	private String HSDJ;//含税单价
	@XmlElement(name="HSJE",required=true)
	private String HSJE;//含税金额
	@XmlElement(name="SPBM",required=true)
	private String SPBM;//商品编码
	public String getFPHXZ() {
		return FPHXZ;
	}
	public void setFPHXZ(String fPHXZ) {
		FPHXZ = fPHXZ;
	}
	public String getXMMC() {
		return XMMC;
	}
	public void setXMMC(String xMMC) {
		XMMC = xMMC;
	}
	public String getGGXH() {
		return GGXH;
	}
	public void setGGXH(String gGXH) {
		GGXH = gGXH;
	}
	public String getJLDW() {
		return JLDW;
	}
	public void setJLDW(String jLDW) {
		JLDW = jLDW;
	}
	public String getXMSL() {
		return XMSL;
	}
	public void setXMSL(String xMSL) {
		XMSL = xMSL;
	}
	public String getXMDJ() {
		return XMDJ;
	}
	public void setXMDJ(String xMDJ) {
		XMDJ = xMDJ;
	}
	public String getXMJE() {
		return XMJE;
	}
	public void setXMJE(String xMJE) {
		XMJE = xMJE;
	}
	public String getSL() {
		return SL;
	}
	public void setSL(String sL) {
		SL = sL;
	}
	public String getSE() {
		return SE;
	}
	public void setSE(String sE) {
		SE = sE;
	}
	public String getHSDJ() {
		return HSDJ;
	}
	public void setHSDJ(String hSDJ) {
		HSDJ = hSDJ;
	}
	public String getHSJE() {
		return HSJE;
	}
	public void setHSJE(String hSJE) {
		HSJE = hSJE;
	}
	public String getSPBM() {
		return SPBM;
	}
	public void setSPBM(String sPBM) {
		SPBM = sPBM;
	}


}
