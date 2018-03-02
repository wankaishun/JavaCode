package com.liqun.dto;

import java.math.BigDecimal;

import com.liqun.util.ExcelVOAttribute;

public class KptjDto {

	/*@ExcelVOAttribute(name = "含税金额", column = "A")    
    private BigDecimal hsje;
	@ExcelVOAttribute(name = "项目金额", column = "B")    
	private BigDecimal xmje;
	@ExcelVOAttribute(name = "税额", column = "AI")   
    private BigDecimal se;
	public BigDecimal getHsje() {
		return hsje;
	}
	public void setHsje(BigDecimal hsje) {
		this.hsje = hsje;
	}
	public BigDecimal getXmje() {
		return xmje;
	}
	public void setXmje(BigDecimal xmje) {
		this.xmje = xmje;
	}
	public BigDecimal getSe() {
		return se;
	}
	public void setSe(BigDecimal se) {
		this.se = se;
	}*/
	
	//创世纪
	private String tjlx;//统计类型
	private String hj;//合计
	private String shuilv1;//百分之17
	private String shuilv2;//百分之13
	private String shuilv3;//百分之11
	private String shuilv4;//百分之6
	private String shuilv5;//百分之4
	private String shuilv6;//百分之3
	private String shuilv7;//其他
	private String kplx;//开票类型
	
	private String kpxm;//开票项目
	private String xxzshsje;//销项正数含税金额
	private String xxzshsjehj;//销项正数含税金额合计
	
	private String xxfshsje;//销项负数含税金额
	private String xxfshsjehj;//销项负数含税金额合计
	
	private String xxzswsje;//销项正数未税金额
	private String xxzswsjehj;//销项正数未税金额合计
	
	private String xxfswsje;//销项负数未税金额
	private String xxfswsjehj;//销项负数未税金额合计
	
	private String xxzsse;//销项正数税额
	private String xxzssehj;//销项正数税额合计
	
	private String xxfsse;//销项负数税额
	private String xxfssehj;//销项负数税额合计
	
	private String xxhsjehj;//销项含税金额合计
	private String xxbhsjehj;//销项不含税金额合计
	private String xxsehj;//销项税额合计
	
	
	
	@ExcelVOAttribute(name = "pos编号", column = "I")    
	private String posId;

    private String hz51Id;
    @ExcelVOAttribute(name = "企业编号", column = "H")    
    private String qybh;
    @ExcelVOAttribute(name = "单据状态", column = "B") 
    private String djzt;
    	   
	private Integer djly;

	@ExcelVOAttribute(name = "发票类型", column = "A")    
	private String fplx;
	@ExcelVOAttribute(name = "发票提取码", column = "C")    
    private String fptqm;
	@ExcelVOAttribute(name = "发票流水号", column = "D")    
    private String fplsh;
	@ExcelVOAttribute(name = "发票代码", column = "E")    
    private String fpdm;
	@ExcelVOAttribute(name = "发票号码", column = "F")    
    private String fphm;
	@ExcelVOAttribute(name = "开票时间", column = "G")    
    private String kpsj;
	@ExcelVOAttribute(name = "销方名称", column = "N")    
    private String xfmc;
	@ExcelVOAttribute(name = "销方税号", column = "O")    
    private String xfsh; //销方税号
	@ExcelVOAttribute(name = "销方银行账号", column = "P")    
    private String xfyhzh;//销方银行账号
	@ExcelVOAttribute(name = "销方地址电话", column = "Q")    
    private String xfdzdh;//销方地址电话
	@ExcelVOAttribute(name = "含税金额", column = "S")    
    private BigDecimal hsje;//含税金额
	@ExcelVOAttribute(name = "邮箱", column = "AA")    
    private String email;//邮箱
  

  

	private String fpmw;

    private String fwm;

    private String ewm;

    private String ewm51;

    private BigDecimal jshj;
    @ExcelVOAttribute(name = "合计金额", column = "T")    
    private BigDecimal hjje;
    @ExcelVOAttribute(name = "合计税额", column = "U")    
    private BigDecimal hjse;

