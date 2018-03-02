package com.liqun.dto.a9input;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@SuppressWarnings("all")
@XmlRootElement(name="FP")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fp implements Serializable{
	
	private String blueFpdm;//蓝字发票代码（红票必填)
	
	
	private String blueFphm;//蓝字发票代码（红票必填)
	
	
	private String bsbz;//报税标志(默认0）

	
	private String bsq;//报税期（默认0）
	
	
	private String bszt;//报送状态（默认0）
	
	
	private String bz;//备注（base64）
	
	
	private String ccdw;//车船吨位（默认0）
	
	private String cd;//产地（机动车发票，空）
	
	
	private String cllx;//购方地址电话（机动车发票，空）
	
	
	private String clsbdh;//车辆识别代号（机动车发票，空）
	
	
	private String cpxh;//厂牌型号（机动车发票，空）
	

	private String cyrmc;
	
	
	private String cyrnsrsbh;
	
	
	private String czch;
	
	
	private String dkqymc;
	

	private String dkqysh;
	
	
	private String dw;//吨位（机动车发票，空）
	
	
	private String dybz;//打印标志（默认0）
	
	
	private String dymb;//打印模板（卷票，空）
	
	
	private String dzsyh;//地址索引号（空）
	
	private String fdjhm;//发动机号码（机动车发票，空）
	
	
	private String fhr;//复核人（可为空）
	
	
	private String fhrmc;//发货人名称（空)
	
	
	private String fhrnsrsbh;//发货人纳税人识别号（空）
	
	
	private String fpdm;//发票代码（空）
	
	
	private String fphm;//发票代码（空）
	
	
	private String fplx;//发票类型	s，专用发票 c,普通发票 q,卷票p,电子发票
	
	
	private String gfbh;//购方编号（可为空）
	
	
	private String gfdzdh;//购方地址电话（可为空）
	
	
	private String gfmc;//购方名称（必填）
	
	
	private String gfsh;//购方税号（可为空）
	
	
	private String gfyhzh;//购方银行账号（可为空）
	
	
	private String hgzh;//合格证号（机动车发票，空）
	
	
	private String hxm;//汉信码-专票和普票的汉字防伪发票（空）
	
	
	private String hzfw;//汉信码标志（默认0）
	
	
	private String invQryNo;//索引号（默认0）
	
	
	private String isBlankWaste;//空白作废发票（0不是空白作废，1空白作废）
	
	
	private String isNewJdcfp;//1表示旧版机动车，2表示新版机动车（机动车发票）
	
	
	private String isRed;//发票标志，0蓝字发票，1红字发票
	
	
	private String isSqd;//清单标志，0无清单，1有清单
	
	
	private String je;//合计金额（不含税，2位小数，必填）
	
	
	private String jkzmsh;//进口证明书号（机动车发票，空	）
	
	
	private String jmbbh;//加密版本号（空）
	
	
	private String jqbh;//机器编号（必填）
	
	
	private String jsk_fpmx_version;//底层串编号（默认2）
	
	
	private String jym;//校验码（空）
	
	
	private String keyFlagNo;
	
	
	private String kpjh;//开票机号（必填）
	
	
	private String kpr;//开票人（可为空）
	
	private String kprq;//开票日期（空）
	
	
	private String kpsxh;//开票顺序号（可以为空）
	
	
	private String mw;//密文（空）
	
	
	private String mxhjje;//明细合计金额（空）
	
	
	private String qyd;//起运地（空）
	
	
	private String rdmByte;
	
	
	private String redNum;//红字通知单号（红字专票发票必填）
	
	
	private String retCode;//开票返回值（空）
	
	
	private String sccjmc;//生产厂家名称（空）
	
	
	private String se;//合计税额（2位小数，必填）
	
	
	private String sfzhm; //身份证号码（空）
	
	
	private String shrmc;//收货人名称（空）
	
	
	private String shrnsrsbh;//收货人纳税人识别号（空）
	
	
	private String sign;//发票签名（空）
	
	
	private String sjdh;//商检单号（机动车发票，空）
	
	
	private String skr;//收款人（#%之前是1代表优惠，0代表不优惠）
	
	
	private String sLv;//发票票面税率（必填）
	
	
	private String spfmc;//受票方名称（空）
	
	
	private String spfnsrsbh;//受票方纳税人识别号（空）
	
	
	private String ssyf;//所属月份
	
	
	
    @XmlElement(name="value")
    @XmlElementWrapper(name="TAX_CLASS")
	private  List<TAX_CLASS>  tax_class;
	
	

	

	public List<TAX_CLASS> getTax_class() {
		return tax_class;
	}

	public void setTax_class(List<TAX_CLASS> tax_class) {
		this.tax_class = tax_class;
	}

	private String wspzhm;//完税凭证号码（空）
	
	
	private String xcrs;//限乘人数（机动车发票，空）
	
	
	private String xfbz;//修复标志（空）
	
	
	private String xfdh;//销方单号（空）
	
	
	private String xfdz;//销方地址（空）
	
	
	private String xfdzdh;//销方地址电话（必填）
	
	
	private String xfmc;//销方名称（必填）
	
	
	private String xfsh;//销方税号（必填）
	
	
	private String xfyh;//销方银行（空）
	
	
	private String xfyhzh;//销方银行账号（必填）
	
	
	private String xfzh;//销方账号（空）
	
	
	private String xsdjbh;//销售单据编号（必填）
	
	
	private String yshwxx;//
	
	
	private String yysbz;//营业税标志（空）
	
	
	private String zfbz;//作废标志（开票是传入0）
	
	
	private String zfsj;//作废时间（空）
	
	
	private String zgswjgdm;//主管税务机关代码（空）
	
	
	private String zgswjgmc;//主管税务机关名称（空）
	
	
	private String zyfplx;/*主要发票类型
	0，标准发票
	1，海洋石油发票
	2，石脑油
	3，石脑油定点直供
	4，燃料油
	6，稀土原材料
	7，稀土产成品
	8，农产品销售
	9，农产品收购
	10, 5%减按1.5%征收
	11，差额征税*/ 
	
	
	private String zyspmc;//主要商品名称（必填）
	
	
	private String zyspsm;//主要商品税目
	
	
	private String bmbbbh;//分类编码版本编号（必填）
	
	
	private String Bh;
	
	
	private String RalateDataBh;
	
	
	private String DefaultDataBh;
	
	
	@XmlElementWrapper(name = "Mxxx")
	@XmlElement(name="spxx",required=true)
	private List<Spxx> spxxm;
	
	@XmlElementWrapper(name = "Qdxx")
	@XmlElement(name="spxx",required=true)
	private List<Spxx> spxxs;
	
	

	public String getBlueFpdm() {
		return blueFpdm;
	}

	public void setBlueFpdm(String blueFpdm) {
		this.blueFpdm = blueFpdm;
	}

	public String getBlueFphm() {
		return blueFphm;
	}

	public void setBlueFphm(String blueFphm) {
		this.blueFphm = blueFphm;
	}

	public String getBsbz() {
		return bsbz;
	}

	public void setBsbz(String bsbz) {
		this.bsbz = bsbz;
	}

	public String getBsq() {
		return bsq;
	}

	public void setBsq(String bsq) {
		this.bsq = bsq;
	}

	public String getBszt() {
		return bszt;
	}

	public void setBszt(String bszt) {
		this.bszt = bszt;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getCcdw() {
		return ccdw;
	}

	public void setCcdw(String ccdw) {
		this.ccdw = ccdw;
	}

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public String getCllx() {
		return cllx;
	}

	public void setCllx(String cllx) {
		this.cllx = cllx;
	}

	public String getClsbdh() {
		return clsbdh;
	}

	public void setClsbdh(String clsbdh) {
		this.clsbdh = clsbdh;
	}

	public String getCpxh() {
		return cpxh;
	}

	public void setCpxh(String cpxh) {
		this.cpxh = cpxh;
	}

	public String getCyrmc() {
		return cyrmc;
	}

	public void setCyrmc(String cyrmc) {
		this.cyrmc = cyrmc;
	}

	

	public String getCyrnsrsbh() {
		return cyrnsrsbh;
	}

	public void setCyrnsrsbh(String cyrnsrsbh) {
		this.cyrnsrsbh = cyrnsrsbh;
	}

	public String getCzch() {
		return czch;
	}

	public void setCzch(String czch) {
		this.czch = czch;
	}

	public String getDkqymc() {
		return dkqymc;
	}

	public void setDkqymc(String dkqymc) {
		this.dkqymc = dkqymc;
	}

	public String getDkqysh() {
		return dkqysh;
	}

	public void setDkqysh(String dkqysh) {
		this.dkqysh = dkqysh;
	}

	public String getDw() {
		return dw;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}

	public String getDybz() {
		return dybz;
	}

	public void setDybz(String dybz) {
		this.dybz = dybz;
	}

	public String getDymb() {
		return dymb;
	}

	public void setDymb(String dymb) {
		this.dymb = dymb;
	}

	public String getDzsyh() {
		return dzsyh;
	}

	public void setDzsyh(String dzsyh) {
		this.dzsyh = dzsyh;
	}

	public String getFdjhm() {
		return fdjhm;
	}

	public void setFdjhm(String fdjhm) {
		this.fdjhm = fdjhm;
	}

	public String getFhr() {
		return fhr;
	}

	public void setFhr(String fhr) {
		this.fhr = fhr;
	}

	public String getFhrmc() {
		return fhrmc;
	}

	public void setFhrmc(String fhrmc) {
		this.fhrmc = fhrmc;
	}

	public String getFhrnsrsbh() {
		return fhrnsrsbh;
	}

	public void setFhrnsrsbh(String fhrnsrsbh) {
		this.fhrnsrsbh = fhrnsrsbh;
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

	public String getFplx() {
		return fplx;
	}

	public void setFplx(String fplx) {
		this.fplx = fplx;
	}

	public String getGfbh() {
		return gfbh;
	}

	public void setGfbh(String gfbh) {
		this.gfbh = gfbh;
	}

	public String getGfdzdh() {
		return gfdzdh;
	}

	public void setGfdzdh(String gfdzdh) {
		this.gfdzdh = gfdzdh;
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

	public String getGfyhzh() {
		return gfyhzh;
	}

	public void setGfyhzh(String gfyhzh) {
		this.gfyhzh = gfyhzh;
	}

	public String getHgzh() {
		return hgzh;
	}

	public void setHgzh(String hgzh) {
		this.hgzh = hgzh;
	}

	public String getHxm() {
		return hxm;
	}

	public void setHxm(String hxm) {
		this.hxm = hxm;
	}

	public String getHzfw() {
		return hzfw;
	}

	public void setHzfw(String hzfw) {
		this.hzfw = hzfw;
	}

	public String getInvQryNo() {
		return invQryNo;
	}

	public void setInvQryNo(String invQryNo) {
		this.invQryNo = invQryNo;
	}

	public String getIsBlankWaste() {
		return isBlankWaste;
	}

	public void setIsBlankWaste(String isBlankWaste) {
		this.isBlankWaste = isBlankWaste;
	}

	public String getIsNewJdcfp() {
		return isNewJdcfp;
	}

	public void setIsNewJdcfp(String isNewJdcfp) {
		this.isNewJdcfp = isNewJdcfp;
	}

	public String getIsRed() {
		return isRed;
	}

	public void setIsRed(String isRed) {
		this.isRed = isRed;
	}

	public String getIsSqd() {
		return isSqd;
	}

	public void setIsSqd(String isSqd) {
		this.isSqd = isSqd;
	}

	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}

	public String getJkzmsh() {
		return jkzmsh;
	}

	public void setJkzmsh(String jkzmsh) {
		this.jkzmsh = jkzmsh;
	}

	public String getJmbbh() {
		return jmbbh;
	}

	public void setJmbbh(String jmbbh) {
		this.jmbbh = jmbbh;
	}

	public String getJqbh() {
		return jqbh;
	}

	public void setJqbh(String jqbh) {
		this.jqbh = jqbh;
	}

	public String getJsk_fpmx_version() {
		return jsk_fpmx_version;
	}

	public void setJsk_fpmx_version(String jsk_fpmx_version) {
		this.jsk_fpmx_version = jsk_fpmx_version;
	}

	public String getJym() {
		return jym;
	}

	public void setJym(String jym) {
		this.jym = jym;
	}

	

	public String getKeyFlagNo() {
		return keyFlagNo;
	}

	public void setKeyFlagNo(String keyFlagNo) {
		this.keyFlagNo = keyFlagNo;
	}


	public String getKpjh() {
		return kpjh;
	}

	public void setKpjh(String kpjh) {
		this.kpjh = kpjh;
	}

	public String getKpr() {
		return kpr;
	}

	public void setKpr(String kpr) {
		this.kpr = kpr;
	}

	public String getKprq() {
		return kprq;
	}

	public void setKprq(String kprq) {
		this.kprq = kprq;
	}

	public String getKpsxh() {
		return kpsxh;
	}

	public void setKpsxh(String kpsxh) {
		this.kpsxh = kpsxh;
	}

	public String getMw() {
		return mw;
	}

	public void setMw(String mw) {
		this.mw = mw;
	}

	public String getMxhjje() {
		return mxhjje;
	}

	public void setMxhjje(String mxhjje) {
		this.mxhjje = mxhjje;
	}

	public String getQyd() {
		return qyd;
	}

	public void setQyd(String qyd) {
		this.qyd = qyd;
	}

	public String getRdmByte() {
		return rdmByte;
	}

	public void setRdmByte(String rdmByte) {
		this.rdmByte = rdmByte;
	}

	public String getRedNum() {
		return redNum;
	}

	public void setRedNum(String redNum) {
		this.redNum = redNum;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getSccjmc() {
		return sccjmc;
	}

	public void setSccjmc(String sccjmc) {
		this.sccjmc = sccjmc;
	}

	public String getSe() {
		return se;
	}

	public void setSe(String se) {
		this.se = se;
	}

	public String getSfzhm() {
		return sfzhm;
	}

	public void setSfzhm(String sfzhm) {
		this.sfzhm = sfzhm;
	}

	public String getShrmc() {
		return shrmc;
	}

	public void setShrmc(String shrmc) {
		this.shrmc = shrmc;
	}

	public String getShrnsrsbh() {
		return shrnsrsbh;
	}

	public void setShrnsrsbh(String shrnsrsbh) {
		this.shrnsrsbh = shrnsrsbh;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSjdh() {
		return sjdh;
	}

	public void setSjdh(String sjdh) {
		this.sjdh = sjdh;
	}

	public String getSkr() {
		return skr;
	}

	public void setSkr(String skr) {
		this.skr = skr;
	}

	public String getsLv() {
		return sLv;
	}

	public void setsLv(String sLv) {
		this.sLv = sLv;
	}

	public String getSpfmc() {
		return spfmc;
	}

	public void setSpfmc(String spfmc) {
		this.spfmc = spfmc;
	}

	public String getSpfnsrsbh() {
		return spfnsrsbh;
	}

	public void setSpfnsrsbh(String spfnsrsbh) {
		this.spfnsrsbh = spfnsrsbh;
	}

	public String getSsyf() {
		return ssyf;
	}

	public void setSsyf(String ssyf) {
		this.ssyf = ssyf;
	}

	public String getWspzhm() {
		return wspzhm;
	}

	public void setWspzhm(String wspzhm) {
		this.wspzhm = wspzhm;
	}

	public String getXcrs() {
		return xcrs;
	}

	public void setXcrs(String xcrs) {
		this.xcrs = xcrs;
	}

	public String getXfbz() {
		return xfbz;
	}

	public void setXfbz(String xfbz) {
		this.xfbz = xfbz;
	}

	public String getXfdh() {
		return xfdh;
	}

	public void setXfdh(String xfdh) {
		this.xfdh = xfdh;
	}

	public String getXfdz() {
		return xfdz;
	}

	public void setXfdz(String xfdz) {
		this.xfdz = xfdz;
	}

	public String getXfdzdh() {
		return xfdzdh;
	}

	public void setXfdzdh(String xfdzdh) {
		this.xfdzdh = xfdzdh;
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

	public String getXfyh() {
		return xfyh;
	}

	public void setXfyh(String xfyh) {
		this.xfyh = xfyh;
	}

	public String getXfyhzh() {
		return xfyhzh;
	}

	public void setXfyhzh(String xfyhzh) {
		this.xfyhzh = xfyhzh;
	}

	public String getXfzh() {
		return xfzh;
	}

	public void setXfzh(String xfzh) {
		this.xfzh = xfzh;
	}

	public String getXsdjbh() {
		return xsdjbh;
	}

	public void setXsdjbh(String xsdjbh) {
		this.xsdjbh = xsdjbh;
	}

	public String getYshwxx() {
		return yshwxx;
	}

	public void setYshwxx(String yshwxx) {
		this.yshwxx = yshwxx;
	}

	public String getYysbz() {
		return yysbz;
	}

	public void setYysbz(String yysbz) {
		this.yysbz = yysbz;
	}

	public String getZfbz() {
		return zfbz;
	}

	public void setZfbz(String zfbz) {
		this.zfbz = zfbz;
	}

	public String getZfsj() {
		return zfsj;
	}

	public void setZfsj(String zfsj) {
		this.zfsj = zfsj;
	}

	public String getZgswjgdm() {
		return zgswjgdm;
	}

	public void setZgswjgdm(String zgswjgdm) {
		this.zgswjgdm = zgswjgdm;
	}

	public String getZgswjgmc() {
		return zgswjgmc;
	}

	public void setZgswjgmc(String zgswjgmc) {
		this.zgswjgmc = zgswjgmc;
	}

	public String getZyfplx() {
		return zyfplx;
	}

	public void setZyfplx(String zyfplx) {
		this.zyfplx = zyfplx;
	}

	public String getZyspmc() {
		return zyspmc;
	}

	public void setZyspmc(String zyspmc) {
		this.zyspmc = zyspmc;
	}

	public String getZyspsm() {
		return zyspsm;
	}

	public void setZyspsm(String zyspsm) {
		this.zyspsm = zyspsm;
	}

	public String getBmbbbh() {
		return bmbbbh;
	}

	public void setBmbbbh(String bmbbbh) {
		this.bmbbbh = bmbbbh;
	}

	public String getBh() {
		return Bh;
	}

	public void setBh(String bh) {
		Bh = bh;
	}

	public String getRalateDataBh() {
		return RalateDataBh;
	}

	public void setRalateDataBh(String ralateDataBh) {
		RalateDataBh = ralateDataBh;
	}

	public String getDefaultDataBh() {
		return DefaultDataBh;
	}

	public void setDefaultDataBh(String defaultDataBh) {
		DefaultDataBh = defaultDataBh;
	}

	public List<Spxx> getSpxxm() {
		return spxxm;
	}

	public void setSpxxm(List<Spxx> spxxm) {
		this.spxxm = spxxm;
	}

	public List<Spxx> getSpxxs() {
		return spxxs;
	}

	public void setSpxxs(List<Spxx> spxxs) {
		this.spxxs = spxxs;
	}

	 


	
	
	
	
}
