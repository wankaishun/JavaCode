package com.liqun.entity;

import java.math.BigDecimal;
import java.util.Date;
/**
 * MProuct
 * @author yzz
 * 商品表实体类
 */
public class MProuct {
    private Integer spid;

    private String spbh;

    private String spm;

    private String spgg;

    private String spdw;

    private BigDecimal bhsdj;

    private BigDecimal hsdj;

    private BigDecimal sl;

    private String spbm;

    private Integer yhzc;

    private Integer yhzclx;

    private Integer spzt;

    private Date zhgxsj;

    private Integer hkzt;

    private String 自定义问问222;

    public Integer getSpid() {
        return spid;
    }

    public void setSpid(Integer spid) {
        this.spid = spid;
    }

    public String getSpbh() {
        return spbh;
    }

    public void setSpbh(String spbh) {
        this.spbh = spbh == null ? null : spbh.trim();
    }

    public String getSpm() {
        return spm;
    }

    public void setSpm(String spm) {
        this.spm = spm == null ? null : spm.trim();
    }

    public String getSpgg() {
        return spgg;
    }

    public void setSpgg(String spgg) {
        this.spgg = spgg == null ? null : spgg.trim();
    }

    public String getSpdw() {
        return spdw;
    }

    public void setSpdw(String spdw) {
        this.spdw = spdw == null ? null : spdw.trim();
    }

    public BigDecimal getBhsdj() {
        return bhsdj;
    }

    public void setBhsdj(BigDecimal bhsdj) {
        this.bhsdj = bhsdj;
    }

    public BigDecimal getHsdj() {
        return hsdj;
    }

    public void setHsdj(BigDecimal hsdj) {
        this.hsdj = hsdj;
    }

    public BigDecimal getSl() {
        return sl;
    }

    public void setSl(BigDecimal sl) {
        this.sl = sl;
    }

    public String getSpbm() {
        return spbm;
    }

    public void setSpbm(String spbm) {
        this.spbm = spbm == null ? null : spbm.trim();
    }

    public Integer getYhzc() {
        return yhzc;
    }

    public void setYhzc(Integer yhzc) {
        this.yhzc = yhzc;
    }

    public Integer getYhzclx() {
        return yhzclx;
    }

    public void setYhzclx(Integer yhzclx) {
        this.yhzclx = yhzclx;
    }

    public Integer getSpzt() {
        return spzt;
    }

    public void setSpzt(Integer spzt) {
        this.spzt = spzt;
    }

    public Date getZhgxsj() {
        return zhgxsj;
    }

    public void setZhgxsj(Date zhgxsj) {
        this.zhgxsj = zhgxsj;
    }

    public Integer getHkzt() {
        return hkzt;
    }

    public void setHkzt(Integer hkzt) {
        this.hkzt = hkzt;
    }

    public String get自定义问问222() {
        return 自定义问问222;
    }

    public void set自定义问问222(String 自定义问问222) {
        this.自定义问问222 = 自定义问问222 == null ? null : 自定义问问222.trim();
    }
}