    private Integer qdbz;

    private String bz;
    @ExcelVOAttribute(name = "pdf下载地址", column = "R")    
    private String pdfxzdz;
    @ExcelVOAttribute(name = "购方名称", column = "J")    
    private String gfmc;
    @ExcelVOAttribute(name = "购方税号", column = "K")    
    private String gfsh;
    @ExcelVOAttribute(name = "购方地址电话", column = "M")    
    private String gfdzdh;
    @ExcelVOAttribute(name = "购方银行账号", column = "L")    
    private String gfyhzh;
    @ExcelVOAttribute(name = "开票人", column = "V")    
    private String kpr;
    @ExcelVOAttribute(name = "收款人", column = "W")    
    private String skr;
    @ExcelVOAttribute(name = "复核人", column = "X")    
    private String fhr;

    private Integer qddybz;

    private Integer fpchbz;

    private Integer hxbz;

    private Integer zfbz;

    private String swyf;
    @ExcelVOAttribute(name = "手机号", column = "Z")    
    private String sjh;

    private Integer fpxzzt;

    private String syh;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //------------------------------------------
  

    private Integer pfhh;
    @ExcelVOAttribute(name = "商品名称", column = "AC")    
    private String xmmc;
    @ExcelVOAttribute(name = "商品编码", column = "AB")    
    private String spbm;

    private String spbh;
    @ExcelVOAttribute(name = "规格型号", column = "AD")    
    private String ggxh;
    @ExcelVOAttribute(name = "计量单位", column = "AE")    
    private String jldw;
    @ExcelVOAttribute(name = "数量", column = "AF")   
    private BigDecimal xmsl;
    @ExcelVOAttribute(name = "单价", column = "AG")    
    private BigDecimal xmdj;

    private BigDecimal xmje;
    @ExcelVOAttribute(name = "税率", column = "AH")   
    private BigDecimal sl;
    @ExcelVOAttribute(name = "税额", column = "AI")   
    private BigDecimal se;
    @ExcelVOAttribute(name = "含税单价", column = "AF")   
    private BigDecimal hsdj;

   
    @ExcelVOAttribute(name = "折扣含税金额", column = "AL")
    private BigDecimal zkhsje;
    @ExcelVOAttribute(name = "折扣金额", column = "AJ")   
    private BigDecimal zkje;
    @ExcelVOAttribute(name = "折扣税额", column = "AK")   
    private BigDecimal zkse;
    @ExcelVOAttribute(name = "折扣后含税金额", column = "AM")
    private BigDecimal zkhhsje;
    @ExcelVOAttribute(name = "折扣后金额", column = "AN")
    private BigDecimal zkhje;
    @ExcelVOAttribute(name = "折扣后税额", column = "AO")
    private BigDecimal zkhse;

    private Integer sfyhzc;

    private String yhzcnr;

    private String lslbs;


    public Integer getPfhh() {
        return pfhh;
    }

    public void setPfhh(Integer pfhh) {
        this.pfhh = pfhh;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc == null ? null : xmmc.trim();
    }

    public String getSpbm() {
        return spbm;
    }

    public void setSpbm(String spbm) {
        this.spbm = spbm == null ? null : spbm.trim();
    }

    public String getSpbh() {
        return spbh;
    }

    public void setSpbh(String spbh) {
        this.spbh = spbh == null ? null : spbh.trim();
    }

    public String getGgxh() {
        return ggxh;
    }

    public void setGgxh(String ggxh) {
        this.ggxh = ggxh == null ? null : ggxh.trim();
    }

    public String getJldw() {
        return jldw;
    }

    public void setJldw(String jldw) {
        this.jldw = jldw == null ? null : jldw.trim();
    }

    public BigDecimal getXmsl() {
        return xmsl;
    }

    public void setXmsl(BigDecimal xmsl) {
        this.xmsl = xmsl;
    }

    public BigDecimal getXmdj() {
        return xmdj;
    }

