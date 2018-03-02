package com.liqun.util;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.liqun.Exception.DataillegalityException;
import com.liqun.Exception.NotEmptyException;
import com.liqun.dao.IBilldelMapper;
import com.liqun.dto.FpkjRequest.COMMON_FPKJ_FPT;
import com.liqun.dto.FpkjRequest.COMMON_FPKJ_XMXX;
import com.liqun.dto.FpkjRequest.COMMON_FPKJ_XMXXS;
import com.liqun.entity.IBilldel;
import com.liqun.entity.IBillmain;

public class FPKJUtils {
	@Autowired
	private IBilldelMapper iBilldelMapper;
	
	//COMMON_FPKJ_FPT
		public  COMMON_FPKJ_FPT getCOMMON_FPKJ_FPT(IBillmain iBillmain, COMMON_FPKJ_FPT common_FPKJ_FPT, Object[] obj)throws DataillegalityException,NotEmptyException{
			common_FPKJ_FPT.setClasses("COMMON_FPKJ_FPT");
			String fplsh = iBillmain.getFplsh();
			if(fplsh.length()== 20) {
				if(!"".equals(fplsh)) {
					common_FPKJ_FPT.setFPQQLSH(fplsh);//发票请求流水号
				}else {
					throw new NotEmptyException("发票请求流水号不能为空!");
				}
				
			}else {
				throw new DataillegalityException("发票请求流水号长度不合法!");
			}
			String fpzl = iBillmain.getFpzl()+"";
			if(fpzl.length()==1) {
				if("0".equals(fpzl)) {
					common_FPKJ_FPT.setXSF_NSRSBH("0");//开票类型:0:蓝票 1:红票
				}else {
					common_FPKJ_FPT.setXSF_NSRSBH("1");//开票类型
				}
			}else {
				throw new DataillegalityException("开票类型的长度不合法");
			}
			String xfmc = iBillmain.getXfmc();
			if(xfmc.length()==20) {
				if(!"".equals(xfmc)&& xfmc !=null) {
					common_FPKJ_FPT.setXSF_MC(xfmc);//销售方名称
				}else {
					throw new NotEmptyException("销售方名称不能为空");
				}
			}else {
				throw new DataillegalityException("销售方名称长度不合法");
						
			}
			
			
			String xfdzdh = iBillmain.getXfdzdh();
			if(xfdzdh.length()==100) {
				if(!"".equals(xfdzdh)&& xfdzdh !=null) {
					common_FPKJ_FPT.setXSF_DZDH(xfdzdh);//销售方地址、电话
				}else {
					throw new NotEmptyException("销售方地址,电话不能为空");
				}
			}else {
				throw new NotEmptyException("销售方地址,电话长度不合法");
			}
			
			common_FPKJ_FPT.setXSF_YHZH(iBillmain.getXfyhzh());//销售方银行账号</XSF_YHZH>
			
			common_FPKJ_FPT.setGMF_NSRSBH("");//购买方纳税人识别号</GMF_NSRSBH>
			
			String gfmc = iBillmain.getGfmc();
			if(gfmc.length()==100) {
				if(!"".equals(gfmc)&& gfmc !=null) {
					common_FPKJ_FPT.setGMF_MC(gfmc);//购买方名称</GMF_MC>
				}else {
					throw new NotEmptyException("购买方名称不能为空");
				}
			}else {
				throw new DataillegalityException("购买方名称长度不合法");
			}
			
			
			
			common_FPKJ_FPT.setGMF_DZDH(iBillmain.getGfdzdh());//;购买方地址、电话</GMF_DZDH>
			common_FPKJ_FPT.setGMF_YHZH(iBillmain.getGfyhzh());//购买方银行账号</GMF_YHZH>
			String kpr = iBillmain.getKpr();
			if(kpr.length()==8) {
				if(!"".equals(kpr)&& kpr != null) {
					common_FPKJ_FPT.setKPR("");//开票人</KPR>
				}else {
					throw new NotEmptyException("开票人不能为空");
				}
			}else {
				throw new DataillegalityException("开票人长度不合法");

			}
			String skr = iBillmain.getSkr();
			if(skr.length()==8) {
				common_FPKJ_FPT.setSKR(skr);//收款人</SKR>
			}else {
				throw new DataillegalityException("收款人长度不合法");
			}
			String fhr = iBillmain.getFhr();
			if(fhr.length()==8) {
				common_FPKJ_FPT.setFHR(fhr);//复核人</FHR>
			}else {
				throw new DataillegalityException("复核人人长度不合法");
			}
		
			if("0".equals(fpzl+"")) {
				common_FPKJ_FPT.setYFP_DM("");//原发票代码</YFP_DM>
				common_FPKJ_FPT.setYFP_HM("");//原发票号码</YFP_HM>
			}else {
				String yfpdm = iBillmain.getYfpdm();
				String yfphm = iBillmain.getYfphm();
				if(yfpdm.length()==12) {
					common_FPKJ_FPT.setYFP_DM(yfpdm);//原发票代码</YFP_DM>
				}else {
					throw new DataillegalityException("原发票代码长度不合法");
				}
				if(yfpdm.length()==8) {
					common_FPKJ_FPT.setYFP_HM(iBillmain.getYfphm());//原发票号码</YFP_HM>
				}else {
					throw new DataillegalityException("原发票号码长度不合法");
				}
				
				
			}
			//0 获取折扣金额,1:折扣税额,2折扣含税金额,3获取折扣后含税金额,4折扣后金额,5折扣后税额
			
			common_FPKJ_FPT.setJSHJ("");//价税合计</JSHJ>
			
			String hjje = obj[4]+"";
			if(("2".equals(hjje.length() - hjje.indexOf(".") - 1 + ""))) {
				common_FPKJ_FPT.setHJJE(hjje);//合计金额</HJJE>
			}else {
				throw new DataillegalityException("你输入的金额的格式不正确");
			}
			
			String hjse = obj[5]+"";
			if(("2".equals(hjse.length() - hjse.indexOf(".") - 1 + ""))) {
				common_FPKJ_FPT.setHJSE(hjse);//合计金额</HJJE>
			}else {
				throw new DataillegalityException("你输入的税额的格式不正确");
			}
			if(("2".equals(hjse.length() - hjse.indexOf(".") - 1 + ""))) {
				common_FPKJ_FPT.setHJSE(hjse);//合计税额</HJSE>
			}else {
				
			}
			String sj = iBillmain.getSj();
			
		    common_FPKJ_FPT.setGHF_SJ(sj);//购货方手机</GHF_SJ>
			
		  
			common_FPKJ_FPT.setGHF_EMAIL(iBillmain.getEmail());//购货方邮箱</GHF_EMAIL>
			common_FPKJ_FPT.setBZ(iBillmain.getBz());//备注</BZ>
			return common_FPKJ_FPT;
		}
		
