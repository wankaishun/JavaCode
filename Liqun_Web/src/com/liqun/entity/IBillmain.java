package com.liqun.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.liqun.util.ExcelVOAttribute;
/**
 * 单据信息主表
 * @author wks
 *
 */
public class IBillmain {
	 @ExcelVOAttribute(name = "序号", column = "A")  
	private Integer id;
    private String qybh;
    @ExcelVOAttribute(name = "发票提取码", column = "B")  
    private String fptqm;
    @ExcelVOAttribute(name = "单据流水号", column = "C")  
    private String fplsh;
    
    private Integer djzt;
    @ExcelVOAttribute(name = "单据状态", column = "D")   
    private String djztName;
    private Integer djlx;
    @ExcelVOAttribute(name = "单据类型", column = "E") 
    private String djlxName;

    public String getDjlxName() {
		return djlxName;
	}

	public void setDjlxName(String djlxName) {
		this.djlxName = djlxName;
	}

	private Integer djly;
    private String nsrsbh;

    private String fjh;
    @ExcelVOAttribute(name = "开票终端号", column = "F") 
    private String kpzdh;
    @ExcelVOAttribute(name = "单据状态", column = "G") 
    private Integer fpzl;
    @ExcelVOAttribute(name = "单据状态", column = "H") 
    private Integer kplx;
    @ExcelVOAttribute(name = "单据状态", column = "I") 
    private String xfsh;
    @ExcelVOAttribute(name = "单据状态", column = "J") 
    private String xfmc;
    @ExcelVOAttribute(name = "单据状态", column = "K") 
    private String xfdzdh;
    @ExcelVOAttribute(name = "单据状态", column = "L") 
    private String xfyhzh;
    @ExcelVOAttribute(name = "购方税号", column = "M") 
    private String gfsh;
    @ExcelVOAttribute(name = "购方名称", column = "N") 
    private String gfmc;
    @ExcelVOAttribute(name = "购方地址电话", column = "O") 
    private String gfdzdh;
    @ExcelVOAttribute(name = "购方银行账号", column = "P") 
    private String gfyhzh;
    @ExcelVOAttribute(name = "开票人", column = "Q") 
    private String kpr;
    @ExcelVOAttribute(name = "收款人", column = "R") 
    private String skr;
    @ExcelVOAttribute(name = "复核人", column = "S") 
    private String fhr;
    private String yfpdm;
    private String yfphm;
    private String hptzdbh;
    private Integer qdbz;
    @ExcelVOAttribute(name = "含税金额", column = "T") 
    private BigDecimal jshj;
    @ExcelVOAttribute(name = "合计金额", column = "U") 
    private BigDecimal hjje;
    @ExcelVOAttribute(name = "合计税额", column = "V") 
    private BigDecimal hjse;
    private String bmbbh;
    @ExcelVOAttribute(name = "备注", column = "W") 
    private String bz;
    @ExcelVOAttribute(name = "手机号", column = "X") 
    private String sj;
    @ExcelVOAttribute(name = "邮箱", column = "Y") 
    private String email;
    private Integer jfzt;
    @ExcelVOAttribute(name = "操作时间", column = "Z") 
    private Date czsj;
    public String getQybh() {
        return qybh;
    }

    public void setQybh(String qybh) {
        this.qybh = qybh == null ? null : qybh.trim();
    }

    public String getFptqm() {
        return fptqm;
    }

    public void setFptqm(String fptqm) {
        this.fptqm = fptqm == null ? null : fptqm.trim();
    }

    public String getFplsh() {
        return fplsh;
    }

    public void setFplsh(String fplsh) {
        this.fplsh = fplsh == null ? null : fplsh.trim();
    }

    public Integer getDjzt() {
        return djzt;
    }

    public void setDjzt(Integer djzt) {
        this.djzt = djzt;
    }

    public Integer getDjlx() {
        return djlx;
    }

    public void setDjlx(Integer djlx) {
        this.djlx = djlx;
    }

    public Integer getDjly() {
        return djly;
    }

    public void setDjly(Integer djly) {
        this.djly = djly;
    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh == null ? null : nsrsbh.trim();
    }

    public String getFjh() {
        return fjh;
    }

    public void setFjh(String fjh) {
        this.fjh = fjh == null ? null : fjh.trim();
    }