    public void setXmdj(BigDecimal xmdj) {
        this.xmdj = xmdj;
    }

    public BigDecimal getXmje() {
        return xmje;
    }

    public void setXmje(BigDecimal xmje) {
        this.xmje = xmje;
    }

    public BigDecimal getSl() {
        return sl;
    }

    public void setSl(BigDecimal sl) {
        this.sl = sl;
    }

    public BigDecimal getSe() {
        return se;
    }

    public void setSe(BigDecimal se) {
        this.se = se;
    }

    public BigDecimal getHsdj() {
        return hsdj;
    }

    public void setHsdj(BigDecimal hsdj) {
        this.hsdj = hsdj;
    }

    public BigDecimal getHsje() {
        return hsje;
    }

    public void setHsje(BigDecimal hsje) {
        this.hsje = hsje;
    }

    public BigDecimal getZkhsje() {
        return zkhsje;
    }

    public void setZkhsje(BigDecimal zkhsje) {
        this.zkhsje = zkhsje;
    }

    public BigDecimal getZkje() {
        return zkje;
    }

    public void setZkje(BigDecimal zkje) {
        this.zkje = zkje;
    }

    public BigDecimal getZkse() {
        return zkse;
    }

    public void setZkse(BigDecimal zkse) {
        this.zkse = zkse;
    }

    public BigDecimal getZkhhsje() {
        return zkhhsje;
    }

    public void setZkhhsje(BigDecimal zkhhsje) {
        this.zkhhsje = zkhhsje;
    }

    public BigDecimal getZkhje() {
        return zkhje;
    }

    public void setZkhje(BigDecimal zkhje) {
        this.zkhje = zkhje;
    }

    public BigDecimal getZkhse() {
        return zkhse;
    }

    public void setZkhse(BigDecimal zkhse) {
        this.zkhse = zkhse;
    }

    public Integer getSfyhzc() {
        return sfyhzc;
    }

    public void setSfyhzc(Integer sfyhzc) {
        this.sfyhzc = sfyhzc;
    }

    public String getYhzcnr() {
        return yhzcnr;
    }

    public void setYhzcnr(String yhzcnr) {
        this.yhzcnr = yhzcnr == null ? null : yhzcnr.trim();
    }

    public String getLslbs() {
        return lslbs;
    }

    public void setLslbs(String lslbs) {
        this.lslbs = lslbs == null ? null : lslbs.trim();
    }
    
    
    
    public String getPosId() {
		return posId;
	}

	public void setPosId(String posId) {
		this.posId = posId;
	}

	public String getHz51Id() {
		return hz51Id;
	}

	public void setHz51Id(String hz51Id) {
		this.hz51Id = hz51Id;
	}

	public String getQybh() {
		return qybh;
	}

	public void setQybh(String qybh) {
		this.qybh = qybh;
	}

	public Integer getDjly() {
		return djly;
	}

	public void setDjly(Integer djly) {
		this.djly = djly;
	}

	public String getFptqm() {
		return fptqm;
	}

	public void setFptqm(String fptqm) {
		this.fptqm = fptqm;
	}

	public String getFplsh() {
		return fplsh;
	}

	public void setFplsh(String fplsh) {
		this.fplsh = fplsh;
	}

	public String getFpdm() {
		return fpdm;
	}

	public void setFpdm(String fpdm) {
		this.fpdm = fpdm;
	}

	public String getFphm() {
		return fphm;
	}

	public void setFphm(String fphm) {
		this.fphm = fphm;
	}

	public String getKpsj() {
		return kpsj;
	}

	public void setKpsj(String kpsj) {
		this.kpsj = kpsj;
	}

	public String getFpmw() {
		return fpmw;
	}

	public void setFpmw(String fpmw) {
		this.fpmw = fpmw;
	}

	public String getFwm() {
		return fwm;
	}

	public void setFwm(String fwm) {
		this.fwm = fwm;
	}

	public String getEwm() {
		return ewm;
	}

