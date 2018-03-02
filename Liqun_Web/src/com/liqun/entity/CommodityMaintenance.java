package com.liqun.entity;

import java.util.Date;

import com.liqun.util.ExcelVOAttribute;

/**
 * CommodityMaintenance
 * @author hzy
 * 商品表实体类
 */
public class CommodityMaintenance {
	@ExcelVOAttribute(name = "ID", column = "A") 
	private Integer spid;
	@ExcelVOAttribute(name = "税收分类编码", column = "C") 
	private String spbh;
	@ExcelVOAttribute(name = "商品名称", column = "D") 
	private String spm;
	@ExcelVOAttribute(name = "规格型号", column = "E") 
	private String spgg;
	@ExcelVOAttribute(name = "计量单位", column = "F") 
	private String spdw;
	@ExcelVOAttribute(name = "不含税单价", column = "G") 
	private Double bhsdj;
	@ExcelVOAttribute(name = "含税单价", column = "H") 
	private Double hsdj;
	@ExcelVOAttribute(name = "税率", column = "I") 
	private Double sl;
	@ExcelVOAttribute(name = "商品状态", column = "L") 
	private Integer spzt;
	@ExcelVOAttribute(name = "企业内部编码", column = "B") 
	private String spbm;
	@ExcelVOAttribute(name = "优惠政策", column = "J") 
	private Integer yhzc;
	@ExcelVOAttribute(name = "优惠政策类型", column = "K") 
	private Integer yhzclx;
	@ExcelVOAttribute(name = "最后更新时间", column = "M") 
	private Date zhgxsj;
	//条件查询
    private String realName;
    private String flag;
    
    //关联表
   private BmSpflZtree bmSpflZtree;//税收编码树形菜单
   private IBilldel iBilldel;//单据表对应表

    //GetSet方法
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
		this.spbh = spbh;
	}
	public String getSpm() {
		return spm;
	}
	public void setSpm(String spm) {
		this.spm = spm;
	}
	public String getSpgg() {
		return spgg;
	}
	public void setSpgg(String spgg) {
		this.spgg = spgg;
	}
	public String getSpdw() {
		return spdw;
	}
	public void setSpdw(String spdw) {
		this.spdw = spdw;
	}
	public Double getBhsdj() {
		return bhsdj;
	}
	public void setBhsdj(Double bhsdj) {
		this.bhsdj = bhsdj;
	}
	public Double getHsdj() {
		return hsdj;
	}
	public void setHsdj(Double hsdj) {
		this.hsdj = hsdj;
	}
	public Double getSl() {
		return sl;
	}
	public void setSl(Double sl) {
		this.sl = sl;
	}
	public Integer getSpzt() {
		return spzt;
	}
	public void setSpzt(Integer spzt) {
		this.spzt = spzt;
	}
	public String getSpbm() {
		return spbm;
	}
	public void setSpbm(String spbm) {
		this.spbm = spbm;
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
	public Date getZhgxsj() {
		return zhgxsj;
	}
	public void setZhgxsj(Date zhgxsj) {
		this.zhgxsj = zhgxsj;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	//关联表
	public IBilldel getiBilldel() {
		return iBilldel;
	}
	public void setiBilldel(IBilldel iBilldel) {
		this.iBilldel = iBilldel;
	}
	public BmSpflZtree getBmSpflZtree() {
		return bmSpflZtree;
	}
	public void setBmSpflZtree(BmSpflZtree bmSpflZtree) {
		this.bmSpflZtree = bmSpflZtree;
	}
	
    
}
