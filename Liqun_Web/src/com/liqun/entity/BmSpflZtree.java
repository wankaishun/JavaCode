package com.liqun.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 税收分类编码属性菜单
 * @author hzy
 * entity实体类
 */
public final class BmSpflZtree{

	 @Override
	public String toString() {
			return "BmSpflZtree [bm=" + bm + ", hbbm=" + hbbm + ", mc=" + mc + ", sm="
					+ sm + ", slv=" + slv + ", zzstsgl=" + zzstsgl + ", zzszcyj=" + zzszcyj
					+ ", zzstsnrdm=" + zzstsnrdm + ", xfsgl=" + xfsgl + ", xfszcyj=" + xfszcyj
					+ ", xfstsnrdm=" + xfstsnrdm + ", gjz=" + gjz + ", hzx=" + hzx + ", tjjbm="
					+ tjjbm + ", bmbBbh=" + bmbBbh + ", bbh=" + bbh + ", kyzt=" + kyzt
					+ ", qysj=" + qysj + ", gdqjzsj=" + gdqjzsj + ", qysj=" + qysj+", wj=" 
					+ wj+", ishide=" + ishide+", slvselect=" + slvselect+", yhzc=" + yhzc+", yhzcmc=" + yhzcmc+
					", lslvbs=" + lslvbs + ", hgjcksppm=" + hgjcksppm + "]";
		}
	 
	private	String bm; 	
	private String hbbm; 	
	private String mc; 	
	private String sm; 	
	private String slv; 	
	private String zzstsgl;
	private String zzszcyj;
	private String zzstsnrdm;
	private String xfsgl;
	private String xfszcyj;
	private String xfstsnrdm;
	private String gjz;
	private String hzx;
	private String tjjbm;
	private String bmbBbh;
	private String bbh;
	private String kyzt;
	private String qysj;
	private String gdqjzsj;
	private String sjbm;
	private Date gxsj;
	private Integer wj;
	private Integer ishide;
	private Float slvselect;
	private String yhzc;
	private String yhzcmc;
	private String lslvbs;
	private String hgjcksppm;

	public BmSpflZtree() {
		super();
	}
	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}
	public String getHbbm() {
		return hbbm;
	}
	public void setHbbm(String hbbm) {
		this.hbbm = hbbm;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getSm() {
		return sm;
	}
	public void setSm(String sm) {
		this.sm = sm;
	}
	public String getSlv() {
		return slv;
	}
	public void setSlv(String slv) {
		this.slv = slv;
	}
	public String getZzstsgl() {
		return zzstsgl;
	}
	public void setZzstsgl(String zzstsgl) {
		this.zzstsgl = zzstsgl;
	}
	public String getZzszcyj() {
		return zzszcyj;
	}
	public void setZzszcyj(String zzszcyj) {
		this.zzszcyj = zzszcyj;
	}
	public String getZzstsnrdm() {
		return zzstsnrdm;
	}
	public void setZzstsnrdm(String zzstsnrdm) {
		this.zzstsnrdm = zzstsnrdm;
	}
	public String getXfsgl() {
		return xfsgl;
	}
	public void setXfsgl(String xfsgl) {
		this.xfsgl = xfsgl;
	}
	public String getXfszcyj() {
		return xfszcyj;
	}
	public void setXfszcyj(String xfszcyj) {
		this.xfszcyj = xfszcyj;
	}
	public String getXfstsnrdm() {
		return xfstsnrdm;
	}
	public void setXfstsnrdm(String xfstsnrdm) {
		this.xfstsnrdm = xfstsnrdm;
	}
	public String getGjz() {
		return gjz;
	}
	public void setGjz(String gjz) {
		this.gjz = gjz;
	}
	public String getHzx() {
		return hzx;
	}
	public void setHzx(String hzx) {
		this.hzx = hzx;
	}
	public String getTjjbm() {
		return tjjbm;
	}
	public void setTjjbm(String tjjbm) {
		this.tjjbm = tjjbm;
	}
	public String getBmbBbh() {
		return bmbBbh;
	}
	public void setBmbBbh(String bmbBbh) {
		this.bmbBbh = bmbBbh;
	}
	public String getBbh() {
		return bbh;
	}
	public void setBbh(String bbh) {
		this.bbh = bbh;
	}
	public String getKyzt() {
		return kyzt;
	}
	public void setKyzt(String kyzt) {
		this.kyzt = kyzt;
	}
	public String getQysj() {
		return qysj;
	}
	public void setQysj(String qysj) {
		this.qysj = qysj;
	}
	public String getGdqjzsj() {
		return gdqjzsj;
	}
	public void setGdqjzsj(String gdqjzsj) {
		this.gdqjzsj = gdqjzsj;
	}
	public String getSjbm() {
		return sjbm;
	}
	public void setSjbm(String sjbm) {
		this.sjbm = sjbm;
	}
	public Date getGxsj() {
		return gxsj;
	}
	public void setGxsj(Date gxsj) {
		this.gxsj = gxsj;
	}
	public Integer getWj() {
		return wj;
	}
	public void setWj(Integer wj) {
		this.wj = wj;
	}
	public Integer getIshide() {
		return ishide;
	}
	public void setIshide(Integer ishide) {
		this.ishide = ishide;
	}
	public Float getSlvselect() {
		return slvselect;
	}
	public void setSlvselect(Float slvselect) {
		this.slvselect = slvselect;
	}
	public String getYhzc() {
		return yhzc;
	}
	public void setYhzc(String yhzc) {
		this.yhzc = yhzc;
	}
	public String getYhzcmc() {
		return yhzcmc;
	}
	public void setYhzcmc(String yhzcmc) {
		this.yhzcmc = yhzcmc;
	}
	public String getLslvbs() {
		return lslvbs;
	}
	public void setLslvbs(String lslvbs) {
		this.lslvbs = lslvbs;
	}
	public String getHgjcksppm() {
		return hgjcksppm;
	}
	public void setHgjcksppm(String hgjcksppm) {
		this.hgjcksppm = hgjcksppm;
	}
	
}
