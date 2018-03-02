package com.liqun.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htxx.utils.TripleDESUtil;
import com.liqun.Exception.DataillegalityException;
import com.liqun.Exception.NotEmptyException;
import com.liqun.dao.IBilldelMapper;
import com.liqun.dao.IBillmainMapper;
import com.liqun.dao.IInvMapper;
import com.liqun.dto.FPKJInput;
import com.liqun.dto.FPXM;
import com.liqun.dto.FPXTInput;
import com.liqun.dto.Input;
import com.liqun.dto.FpkjRequest.COMMON_FPKJ_FPT;
import com.liqun.dto.FpkjRequest.COMMON_FPKJ_XMXX;
import com.liqun.dto.FpkjRequest.COMMON_FPKJ_XMXXS;
import com.liqun.dto.FpkjRequest.REQUEST_COMMON_FPKJ;
import com.liqun.dto.FpkjResponse.RESPONSE_COMMON_FPKJ;
import com.liqun.dto.a9FPKJOutput.DATA;
import com.liqun.dto.a9FPKJOutput.FpkjOutput;
import com.liqun.dto.a9FPKJOutput.OUTPUT;
import com.liqun.entity.IBilldel;
import com.liqun.entity.IBillmain;
import com.liqun.entity.IInv;
import com.liqun.util.AxisWebserviceUtils;
import com.liqun.util.FPKJUtils;
import com.liqun.util.JaxbUtil;  

  
/**
 * 用户开票单据存储
 * 
 * @author dyh
 *
 */
@Service
public class BillServiceImpl extends BillServiceBase {
	@Autowired
	private IBilldelMapper iBilldelMapper;
	@Autowired
	private IBillmainMapper iBillmainMapper;
	@Autowired
	private IInvMapper iInvMapper;

