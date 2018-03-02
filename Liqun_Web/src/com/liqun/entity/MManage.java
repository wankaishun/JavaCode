package com.liqun.entity;

import java.math.BigDecimal;

public class MManage {
    private Integer pzbh;

    private String qydm;

    private String qymc;

    private String qysh;

    private String qydzdh;

    private String yhzh;

    private BigDecimal zdxe;

    private String kpr;

    private String skr;

    private String fhr;

    private Integer bbbh;

    public Integer getPzbh() {
        return pzbh;
    }

    public void setPzbh(Integer pzbh) {
        this.pzbh = pzbh;
    }

    public String getQydm() {
        return qydm;
    }

    public void setQydm(String qydm) {
        this.qydm = qydm == null ? null : qydm.trim();
    }

    public String getQymc() {
        return qymc;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc == null ? null : qymc.trim();
    }

    public String getQysh() {
        return qysh;
    }

    public void setQysh(String qysh) {
        this.qysh = qysh == null ? null : qysh.trim();
    }

    public String getQydzdh() {
        return qydzdh;
    }

    public void setQydzdh(String qydzdh) {
        this.qydzdh = qydzdh == null ? null : qydzdh.trim();
    }

    public String getYhzh() {
        return yhzh;
    }

    public void setYhzh(String yhzh) {
        this.yhzh = yhzh == null ? null : yhzh.trim();
    }

    public BigDecimal getZdxe() {
        return zdxe;
    }

    public void setZdxe(BigDecimal zdxe) {
        this.zdxe = zdxe;
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

    public Integer getBbbh() {
        return bbbh;
    }

    public void setBbbh(Integer bbbh) {
        this.bbbh = bbbh;
    }
}