		//获取商品清单的
		public COMMON_FPKJ_XMXXS getFpkjXmxx(List<IBilldel> iBilldelList, COMMON_FPKJ_XMXX common_FPKJ_XMXX) throws DataillegalityException{
			COMMON_FPKJ_XMXXS common_FPKJ_XMXXS = new COMMON_FPKJ_XMXXS();
			Integer size = 1;
			for (IBilldel iBilldel : iBilldelList) {
				size ++;
				//获取折扣金额
				BigDecimal zkhje = iBilldel.getZkhje();
				if((zkhje+"").length() == 1) {
					if(zkhje.compareTo(BigDecimal.ZERO)<0) {
						//折扣行
						common_FPKJ_XMXX.setFPHXZ("");//发票行性质</FPHXZ>
					}else if(zkhje.compareTo(BigDecimal.ZERO)== 0) {
						//正常行
						common_FPKJ_XMXX.setFPHXZ("");//发票行性质</FPHXZ>
					}
				}else {
					throw new DataillegalityException("你输入的折扣行的长度不符合要求");
				}
				String xmmc = iBilldel.getXmmc();
				if((xmmc.length())== 90 && !"".equals(xmmc)&&xmmc!=null) {
					common_FPKJ_XMXX.setXMMC(xmmc);//项目名称</XMMC>
				}
				String ggxh = iBilldel.getGgxh();
				if((ggxh.length())<= 40) {
					common_FPKJ_XMXX.setGGXH(ggxh);//规格型号</GGXH>
				}else {
					throw new DataillegalityException("你输入的规格型号的长度不能超过40个字符!");
				}
				
				String jldw = iBilldel.getJldw();
				if((jldw.length())<= 20) {
					common_FPKJ_XMXX.setDW(jldw);//单位</DW>
				}else {
					throw new DataillegalityException("输入的单位的长度不能超过20个字符!");
				}
				String sl = iBilldel.getXmsl()+"";
				if("6".equals(sl.length() - sl.indexOf(".") - 1 + "")) {
					common_FPKJ_XMXX.setXMSL(sl);//项目数量</XMSL>
				}else {
					throw new DataillegalityException("你输入的数量的格式不合法!");
				}
				String xmdj = iBilldel.getXmdj()+"";
				if("6".equals(xmdj.length() - xmdj.indexOf(".") - 1 + "")) {
					common_FPKJ_XMXX.setXMDJ(xmdj);;//项目数量</XMSL>
				}else {
					throw new DataillegalityException("你输入的数量的格式不合法!");
				}
				//不含税金额
				String xmje = iBilldel.getXmje()+"";
				if("2".equals(xmje.length() - xmje.indexOf(".") - 1 + "")) {
					common_FPKJ_XMXX.setXMJE(xmje);//项目金额</XMJE>
				}else {
					throw new DataillegalityException("你输入的金额的格式不合法");
				}
				String slv = iBilldel.getSl()+"";
				if("2".equals(slv.length() - slv.indexOf(".") - 1 + "")) {
					common_FPKJ_XMXX.setSL(slv);//税率</SL>
				}else if(slv.contains("%")||(!"2".equals(slv.length() - slv.indexOf(".") - 1 + ""))){
					throw new DataillegalityException("你输入的税率的格式不合法!");
				}
				String se = iBilldel.getSe()+"";
				if("2".equals(se.length() - se.indexOf(".") - 1 + "")) {
					common_FPKJ_XMXX.setSE(se);//税额</SE>
				}else{
					throw new DataillegalityException("你输入的税额的格式不合法!");
				}
				String spbm = iBilldel.getSpbm()+"";
				if(!"".equals(spbm)&& spbm!=null && "19".equals(se.length() - se.indexOf(".") - 1 + "" )) {
					common_FPKJ_XMXX.setSPBM(spbm);;//商品编码</SPBM>
				}else {
					throw new DataillegalityException("你输入的商品编码的格式不正确!");
				}
				
				common_FPKJ_XMXX.setZXBM(iBilldel.getSpbh());//自行编码</ZXBM>
				String sfyhzc = iBilldel.getSfyhzc()+"";
				if(sfyhzc.length()==1 && !"".equals(sfyhzc)){
					common_FPKJ_XMXX.setYHZCBS(iBilldel.getSfyhzc()+"");//优惠政策标识<YHZCBS>
				}else {
					throw new DataillegalityException("你输入的优惠政策标识的格式不正确!");
				}
				
				String lslbs = iBilldel.getLslbs();
				if(lslbs.length()==1) {
					common_FPKJ_XMXX.setLSLBS(lslbs);//零税率标识<LSLBS>
				}else {
					throw new DataillegalityException("你输入的优惠政策标识的格式不正确!");
				}
				
				if("1".equals(common_FPKJ_XMXX.getYHZCBS())) {
					common_FPKJ_XMXX.setZZSTSGL("");//增值税特殊管理</ZZSTSGL>
				}else {
					common_FPKJ_XMXX.setZZSTSGL("");//增值税特殊管理</ZZSTSGL>
				}
				
				// 发票开具,没有清单的
				iBilldelMapper.insertSelective(iBilldel);
			}
			common_FPKJ_XMXXS.setCommonFPJKXMXX(common_FPKJ_XMXX);
			common_FPKJ_XMXXS.setSize(size+"");
			common_FPKJ_XMXXS.setClasses("COMMON_FPKJ_XMXX");
			return common_FPKJ_XMXXS;
		}
		
