package com.liqun.dto.FpkjRequest;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author 朱
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="COMMON_FPKJ_FPT")
@SuppressWarnings("all")
public class COMMON_FPKJ_FPT implements Serializable{
	@XmlAttribute(name = "class")
	private String classes;
	@XmlElement(name="FPQQLSH",required=true)
	private String FPQQLSH;//发票请求流水号</FPQQLSH>
	@XmlElement(name="KPLX",required=true)
	private String KPLX;//开票类型</KPLX>
	@XmlElement(name="XSF_NSRSBH",required=true)
	private String XSF_NSRSBH;//销售方纳税人识别号</XSF_NSRSBH>
	@XmlElement(name="XSF_MC",required=true)
	private String XSF_MC;//销售方名称</XSF_MC>
	@XmlElement(name="XSF_DZDH",required=true)
	private String XSF_DZDH;//销售方地址、电话</XSF_DZDH>
	@XmlElement(name="XSF_YHZH",required=true)
	private String XSF_YHZH;//销售方银行账号</XSF_YHZH>
	@XmlElement(name="GMF_NSRSBH",required=true)
	private String GMF_NSRSBH;//购买方纳税人识别号</GMF_NSRSBH>
	@XmlElement(name="GMF_MC",required=true)
	private String GMF_MC;//购买方名称</GMF_MC>
	@XmlElement(name="GMF_DZDH",required=true)
	private String GMF_DZDH;//购买方地址、电话</GMF_DZDH>
	@XmlElement(name="GMF_YHZH",required=true)
	private String GMF_YHZH;//购买方银行账号</GMF_YHZH>
	@XmlElement(name="KPR",required=true)
	private String KPR;//开票人</KPR>
	@XmlElement(name="SKR",required=true)
	private String SKR;//收款人</SKR>
	@XmlElement(name="FHR",required=true)
	private String FHR;//复核人</FHR>
	@XmlElement(name="YFP_DM",required=true)
	private String YFP_DM;//原发票代码</YFP_DM>
	@XmlElement(name="YFP_HM",required=true)
	private String YFP_HM;//原发票号码</YFP_HM>
	@XmlElement(name="JSHJ",required=true)
	private String JSHJ;//价税合计</JSHJ>
	@XmlElement(name="HJJE",required=true)
	private String HJJE;//合计金额</HJJE>
	@XmlElement(name="HJSE",required=true)
	private String HJSE;//合计税额</HJSE>
	@XmlElement(name="GHF_SJ",required=true)
	private String GHF_SJ;//购货方手机</GHF_SJ>
	@XmlElement(name="GHF_EMAIL",required=true)
	private String GHF_EMAIL;//购货方邮箱</GHF_EMAIL>
	@XmlElement(name="BZ",required=true)
	private String BZ;//备注</BZ>
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
	public String getKPLX() {
		return KPLX;
	}
	public void setKPLX(String kPLX) {
		KPLX = kPLX;
	}
	public String getXSF_NSRSBH() {
		return XSF_NSRSBH;
	}
	public void setXSF_NSRSBH(String xSF_NSRSBH) {
		XSF_NSRSBH = xSF_NSRSBH;
	}
	public String getXSF_MC() {
		return XSF_MC;
	}
	public void setXSF_MC(String xSF_MC) {
		XSF_MC = xSF_MC;
	}
	public String getXSF_DZDH() {
		return XSF_DZDH;
	}
	public void setXSF_DZDH(String xSF_DZDH) {
		XSF_DZDH = xSF_DZDH;
	}
	public String getXSF_YHZH() {
		return XSF_YHZH;
	}
	public void setXSF_YHZH(String xSF_YHZH) {
		XSF_YHZH = xSF_YHZH;
	}
	public String getGMF_NSRSBH() {
		return GMF_NSRSBH;
	}
	public void setGMF_NSRSBH(String gMF_NSRSBH) {
		GMF_NSRSBH = gMF_NSRSBH;
	}
	public String getGMF_MC() {
		return GMF_MC;
	}
	public void setGMF_MC(String gMF_MC) {
		GMF_MC = gMF_MC;
	}
	public String getGMF_DZDH() {
		return GMF_DZDH;
	}
	public void setGMF_DZDH(String gMF_DZDH) {
		GMF_DZDH = gMF_DZDH;
	}
	public String getGMF_YHZH() {
		return GMF_YHZH;
	}
	public void setGMF_YHZH(String gMF_YHZH) {
		GMF_YHZH = gMF_YHZH;
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
	public String getYFP_DM() {
		return YFP_DM;
	}
	public void setYFP_DM(String yFP_DM) {
		YFP_DM = yFP_DM;
	}
	public String getYFP_HM() {
		return YFP_HM;
	}
	public void setYFP_HM(String yFP_HM) {
		YFP_HM = yFP_HM;
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
	public String getGHF_SJ() {
		return GHF_SJ;
	}
	public void setGHF_SJ(String gHF_SJ) {
		GHF_SJ = gHF_SJ;
	}
	public String getGHF_EMAIL() {
		return GHF_EMAIL;
	}
	public void setGHF_EMAIL(String gHF_EMAIL) {
		GHF_EMAIL = gHF_EMAIL;
	}
	public String getBZ() {
		return BZ;
	}
	public void setBZ(String bZ) {
		BZ = bZ;
	}
	@Override
	public String toString() {
		return "COMMON_FPKJ_FPT [classes=" + classes + ", FPQQLSH=" + FPQQLSH + ", KPLX=" + KPLX + ", XSF_NSRSBH="
				+ XSF_NSRSBH + ", XSF_MC=" + XSF_MC + ", XSF_DZDH=" + XSF_DZDH + ", XSF_YHZH=" + XSF_YHZH
				+ ", GMF_NSRSBH=" + GMF_NSRSBH + ", GMF_MC=" + GMF_MC + ", GMF_DZDH=" + GMF_DZDH + ", GMF_YHZH="
				+ GMF_YHZH + ", KPR=" + KPR + ", SKR=" + SKR + ", FHR=" + FHR + ", YFP_DM=" + YFP_DM + ", YFP_HM="
				+ YFP_HM + ", JSHJ=" + JSHJ + ", HJJE=" + HJJE + ", HJSE=" + HJSE + ", GHF_SJ=" + GHF_SJ
				+ ", GHF_EMAIL=" + GHF_EMAIL + ", BZ=" + BZ + "]";
	}
	
}
