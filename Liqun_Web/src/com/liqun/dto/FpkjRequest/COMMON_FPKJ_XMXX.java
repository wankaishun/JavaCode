package com.liqun.dto.FpkjRequest;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="COMMON_FPKJ_XMXX")
@XmlAccessorType(XmlAccessType.FIELD)
public class COMMON_FPKJ_XMXX implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(name="FPHXZ",required=true)
	private String FPHXZ;//发票行性质</FPHXZ>
	@XmlElement(name="XMMC",required=true)
	private String XMMC;//项目名称</XMMC>
	@XmlElement(name="GGXH",required=true)
	private String GGXH;//规格型号</GGXH>
	@XmlElement(name="DW",required=true)
	private String DW;//单位</DW>
	@XmlElement(name="XMSL",required=true)
	private String XMSL;//项目数量</XMSL>
	@XmlElement(name="XMDJ",required=true)
	private String XMDJ;//项目单价</XMDJ>
	@XmlElement(name="XMJEL",required=true)
	private String XMJE;//项目金额</XMJE>
	@XmlElement(name="SL",required=true)
	private String SL;//税率</SL>
	@XmlElement(name="SE",required=true)
	private String SE;//税额</SE>
	@XmlElement(name="SPBM",required=true)
	private String SPBM;//商品编码</SPBM>
	@XmlElement(name="ZXBM",required=true)
	private String ZXBM;//自行编码</ZXBM>
	@XmlElement(name="YHZCBS",required=true)
	private String YHZCBS;//优惠政策标识<YHZCBS>
	@XmlElement(name="LSLBS",required=true)
	private String LSLBS;//零税率标识<LSLBS>
	@XmlElement(name="ZZSTSGL",required=true)
	private String ZZSTSGL;//增值税特殊管理</ZZSTSGL>
	public String getSPBM() {
		return SPBM;
	}
	public void setSPBM(String sPBM) {
		SPBM = sPBM;
	}
	public String getZXBM() {
		return ZXBM;
	}
	public void setZXBM(String zXBM) {
		ZXBM = zXBM;
	}
	public String getYHZCBS() {
		return YHZCBS;
	}
	public void setYHZCBS(String yHZCBS) {
		YHZCBS = yHZCBS;
	}
	public String getLSLBS() {
		return LSLBS;
	}
	public void setLSLBS(String lSLBS) {
		LSLBS = lSLBS;
	}
	public String getZZSTSGL() {
		return ZZSTSGL;
	}
	public void setZZSTSGL(String zZSTSGL) {
		ZZSTSGL = zZSTSGL;
	}
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
	public String getDW() {
		return DW;
	}
	public void setDW(String dW) {
		DW = dW;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "COMMON_FPKJ_XMXX [FPHXZ=" + FPHXZ + ", XMMC=" + XMMC + ", GGXH=" + GGXH + ", DW=" + DW + ", XMSL="
				+ XMSL + ", XMDJ=" + XMDJ + ", XMJE=" + XMJE + ", SL=" + SL + ", SE=" + SE + ", SPBM=" + SPBM
				+ ", ZXBM=" + ZXBM + ", YHZCBS=" + YHZCBS + ", LSLBS=" + LSLBS + ", ZZSTSGL=" + ZZSTSGL + ", getSPBM()="
				+ getSPBM() + ", getZXBM()=" + getZXBM() + ", getYHZCBS()=" + getYHZCBS() + ", getLSLBS()=" + getLSLBS()
				+ ", getZZSTSGL()=" + getZZSTSGL() + ", getFPHXZ()=" + getFPHXZ() + ", getXMMC()=" + getXMMC()
				+ ", getGGXH()=" + getGGXH() + ", getDW()=" + getDW() + ", getXMSL()=" + getXMSL() + ", getXMDJ()="
				+ getXMDJ() + ", getXMJE()=" + getXMJE() + ", getSL()=" + getSL() + ", getSE()=" + getSE()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
}