		/*
		 * 获取折扣金额,折扣税额,折扣含税金额,获取折扣后含税金额,折扣后金额,折扣后税额
		 */
		public Object[] getCount(List<IBilldel> iBilldelList) {
			
			BigDecimal zkhsje =  new BigDecimal(0); ;//折扣含税金额
			
			BigDecimal zkje =  new BigDecimal(0); ;//折扣金额
			
			BigDecimal zkse =  new BigDecimal(0); ;//折扣税额
			
			BigDecimal zkhhsje =  new BigDecimal(0); ;//折扣后含税金额
			
			BigDecimal zkhje =  new BigDecimal(0); ;//折扣后 金额(不含税)
			
			BigDecimal zkhse =  new BigDecimal(0); ;//折扣后税额
			BigDecimal zkhbhsse =  new BigDecimal(0); ;//折扣后不含税税额
			
			
			
			for (IBilldel iBilldel : iBilldelList) {
				
					zkhsje = zkhsje.add(iBilldel.getZkhsje());
					zkje = zkje.add(iBilldel.getZkje());
					zkse = zkse.add(iBilldel.getZkse());
					zkhhsje = zkhhsje.add(iBilldel.getZkhhsje());
					zkhje = zkhje.add(iBilldel.getZkhje());
					zkhse = zkhse.add(iBilldel.getZkhhsje());
					
					
			}
			Object []obj= {zkhsje,zkje,zkse,zkhhsje,zkhje,zkhse};
			return obj;
			
		}
		
		

}