    public String getKpzdh() {
        return kpzdh;
    }

    public void setKpzdh(String kpzdh) {
        this.kpzdh = kpzdh == null ? null : kpzdh.trim();
    }

    public Integer getFpzl() {
        return fpzl;
    }

    public void setFpzl(Integer fpzl) {
        this.fpzl = fpzl;
    }

    public Integer getKplx() {
        return kplx;
    }

    public void setKplx(Integer kplx) {
        this.kplx = kplx;
    }

    public String getXfsh() {
        return xfsh;
    }

    public void setXfsh(String xfsh) {
        this.xfsh = xfsh == null ? null : xfsh.trim();
    }

    public String getXfmc() {
        return xfmc;
    }

    public void setXfmc(String xfmc) {
        this.xfmc = xfmc == null ? null : xfmc.trim();
    }

    public String getXfdzdh() {
        return xfdzdh;
    }

    public void setXfdzdh(String xfdzdh) {
        this.xfdzdh = xfdzdh == null ? null : xfdzdh.trim();
    }

    public String getXfyhzh() {
        return xfyhzh;
    }

    public void setXfyhzh(String xfyhzh) {
        this.xfyhzh = xfyhzh == null ? null : xfyhzh.trim();
    }

    public String getGfsh() {
        return gfsh;
    }

    public void setGfsh(String gfsh) {
        this.gfsh = gfsh == null ? null : gfsh.trim();
    }

    public String getGfmc() {
        return gfmc;
    }

    public void setGfmc(String gfmc) {
        this.gfmc = gfmc == null ? null : gfmc.trim();
    }

    public String getGfdzdh() {
        return gfdzdh;
    }

    public void setGfdzdh(String gfdzdh) {
        this.gfdzdh = gfdzdh == null ? null : gfdzdh.trim();
    }

    public String getGfyhzh() {
        return gfyhzh;
    }

    public void setGfyhzh(String gfyhzh) {
        this.gfyhzh = gfyhzh == null ? null : gfyhzh.trim();
    }

    public String getKpr() {
        return kpr;
    }

    public void setKpr(String kpr) {
        this.kpr = kpr == null ? null : kpr.trim();
    }

    public String getSkr() {
        return skr;
    }

    public void setSkr(String skr) {
        this.skr = skr == null ? null : skr.trim();
    }

    public String getFhr() {
        return fhr;
    }

    public void setFhr(String fhr) {
        this.fhr = fhr == null ? null : fhr.trim();
    }

    public String getYfpdm() {
        return yfpdm;
    }

    public void setYfpdm(String yfpdm) {
        this.yfpdm = yfpdm == null ? null : yfpdm.trim();
    }

    public String getYfphm() {
        return yfphm;
    }

    public void setYfphm(String yfphm) {
        this.yfphm = yfphm == null ? null : yfphm.trim();
    }

    public String getHptzdbh() {
        return hptzdbh;
    }

    public void setHptzdbh(String hptzdbh) {
        this.hptzdbh = hptzdbh == null ? null : hptzdbh.trim();
    }

    public Integer getQdbz() {
        return qdbz;
    }

    public void setQdbz(Integer qdbz) {
        this.qdbz = qdbz;
    }

    public BigDecimal getJshj() {
        return jshj;
    }

    public void setJshj(BigDecimal jshj) {
        this.jshj = jshj;
    }

    public BigDecimal getHjje() {
        return hjje;
    }

    public void setHjje(BigDecimal hjje) {
        this.hjje = hjje;
    }

    public BigDecimal getHjse() {
        return hjse;
    }

    public void setHjse(BigDecimal hjse) {
        this.hjse = hjse;
    }

    public String getBmbbh() {
        return bmbbh;
    }

    public void setBmbbh(String bmbbh) {
        this.bmbbh = bmbbh == null ? null : bmbbh.trim();
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    public String getSj() {
        return sj;
    }

    public void setSj(String sj) {
        this.sj = sj == null ? null : sj.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getJfzt() {
        return jfzt;
    }

    public void setJfzt(Integer jfzt) {
        this.jfzt = jfzt;
    }

    public Date getCzsj() {
        return czsj;
    }

    public void setCzsj(Date czsj) {
        this.czsj = czsj;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDjztName() {
		return djztName;
	}

	public void setDjztName(String djztName) {
		this.djztName = djztName;
	}
}