	public void setEwm(String ewm) {
		this.ewm = ewm;
	}

	public String getEwm51() {
		return ewm51;
	}

	public void setEwm51(String ewm51) {
		this.ewm51 = ewm51;
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

	public Integer getQdbz() {
		return qdbz;
	}

	public void setQdbz(Integer qdbz) {
		this.qdbz = qdbz;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getPdfxzdz() {
		return pdfxzdz;
	}

	public void setPdfxzdz(String pdfxzdz) {
		this.pdfxzdz = pdfxzdz;
	}

	public String getGfmc() {
		return gfmc;
	}

	public void setGfmc(String gfmc) {
		this.gfmc = gfmc;
	}

	public String getGfsh() {
		return gfsh;
	}

	public void setGfsh(String gfsh) {
		this.gfsh = gfsh;
	}

	public String getGfdzdh() {
		return gfdzdh;
	}

	public void setGfdzdh(String gfdzdh) {
		this.gfdzdh = gfdzdh;
	}

	public String getGfyhzh() {
		return gfyhzh;
	}

	public void setGfyhzh(String gfyhzh) {
		this.gfyhzh = gfyhzh;
	}

	public String getKpr() {
		return kpr;
	}

	public void setKpr(String kpr) {
		this.kpr = kpr;
	}

	public String getSkr() {
		return skr;
	}

	public void setSkr(String skr) {
		this.skr = skr;
	}

	public String getFhr() {
		return fhr;
	}

	public void setFhr(String fhr) {
		this.fhr = fhr;
	}

	public Integer getQddybz() {
		return qddybz;
	}

	public void setQddybz(Integer qddybz) {
		this.qddybz = qddybz;
	}

	public Integer getFpchbz() {
		return fpchbz;
	}

	public void setFpchbz(Integer fpchbz) {
		this.fpchbz = fpchbz;
	}

	public Integer getHxbz() {
		return hxbz;
	}

	public void setHxbz(Integer hxbz) {
		this.hxbz = hxbz;
	}

	public Integer getZfbz() {
		return zfbz;
	}

	public void setZfbz(Integer zfbz) {
		this.zfbz = zfbz;
	}

	public String getSwyf() {
		return swyf;
	}

	public void setSwyf(String swyf) {
		this.swyf = swyf;
	}

	public String getSjh() {
		return sjh;
	}

	public void setSjh(String sjh) {
		this.sjh = sjh;
	}

	public Integer getFpxzzt() {
		return fpxzzt;
	}

	public void setFpxzzt(Integer fpxzzt) {
		this.fpxzzt = fpxzzt;
	}

	public String getSyh() {
		return syh;
	}

	public void setSyh(String syh) {
		this.syh = syh;
	}
	
	 public String getDjzt() {
			return djzt;
		}

		public void setDjzt(String djzt) {
			this.djzt = djzt;
		}

	public String getXfmc() {
			return xfmc;
		}

		public void setXfmc(String xfmc) {
			this.xfmc = xfmc;
		}

	public String getXfsh() {
			return xfsh;
		}

		public void setXfsh(String xfsh) {
			this.xfsh = xfsh;
		}

	public String getXfyhzh() {
			return xfyhzh;
		}

		public void setXfyhzh(String xfyhzh) {
			this.xfyhzh = xfyhzh;
		}

	public String getXfdzdh() {
			return xfdzdh;
		}

		public void setXfdzdh(String xfdzdh) {
			this.xfdzdh = xfdzdh;
		}
	    public String getFplx() {
			return fplx;
		}

		public void setFplx(String fplx) {
			this.fplx = fplx;
		}


	public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getTjlx() {
			return tjlx;
		}

		public void setTjlx(String tjlx) {
			this.tjlx = tjlx;
		}

		public String getHj() {
			return hj;
		}

		public void setHj(String hj) {
			this.hj = hj;
		}

		public String getShuilv1() {
			return shuilv1;
		}

		public void setShuilv1(String shuilv1) {
			this.shuilv1 = shuilv1;
		}

		public String getShuilv2() {
			return shuilv2;
		}

		public void setShuilv2(String shuilv2) {
			this.shuilv2 = shuilv2;
		}

		public String getShuilv3() {
			return shuilv3;
		}

		public void setShuilv3(String shuilv3) {
			this.shuilv3 = shuilv3;
		}

		public String getShuilv4() {
			return shuilv4;
		}

		public void setShuilv4(String shuilv4) {
			this.shuilv4 = shuilv4;
		}

		public String getShuilv5() {
			return shuilv5;
		}

		public void setShuilv5(String shuilv5) {
			this.shuilv5 = shuilv5;
		}

		public String getShuilv6() {
			return shuilv6;
		}

		public void setShuilv6(String shuilv6) {
			this.shuilv6 = shuilv6;
		}

		public String getShuilv7() {
			return shuilv7;
		}

		public void setShuilv7(String shuilv7) {
			this.shuilv7 = shuilv7;
		}

		public String getKplx() {
			return kplx;
		}

		public void setKplx(String kplx) {
			this.kplx = kplx;
		}

		public String getKpxm() {
			return kpxm;
		}

		public void setKpxm(String kpxm) {
			this.kpxm = kpxm;
		}

		public String getXxzshsje() {
			return xxzshsje;
		}

		public void setXxzshsje(String xxzshsje) {
			this.xxzshsje = xxzshsje;
		}

		public String getXxfshsje() {
			return xxfshsje;
		}

		public void setXxfshsje(String xxfshsje) {
			this.xxfshsje = xxfshsje;
		}

		public String getXxzswsje() {
			return xxzswsje;
		}

		public void setXxzswsje(String xxzswsje) {
			this.xxzswsje = xxzswsje;
		}

		public String getXxfswsje() {
			return xxfswsje;
		}

		public void setXxfswsje(String xxfswsje) {
			this.xxfswsje = xxfswsje;
		}

		public String getXxzsse() {
			return xxzsse;
		}

		public void setXxzsse(String xxzsse) {
			this.xxzsse = xxzsse;
		}

		public String getXxfsse() {
			return xxfsse;
		}

		public void setXxfsse(String xxfsse) {
			this.xxfsse = xxfsse;
		}

		public String getXxhsjehj() {
			return xxhsjehj;
		}

		public void setXxhsjehj(String xxhsjehj) {
			this.xxhsjehj = xxhsjehj;
		}

		public String getXxbhsjehj() {
			return xxbhsjehj;
		}

		public void setXxbhsjehj(String xxbhsjehj) {
			this.xxbhsjehj = xxbhsjehj;
		}

		public String getXxsehj() {
			return xxsehj;
		}

		public void setXxsehj(String xxsehj) {
			this.xxsehj = xxsehj;
		}

		public String getXxzshsjehj() {
			return xxzshsjehj;
		}

		public void setXxzshsjehj(String xxzshsjehj) {
			this.xxzshsjehj = xxzshsjehj;
		}

		public String getXxfshsjehj() {
			return xxfshsjehj;
		}

		public void setXxfshsjehj(String xxfshsjehj) {
			this.xxfshsjehj = xxfshsjehj;
		}

		public String getXxzswsjehj() {
			return xxzswsjehj;
		}

		public void setXxzswsjehj(String xxzswsjehj) {
			this.xxzswsjehj = xxzswsjehj;
		}

		public String getXxfswsjehj() {
			return xxfswsjehj;
		}

		public void setXxfswsjehj(String xxfswsjehj) {
			this.xxfswsjehj = xxfswsjehj;
		}

		public String getXxfssehj() {
			return xxfssehj;
		}

		public void setXxfssehj(String xxfssehj) {
			this.xxfssehj = xxfssehj;
		}

		public String getXxzssehj() {
			return xxzssehj;
		}

		public void setXxzssehj(String xxzssehj) {
			this.xxzssehj = xxzssehj;
		}
	
	
	
	
}