	public int addBill(String xml) throws UnsupportedEncodingException {
		FPXTInput FPXTInput = JaxbUtil.converyToJavaBean(xml, FPXTInput.class);
		byte[] data = TripleDESUtil.decode(FPXTInput.getData().replace(' ', '+').getBytes());
		String a = new String(data, "utf-8");
		FPKJInput FPKJInput = JaxbUtil.converyToJavaBean(a, FPKJInput.class);
		/* System.out.println(JaxbUtil.convertToXml(FPKJInput)); */
		Input input = FPKJInput.getInput();
		List<FPXM> FPXMXX = input.getFPXMXX();
		IBillmain iBillmain = getIBillMain(input);

		iBillmainMapper.insertSelective(iBillmain);
		List<IBilldel> iBilldelList = new ArrayList<IBilldel>();
		for (int i = 0; i < FPXMXX.size(); i++) {
			FPXM fpxm3 = FPXMXX.get(i);
			FPXM fpxm4 = null;
			IBilldel iBilldel3 = getFPXM(fpxm3, input);
			String fphxz = fpxm3.getFPHXZ();// 获得发票行性质
			if ("3".equals(fphxz)) {
				fpxm4 = FPXMXX.get(i + 1);
				BigDecimal zkje = new BigDecimal(fpxm4.getXMJE());
				BigDecimal zkhsje = new BigDecimal(fpxm4.getHSJE());
				iBilldel3.setZkje(zkje);// 折扣行金额对应折扣金额
				iBilldel3.setZkhsje(zkhsje);// 折扣行含税金额对应折扣后含税金额
				iBilldel3.setZkse(zkhsje.subtract(zkje));
				BigDecimal hsje = new BigDecimal(fpxm3.getHSJE());
				BigDecimal xmje = new BigDecimal(fpxm3.getXMJE());
				BigDecimal se = new BigDecimal(fpxm3.getSE());
				iBilldel3.setZkhhsje(hsje.add(zkhsje));
				iBilldel3.setZkhje(xmje.add(zkje));
				iBilldel3.setZkhse(se.add(zkhsje.subtract(zkje)));
				FPXMXX.remove(i + 1);
			}
			iBilldelList.add(iBilldel3);
		}
		for (int i = 0; i < iBilldelList.size(); i++) {
			IBilldel iBilldel = iBilldelList.get(i);
			iBilldel.setPfhh(i + 1);// 设置行号
			iBilldelMapper.insertSelective(iBilldel);
		}
		return 1;
	}
	/**
	 * 立即开票
	 * @throws Exception 
	 */
	public String immediatelyDrawBill(String xml) throws Exception {
		String xmlResp = "";
		try {
			IBillmain iBillmain = getWholeIBillmain(xml);
			iBillmainMapper.insertSelective(iBillmain);
			List<IBilldel> iBilldelList = getWholeIBilldelList(xml);
			//判断是否是同步
			Integer djlx = iBillmain.getDjlx();
			if ("1".equals(djlx + "")) {// 同步
				String con="";
				con="<?xml version=\"1.0\" encoding=\"gbk\"?>";
				con +="<business id=\"FPKJA9\" comment=\"发票开具\">";
				con+="<![CDATA[";
				COMMON_FPKJ_FPT common_FPKJ_FPT = new COMMON_FPKJ_FPT();
				
				
				Object[]obj=getCount(iBilldelList);
				common_FPKJ_FPT = getCOMMON_FPKJ_FPT(iBillmain,common_FPKJ_FPT,obj);
				COMMON_FPKJ_XMXXS common_FPKJ_XMXXS = new COMMON_FPKJ_XMXXS();
			
				//清单明细不可以超过100
				if(iBilldelList.size() <= 100) {
					COMMON_FPKJ_XMXX common_FPKJ_XMXX = new COMMON_FPKJ_XMXX();
					common_FPKJ_XMXXS =getFpkjXmxx(iBilldelList,common_FPKJ_XMXX,iBillmain);
				}
				REQUEST_COMMON_FPKJ request_COMMON_FPKJ = new REQUEST_COMMON_FPKJ();
				request_COMMON_FPKJ.setClasss("REQUEST_COMMON_FPKJ");
				request_COMMON_FPKJ.setCommonFPKJFPT(common_FPKJ_FPT);
				request_COMMON_FPKJ.setCommonFPKJXMXXS(common_FPKJ_XMXXS);
				String convertToXml = JaxbUtil.convertToXml(request_COMMON_FPKJ);
				int indexOf = xml.indexOf("k");
				int lastIndexOf = xml.lastIndexOf("]");
				String s = xml.substring(indexOf+4);
				/*int indexOf = xml.indexOf("T");
				int lastIndexOf = xml.lastIndexOf("]");
				String s = xml.substring(indexOf+3, lastIndexOf-1);*/
				System.out.println(s);
				con+=s+"]]>";
				con+="</business>";
				System.out.println(con);
				//WebService调用接口
				String axis = AxisWebserviceUtils.getAxis(con);
				//解析webservice返回的报文成bean对象，获取数据存储数据库	
				IInv inv =getInv(axis,iBillmain,iBilldelList,obj);
				iInvMapper.insert(inv);
				//应答数据
				String result = getResult(iBillmain,inv,obj);
				return result;
			}
		}catch(DataillegalityException ex) {
			ex.printStackTrace();
		}catch(NotEmptyException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	/**
	 * 返回客户端数据
	 * 
	 * @throws Exception
	 */
	
	public String getResult(IBillmain iBillmain,IInv inv,Object[]obj) throws Exception {
		/*<?xml version="1.0" encoding="GBK"?>
		<FPXT_OUTPUT>
		<ID></ID><!--业务标识-->
		<CODE></CODE><!--结果代码： 000000 成功，否则失败-->
		<MESS></MESS><!--描述信息-->
		<DATA></DATA><!--返回业务报文，经过 Base64 编码 -->
		</FPXT_OUTPUT>*/
		String result="";
		

		
		/*<?xml version="1.0" encoding="GBK"?>
		<FPKJOUTPUT>
		<OUTPUT>
		<RETURN>
		<CODE></CODE><!--结果代码： 000000 成功，否则失败-->
		<MESS></MESS><!--描述信息-->
		</RETURN>
		<DATA>
		<KPZDH>开票终端号</KPZDH>
		<FPLSH>发票流水号</FPLSH>
		<FPTQM>发票提取码</FPTQM>
		<KPMS>开票模式</KPMS>
		<FPDM>发票代码</FPDM>
		<FPHM>发票号码</FPHM>
		<KPSJ>开票时间</KPSJ>
		<JSHJ>价税合计</JSHJ>
		<HJJE>合计金额(不含税)</HJJE>
		<HJSE>合计税额</HJSE>
		<BZ>备注</BZ>
		</DATA>
		</OUTPUT>
		</FPKJOUTPUT>*/
		
		DATA data = new DATA();
		data.setKPZDH(iBillmain.getKpzdh());
		data.setFPLSH(iBillmain.getFplsh());
		data.setFPTQM(iBillmain.getFptqm());
		data.setKPMS(iBillmain.getDjlx()+"");
		data.setFPDM(inv.getFpdm());
		data.setFPHM(inv.getFphm());
		data.setKPSJ(inv.getKpsj()+"");
		//0:获取折扣金额,1:折扣税额,2:折扣含税金额,3:获取折扣后含税金额,4折扣后金额,5:折扣后税额Object []obj= {zkhsje,zkje,zkse,zkhhsje,zkhje,zkhse};
		data.setJSHJ(inv.getJshj()+"");
		data.setHJJE(inv.getHjje()+"");
		data.setHJSE(inv.getHjse()+"");
		data.setBZ(inv.getBz());
		
		OUTPUT out = new OUTPUT();
		out.setData(data);
		FpkjOutput fpkjOutput = new FpkjOutput();
		fpkjOutput.setOutput(out);
		String xml = JaxbUtil.convertToXml(fpkjOutput);
		
		String xmls = "";
		xmls = xmls + "<?xml version=\"1.0\" encoding=\"GBK\"?>";
		xmls = xmls + "<FPXT_OUTPUT>";
		xmls = xmls + "<ID>" + "0103" + "</ID>";
		xmls = xmls + "<code>"+"000000"+"</code>";
		xmls = xmls + "<mess>"+"成功!"+"</mess>";
		xmls = xmls + "<DATA>" + new String(TripleDESUtil.encode(xml.getBytes("GBK")), "GBK") + "</DATA>";
		xmls = xmls + "</FPXT_OUTPUT>";
		return xmls;
	}
	//把iBillmain  webservice调用接口的数据封装到inv  
	private IInv getInv(String axis,IBillmain iBillmain,List<IBilldel> iBilldelList,Object[] count) throws UnsupportedEncodingException, ParseException {
		int indexOf = axis.indexOf("T");
		int lastIndexOf = axis.lastIndexOf("]");
		String news = axis.substring(indexOf+6,lastIndexOf-1).trim();
		String con="<?xml version='1.0' encoding='gbk'?>";
		con+=news;
		String s = new String(con.getBytes("GBK"),"GBK");
		   
		RESPONSE_COMMON_FPKJ response_COMMON_FPKJ = new RESPONSE_COMMON_FPKJ();
		if("0000".equals(response_COMMON_FPKJ.getRETURNCODE())) {
			String fpqqlsh = response_COMMON_FPKJ.getFPQQLSH();
			String jqbh = response_COMMON_FPKJ.getJQBH();
			String fp_DM = response_COMMON_FPKJ.getFP_DM();
			String fp_HM = response_COMMON_FPKJ.getFP_HM();
			String kprq = response_COMMON_FPKJ.getKPRQ();
			String fp_MW = response_COMMON_FPKJ.getFP_MW();
			String jym = response_COMMON_FPKJ.getJYM();
			String ewm = response_COMMON_FPKJ.getEWM();
			IInv inv = new IInv();
			inv.setFplsh(fpqqlsh);
			inv.setPosId(iBillmain.getKpzdh()+"");
			inv.setHz51Id("");
			inv.setQybh(iBillmain.getQybh());;
			inv.setDjly(iBillmain.getDjly());
			inv.setFplx(iBillmain.getFpzl());
			inv.setFptqm(iBillmain.getFptqm());
			inv.setFplsh(fpqqlsh);
			inv.setFpdm(fp_DM);			
			inv.setFphm(fp_HM);	
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date kpsj = sdf.parse(kprq);
			inv.setKpsj(kpsj);
			inv.setFpmw(fp_MW);
			inv.setFwm(jym);
			inv.setEwm(ewm);//??
			inv.setEwm51(ewm);//?
			//0:获取折扣金额,1:折扣税额,2:折扣含税金额,3:获取折扣后含税金额,4折扣后金额,5:折扣后税额Object []obj= {zkhsje,zkje,zkse,zkhhsje,zkhje,zkhse};

			inv.setJshj(new BigDecimal(count[3]+""));//价税合计
			inv.setHjje(new BigDecimal(count[4]+""));
			inv.setHjse(new BigDecimal(count[5]+""));
			inv.setQdbz(iBillmain.getQdbz());
			inv.setBz(iBillmain.getBz());
			inv.setPdfxzdz("");
		    inv.setGfmc(iBillmain.getGfmc());
			inv.setGfsh(iBillmain.getGfsh());
			inv.setGfdzdh(iBillmain.getGfsh());
			inv.setGfyhzh(iBillmain.getGfyhzh());
			inv.setKpr(iBillmain.getKpr());
			inv.setSkr(iBillmain.getSkr());
			inv.setFhr(iBillmain.getFhr());	
			inv.setQddybz(0);
			inv.setFpchbz(0);
			inv.setHxbz(0);
			inv.setZfbz(0);
			Calendar calendar=Calendar.getInstance();
			//获得当前时间的月份，月份<a href="https://www.baidu.com/s?wd=%E4%BB%8E0%E5%BC%80%E5%A7%8B&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHckPjm4nH00T1Y4uhD4P1DdnW-hm1T4uWn10ZwV5Hcvrjm3rH6sPfKWUMw85HfYnjn4nH6sgvPsT6KdThsqpZwYTjCEQLGCpyw9Uz4Bmy-bIi4WUvYETgN-TLwGUv3EnW0vnH61PWTv" target="_blank" class="baidu-highlight">从0开始</a>所以结果要加1
			int month=calendar.get(Calendar.MONTH)+1;
			if(month <10) {
				inv.setSwyf("0"+month);
			}else {
				inv.setSwyf(month+"");
			}
			inv.setSjh(iBillmain.getSj());
			inv.setFpxzzt(0);
			inv.setSyh("");			
		}
		return null;
		
		
	}
	//COMMON_FPKJ_FPT
			public  COMMON_FPKJ_FPT getCOMMON_FPKJ_FPT(IBillmain iBillmain, COMMON_FPKJ_FPT common_FPKJ_FPT, Object[] obj)throws DataillegalityException,NotEmptyException, UnsupportedEncodingException{
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
				String kplx = iBillmain.getKplx()+"";
				
				if("0".equals(kplx)) {
					common_FPKJ_FPT.setXSF_NSRSBH("0");//开票类型:0:蓝票 1:红票
				}else {
					common_FPKJ_FPT.setXSF_NSRSBH("1");//开票类型
				}
				
				//String xfmc = iBillmain.getXfmc();
				String xfmc = new String ((iBillmain.getXfmc()).getBytes(),"utf-8");
				if(xfmc.length()<=20) {
					if(!"".equals(xfmc)&& xfmc !=null) {
						common_FPKJ_FPT.setXSF_MC(xfmc);//销售方名称
					}else {
						throw new NotEmptyException("销售方名称不能为空");
					}
				}else {
					throw new DataillegalityException("销售方名称长度不合法");
							
				}
				
				
				String xfdzdh = iBillmain.getXfdzdh();
				if(xfdzdh.length()<=100) {
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
				if(gfmc.length()<=100) {
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
				if(kpr.length()<=8) {
					if(!"".equals(kpr)&& kpr != null) {
						common_FPKJ_FPT.setKPR("");//开票人</KPR>
					}else {
						throw new NotEmptyException("开票人不能为空");
					}
				}else {
					throw new DataillegalityException("开票人长度不合法");

				}
				String skr = iBillmain.getSkr();
				if(skr.length()<=8) {
					common_FPKJ_FPT.setSKR(skr);//收款人</SKR>
				}else {
					throw new DataillegalityException("收款人长度不合法");
				}
				String fhr = iBillmain.getFhr();
				if(fhr.length()<=8) {
					common_FPKJ_FPT.setFHR(fhr);//复核人</FHR>
				}else {
					throw new DataillegalityException("复核人人长度不合法");
				}
				
				if("0".equals(kplx+"")) {
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
			public COMMON_FPKJ_XMXXS getFpkjXmxx(List<IBilldel> iBilldelList, COMMON_FPKJ_XMXX common_FPKJ_XMXX,IBillmain iBillmain) throws DataillegalityException{
				COMMON_FPKJ_XMXXS common_FPKJ_XMXXS = new COMMON_FPKJ_XMXXS();
				Integer size = 1;
				BigDecimal zkl = new BigDecimal(0);
				for (IBilldel iBilldel : iBilldelList) {
					//折扣行性质:判断折扣金额,如果小于0,需要拆分成被折扣行和折扣行;如果等于0说明是正常行;
					//发票行正常行;非正常行
					BigDecimal zkhje = iBilldel.getZkhje();
					//如果折扣行的税率和相邻的商品行的折扣行的税率相同.折扣行汇总
					
						if(zkhje.compareTo(BigDecimal.ZERO)<0) {
							
							//获取折扣金额
							BigDecimal zkje = iBilldel.getZkje();
							//获取金额
							BigDecimal xmje2 = iBilldel.getXmje();
							
							//计算折扣率
							BigDecimal zkll = zkje.divide(xmje2, 3, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(3,BigDecimal.ROUND_HALF_UP);
							//相邻的商品的折扣率不想等
							if(zkll != zkl ) {
								common_FPKJ_XMXX.setFPHXZ("2");//发票行性质</FPHXZ>
								String xmmc = iBilldel.getXmmc();
								if((xmmc.length())<= 90 && !"".equals(xmmc)&&xmmc!=null) {
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
									throw new DataillegalityException("你输入的项目单价的格式不合法!");
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
								if(!"".equals(spbm)&& spbm!=null && "19".equals(spbm.length() - spbm.indexOf(".") - 1 + "" )) {
									common_FPKJ_XMXX.setSPBM(spbm);;//商品编码</SPBM>
								}else {
									throw new DataillegalityException("你输入的商品编码的长度不正确!");
								}
								
								common_FPKJ_XMXX.setZXBM(iBilldel.getSpbh());//自行编码</ZXBM>
								if("0".equals(iBilldel.getSe())) {
									common_FPKJ_XMXX.setYHZCBS("1");
									common_FPKJ_XMXX.setLSLBS("3");
									common_FPKJ_XMXX.setZZSTSGL("");
									
								}else {
									common_FPKJ_XMXX.setYHZCBS("0");
									common_FPKJ_XMXX.setLSLBS("3");
									common_FPKJ_XMXX.setZZSTSGL("");
								}
								/*String sfyhzc = iBilldel.getSfyhzc()+"";
								if(!"".equals(sfyhzc) && sfyhzc!=null){
									
								}else {
									throw new DataillegalityException("你输入的优惠政策标识不能为空!");
								}
								
								String lslbs = iBilldel.getLslbs();
								
								common_FPKJ_XMXX.setLSLBS(lslbs);//零税率标识<LSLBS>
								
								
								if("1".equals(common_FPKJ_XMXX.getYHZCBS())) {
									common_FPKJ_XMXX.setZZSTSGL("");//增值税特殊管理</ZZSTSGL>
								}else {
									common_FPKJ_XMXX.setZZSTSGL("");//增值税特殊管理</ZZSTSGL>
								}
								common_FPKJ_XMXX.setZZSTSGL("");//增值税特殊管理</ZZSTSGL>
*/								
								
								//---------------------------------------------------------折扣行:1
								common_FPKJ_XMXX.setFPHXZ("1");//发票行性质</FPHXZ>
								String xmmc1 = iBilldel.getXmmc();
								if((xmmc.length())<= 90 && !"".equals(xmmc)&&xmmc!=null) {
									common_FPKJ_XMXX.setXMMC("折扣("+zkll+"%)");//项目名称</XMMC>????
								}
								
								common_FPKJ_XMXX.setGGXH("");//规格型号</GGXH>
								common_FPKJ_XMXX.setDW("");//单位</DW>			
								common_FPKJ_XMXX.setXMSL("");//项目数量</XMSL>
								common_FPKJ_XMXX.setXMDJ(xmdj);;//项目数量</XMSL>
								
								//折扣行金额:折后金额
								BigDecimal zkhje2 = iBilldel.getZkhje();
								
								if("2".equals(xmje.length() - xmje.indexOf(".") - 1 + "")) {
									common_FPKJ_XMXX.setXMJE(zkhje2+"");//项目金额</XMJE>
								}else {
									throw new DataillegalityException("你输入的金额的格式不合法");
								}
								String slv1 =iBilldel.getSl()+"";
								if("2".equals(slv1.length() - slv1.indexOf(".") - 1 + "")) {
									common_FPKJ_XMXX.setSL(slv1);//税率</SL>
								}else if(slv.contains("%")||(!"2".equals(slv.length() - slv.indexOf(".") - 1 + ""))){
									throw new DataillegalityException("你输入的税率的格式不合法!");
								}
								String se1 = iBilldel.getZkse()+"";//折扣税额
								if("2".equals(se1.length() - se1.indexOf(".") - 1 + "")) {
									common_FPKJ_XMXX.setSE(se1);//税额</SE>
								}else{
									throw new DataillegalityException("你输入的税额的格式不合法!");
								}
								
								common_FPKJ_XMXX.setSPBM("");//商品编码</SPBM>
								
								
								common_FPKJ_XMXX.setZXBM("");//自行编码</ZXBM>
								String sfyhzc1 = iBilldel.getSfyhzc()+"";
								if("0".equals(iBilldel.getSe())) {
									//税额为0:说明使用优惠政策 优惠政策为0普通零税率
									common_FPKJ_XMXX.setYHZCBS("");//优惠政策标识<YHZCBS>
									
									common_FPKJ_XMXX.setLSLBS("");//零税率标识<LSLBS>
									
									
								}
								common_FPKJ_XMXX.setYHZCBS("");//优惠政策标识<YHZCBS>
							
								common_FPKJ_XMXX.setLSLBS("");//零税率标识<LSLBS>
								
								common_FPKJ_XMXX.setZZSTSGL("");//增值税特殊管理</ZZSTSGL>
								zkl=zkll;
								size += 2;
							}
						}else if(zkhje.compareTo(BigDecimal.ZERO)== 0) {
							
							//正常行
							common_FPKJ_XMXX.setFPHXZ("0");//发票行性质</FPHXZ>
							//被折扣行
							common_FPKJ_XMXX.setFPHXZ("2");//发票行性质</FPHXZ>
							String xmmc = iBilldel.getXmmc();
							if((xmmc.length())<= 90 && !"".equals(xmmc)&&xmmc!=null) {
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
								throw new DataillegalityException("你输入的项目单价的格式不合法!");
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
							if(!"".equals(spbm)&& spbm!=null && "19".equals(spbm.length() - spbm.indexOf(".") - 1 + "" )) {
								common_FPKJ_XMXX.setSPBM(spbm);;//商品编码</SPBM>
							}else {
								throw new DataillegalityException("你输入的商品编码的长度不正确!");
							}
							
							common_FPKJ_XMXX.setZXBM(iBilldel.getSpbh());//自行编码</ZXBM>
							
							if("0".equals(iBilldel.getSe())) {
								common_FPKJ_XMXX.setYHZCBS("1");//优惠政策标识<YHZCBS>
								common_FPKJ_XMXX.setLSLBS("3");//零税率标识<LSLBS>
							}else {
								common_FPKJ_XMXX.setYHZCBS("0");//优惠政策标识<YHZCBS>
								common_FPKJ_XMXX.setLSLBS("");//零税率标识<LSLBS>
							}
							/*String sfyhzc = iBilldel.getSfyhzc()+"";
							if(!"".equals(sfyhzc) && sfyhzc!=null){
								common_FPKJ_XMXX.setYHZCBS(iBilldel.getSfyhzc()+"");//优惠政策标识<YHZCBS>
							}else {
								throw new DataillegalityException("你输入的优惠政策标识不能为空!");
							}*/
							
							/*String lslbs = iBilldel.getLslbs();
							
							common_FPKJ_XMXX.setLSLBS(lslbs);//零税率标识<LSLBS>
*/							
							
							/*if("1".equals(common_FPKJ_XMXX.getYHZCBS())) {
								common_FPKJ_XMXX.setZZSTSGL("");//增值税特殊管理</ZZSTSGL>
							}else {
								common_FPKJ_XMXX.setZZSTSGL("");//增值税特殊管理</ZZSTSGL>
							}*/
							common_FPKJ_XMXX.setZZSTSGL("");//增值税特殊管理</ZZSTSGL>
							size++;
						}
					
					
					String xmmc = iBilldel.getXmmc();
					if((xmmc.length())<= 90 && !"".equals(xmmc)&&xmmc!=null) {
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
						throw new DataillegalityException("你输入的项目单价的格式不合法!");
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
					if(!"".equals(spbm)&& spbm!=null && "19".equals(spbm.length() - spbm.indexOf(".") - 1 + "" )) {
						common_FPKJ_XMXX.setSPBM(spbm);;//商品编码</SPBM>
					}else {
						throw new DataillegalityException("你输入的商品编码的长度不正确!");
					}
					
					common_FPKJ_XMXX.setZXBM(iBilldel.getSpbh());//自行编码</ZXBM>
					/*String sfyhzc = iBilldel.getSfyhzc()+"";
					if(!"".equals(sfyhzc) && sfyhzc!=null){
						common_FPKJ_XMXX.setYHZCBS(iBilldel.getSfyhzc()+"");//优惠政策标识<YHZCBS>
					}else {
						throw new DataillegalityException("你输入的优惠政策标识不能为空!");
					}
					
					String lslbs = iBilldel.getLslbs();
					
					common_FPKJ_XMXX.setLSLBS(lslbs);//零税率标识<LSLBS>
*/					if("0".equals(iBilldel.getSe())) {
						common_FPKJ_XMXX.setYHZCBS("1");//优惠政策标识<YHZCBS>
						common_FPKJ_XMXX.setLSLBS("3");//零税率标识<LSLBS>
					}else {
						common_FPKJ_XMXX.setYHZCBS("0");//优惠政策标识<YHZCBS>
						common_FPKJ_XMXX.setLSLBS("");//零税率标识<LSLBS>
					}
					
					/*if("1".equals(common_FPKJ_XMXX.getYHZCBS())) {
						common_FPKJ_XMXX.setZZSTSGL("");//增值税特殊管理</ZZSTSGL>
					}else {
						common_FPKJ_XMXX.setZZSTSGL("");//增值税特殊管理</ZZSTSGL>
					}*/
					common_FPKJ_XMXX.setZZSTSGL("");//增值税特殊管理</ZZSTSGL>
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
			public static void main(String[] args) {
				Calendar calendar=Calendar.getInstance();
				int month=calendar.get(Calendar.MONTH)+1;
				System.out.println(month);
			}
}

