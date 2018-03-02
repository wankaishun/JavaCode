package com.liqun.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis2.databinding.types.soapencoding.Integer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htxx.pojo.A9Param;
import com.htxx.services.A9Servcie;
import com.liqun.dao.IBilldelMapper;
import com.liqun.dao.IBillmainMapper;
import com.liqun.dao.MManageMapper;
import com.liqun.entity.IBilldel;
import com.liqun.entity.IBillmain;
/**
 * 单据处理
 * @author wks
 *
 */
@Service
@Transactional
public class BillHandingServiceImpl {
	@Autowired
	private IBillmainMapper iBillmainMapper;
	@Autowired
	private IBilldelMapper iBilldelMapper;
	@Autowired
	private MManageMapper  mManageMapper;
	
	/**
	 * 获得单据列表
	 * @param pageRequest
	 * @param fptqm 提取码
	 * @param fplsh 流水号
	 * @param gfmc	购方名称
	 * @param gfsh	购方税号
	 * @param starttime 
	 * @param endtime
	 * @return
	 */
	public Page<IBillmain> getBillList(PageRequest pageRequest,String fptqm, String fplsh, String gfmc,String gfsh,String djzt, String starttime,String endtime) {
		List<IBillmain> billList=iBillmainMapper.getIBillmainList(pageRequest,fptqm, fplsh, gfmc, gfsh,djzt, starttime, endtime);
		long total=iBillmainMapper.countAll(fptqm, fplsh, gfmc, gfsh,djzt, starttime, endtime);
		return new PageImpl<>(billList, pageRequest, total);
	}
	/**
	 * 根据发票流水号获得所有的商品明细
	 * @param pageRequest
	 * @param fplsh
	 * @return
	 */
	public Page<IBilldel> getBilldel(PageRequest pageRequest, String fplsh) {
		List<IBilldel> billdelList=iBilldelMapper.getIBilldel(pageRequest, fplsh);
		long total=iBilldelMapper.countAll(fplsh);
		return new PageImpl<>(billdelList, pageRequest, total);
	}
	/**
	 * 合并 单据
	 * @param id
	 * @param type
	 * @return
	 */
	public int mergeBill(String[] fplsh,int type) {
		/*
		 * 1.将选中的 单据 数据变为已合并
		 * 2.将 选中的一条数据对发票提取码 和 发票流水号修改该 进行重新插入
		 * 3.根据 fplsh 查出所有的 billdel  然后根据 type进行合并
		 */
		Map<String,Object> map=new HashMap<String,Object>();
    	map.put("fplshs", fplsh);
    	map.put("djzt", 5);
		iBillmainMapper.updateDjzt(map);
		//1查询出合并的后的IBillmain对象
		IBillmain iBillmain=iBillmainMapper.getIBillmain(map);
		//2将对象插入
		int result=iBillmainMapper.insertSelective(iBillmain);
		//3查询出合并的后的ibilldel集合
		map.put("type", 0);
		List<IBilldel> list=iBilldelMapper.getBilldelMerge(map);
		//4将集合插入
		Map<String,Object> delMap=new HashMap<String,Object>();
		delMap.put("list", list);
		iBilldelMapper.insertBilldelList(delMap);
		return 1;
	}
	/**
	 * 获得导出的所有数据list
	 * @param fptqm
	 * @param fplsh
	 * @param gfmc 购方名称
	 * @param gfsh 购方税号
	 * @param djzt 单据状态
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public List<IBillmain> getIBillmainForExport(String fptqm,String fplsh,String gfmc,String gfsh,String djzt,String starttime,String endtime){
		return iBillmainMapper.getIBillmainForExport(fptqm, fplsh, gfmc, gfsh, djzt, starttime, endtime);
	}
	/**
	 * 拆分 单据数据
	 * @param fplsh 发票流水号
	 * @param type 拆分类型（保XX）
	 * @param typeArray 拆分类型数组
	 * @return
	 */
	public int splitBill(String[] fplsh,int type,String[] typeArray,int row) {
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("fplshs", fplsh);
    	map.put("djzt", 0);
		//获得需要拆分的 单据信息list集合
		List<IBillmain> list=iBillmainMapper.getIBillmainByFPLSH(map);
		Map<String,Object> map1=new HashMap<String,Object>();
		map1.put("fplshs", fplsh);
    	map1.put("djzt", 1);
		//更改单据状态（已拆分）
		iBillmainMapper.updateDjzt(map1);
		//对每个单据进行拆分处理
		for (IBillmain iBillmain : list) {
			Map<String,Object> map3=new HashMap<String,Object>();
			map3.put("fplsh", iBillmain.getFplsh());
			List<IBilldel> iBilldelList=iBilldelMapper.getBilldeList(map3);
			int size=iBilldelList.size();
			if(Arrays.asList(typeArray).contains("3")) {
				if(size>row) {//如果该条单据的明细条数超过限制
					int billNum=(int) Math.ceil((double)size/row);//超过 规定明细条数 拆成的单据条数
					String fplsh_=iBillmain.getFplsh();
					String fptqm_=iBillmain.getFptqm();
					for (int i = 1; i <=billNum; i++) {
						List<IBilldel> list1=new ArrayList<IBilldel>();
						if(iBilldelList.size()>row) { //判断剩余明细是否 大于最大明细条数限制
							for (int j = 0; j < row; j++) {
								iBilldelList.get(0);
								list1.add(iBilldelList.get(0));//得到list集合
								iBilldelList.remove(0);
							}
							//list1.add(e);
						}else {	
							list1=iBilldelList;
						}
						BigDecimal sumXmje=new BigDecimal(0);BigDecimal sumSe=new BigDecimal(0) ;BigDecimal sumHsje=new BigDecimal(0); 
						for (IBilldel iBilldel : list1) {
							sumXmje=sumXmje.add(iBilldel.getZkhje());//项目金额
							sumSe=sumSe.add(iBilldel.getZkhse());//税额
							sumHsje=sumHsje.add(iBilldel.getZkhhsje());//含税金额
							iBilldel.setFplsh(iBilldel.getFplsh()+"_"+i);//修改明细的发票流水号
							iBilldel.setFptqm(iBilldel.getFptqm()+"_"+i);//修改明细的发票流水号
						}
						iBillmain.setFptqm(fptqm_+"_"+i);
						iBillmain.setFplsh(fplsh_+"_"+i);
						iBillmain.setHjje(sumXmje);
						iBillmain.setHjse(sumSe);
						iBillmain.setJshj(sumHsje);
						iBillmainMapper.insertSelective(iBillmain);
						Map<String,Object> map4=new HashMap<String,Object>();
						map4.put("list", list1);
						iBilldelMapper.insertBilldelList(map4);//将明细插入
						Map<String,Object> map2=new HashMap<String,Object>();//将第一次拆分的 ibiallmain 改为已拆分
						List<String> list2=new ArrayList<String>();
						list2.add(iBillmain.getFplsh());
						map2.put("fplshs", list2);
						map2.put("djzt", 1);
						//更改单据状态（已拆分）
						iBillmainMapper.updateDjzt(map2);
						if(Arrays.asList(typeArray).contains("1")) {//是否勾选 按金额均分
							splitAverage(iBillmain,type,typeArray);
						}else {
							splitMaxLimit(iBillmain,type,typeArray);
						}
					}
					return 0;
				}
			}
			if(Arrays.asList(typeArray).contains("1")) {//是否勾选 按金额均分
				splitAverage(iBillmain,type,typeArray);
			}else {
				splitMaxLimit(iBillmain,type,typeArray);
			}
		}
		return 1;
	}
	//按最大限额均分
	@SuppressWarnings("unused")
	private void splitMaxLimit(IBillmain iBillmain,int type,String[] typeArray) {
		double limit=getLimit();
		double jshjSum=iBillmain.getJshj().doubleValue();//加税合计
		double jshj=iBillmain.getJshj().doubleValue();//加税合计
		int splitTimes=(int) Math.ceil(jshj/limit);//均分次数
		List<IBillmain> list=new ArrayList<IBillmain>();
		for(int i=1;i<=splitTimes;i++) {
			IBillmain iBillmainTemp =new IBillmain();
			iBillmainTemp.setBmbbh(iBillmain.getBmbbh());
			iBillmainTemp.setBz(iBillmain.getBz());
			iBillmainTemp.setCzsj(iBillmain.getCzsj());
			iBillmainTemp.setDjlx(iBillmain.getDjlx());
			iBillmainTemp.setDjly(iBillmain.getDjly());
			iBillmainTemp.setDjzt(iBillmain.getDjzt());
			iBillmainTemp.setEmail(iBillmain.getEmail());
			iBillmainTemp.setFhr(iBillmain.getFhr());
			iBillmainTemp.setFjh(iBillmain.getFjh());
			String newFplsh=iBillmain.getFplsh()+'_'+i;
			iBillmainTemp.setFplsh(newFplsh);//---------
			String newFptqm=iBillmain.getFptqm()+'_'+i;
			iBillmainTemp.setFptqm(newFptqm);//---------
			iBillmainTemp.setFpzl(iBillmain.getFpzl());//发票种类41:卷票 51:电子票
			iBillmainTemp.setGfdzdh(iBillmain.getGfdzdh());
			iBillmainTemp.setGfmc(iBillmain.getGfmc());
			iBillmainTemp.setGfsh(iBillmain.getGfsh());
			iBillmainTemp.setGfyhzh(iBillmain.getGfyhzh());//购方银行帐号
			//最大限额分
			iBillmainTemp.setHjje(new BigDecimal(iBillmain.getHjje().doubleValue()*(limit/jshjSum)));
			iBillmainTemp.setHjse(new BigDecimal(iBillmain.getHjse().doubleValue()*(limit/jshjSum)));
			iBillmainTemp.setJshj(new BigDecimal(limit));
			iBillmainTemp.setHptzdbh(iBillmain.getHptzdbh());//红票通知单号
			iBillmainTemp.setJfzt(iBillmain.getJfzt());
			iBillmainTemp.setKplx(iBillmain.getKplx());
			iBillmainTemp.setKpr(iBillmain.getKpr());
			iBillmainTemp.setKpzdh(iBillmain.getKpzdh());
			iBillmainTemp.setNsrsbh(iBillmain.getNsrsbh());
			iBillmainTemp.setQdbz(iBillmain.getQdbz());
			iBillmainTemp.setQybh(iBillmain.getQybh());
			iBillmainTemp.setSj(iBillmain.getSj());
			iBillmainTemp.setXfdzdh(iBillmain.getXfdzdh());
			iBillmainTemp.setXfmc(iBillmain.getXfmc());
			iBillmainTemp.setXfsh(iBillmain.getXfsh());
			iBillmainTemp.setXfyhzh(iBillmain.getXfyhzh());
			iBillmainTemp.setYfpdm(iBillmain.getYfpdm());
			iBillmainTemp.setYfphm(iBillmain.getYfphm());
			iBillmainMapper.insertSelective(iBillmainTemp);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("fplsh", iBillmain.getFplsh());
			List<IBilldel> iBilldelList=iBilldelMapper.getBilldeList(map);
			BigDecimal sumZkhsje=new BigDecimal(0);BigDecimal sumZkje=new BigDecimal(0);BigDecimal sumZkse=new BigDecimal(0);
			int size=iBilldelList.size();
			for (IBilldel iBilldel : iBilldelList) {
				sumZkhsje=sumZkhsje.add(iBilldel.getZkhsje());
				sumZkje=sumZkje.add(iBilldel.getZkje());
				sumZkse=sumZkse.add(iBilldel.getZkse());
			}
			BigDecimal divZkhsje=sumZkhsje.multiply(new BigDecimal(limit/jshjSum));//均分折扣后的数据
			BigDecimal divZkje=sumZkje.multiply(new BigDecimal(limit/jshjSum));
			BigDecimal divZkse=sumZkse.multiply(new BigDecimal(limit/jshjSum));
			for(int j=0;j<iBilldelList.size();j++) {
				IBilldel iBilldel=iBilldelList.get(j);
				if(type==1) {
					//保单价
					iBilldel.setXmsl(new BigDecimal(iBilldel.getXmsl().doubleValue()*(limit/jshjSum)));//项目数量均分
					iBilldel.setXmje(new BigDecimal(iBilldel.getXmje().doubleValue()*(limit/jshjSum)));
					iBilldel.setSe(new BigDecimal(iBilldel.getSe().doubleValue()*(limit/jshjSum)));
					iBilldel.setHsje(new BigDecimal(iBilldel.getHsje().doubleValue()*(limit/jshjSum)));
				}else if(type==2) {
					//保数量
					iBilldel.setXmdj(new BigDecimal(iBilldel.getXmdj().doubleValue()*(limit/jshjSum)));//项目单价均分
					iBilldel.setXmje(new BigDecimal(iBilldel.getXmje().doubleValue()*(limit/jshjSum)));
					iBilldel.setSe(new BigDecimal(iBilldel.getSe().doubleValue()*(limit/jshjSum)));
					iBilldel.setHsje(new BigDecimal(iBilldel.getHsje().doubleValue()*(limit/jshjSum)));
				}
				//不均摊折扣
				if(Arrays.asList(typeArray).contains("2")) {
					iBilldel.setZkhsje(new BigDecimal(iBilldel.getZkhsje().doubleValue()*(limit/jshjSum)));
					iBilldel.setZkje(new BigDecimal(iBilldel.getZkje().doubleValue()*(limit/jshjSum)));
					iBilldel.setZkhhsje(new BigDecimal(iBilldel.getZkhhsje().doubleValue()*(limit/jshjSum)));
					iBilldel.setZkhje(new BigDecimal(iBilldel.getZkhje().doubleValue()*(limit/jshjSum)));
					iBilldel.setZkhse(new BigDecimal(iBilldel.getZkhse().doubleValue()*(limit/jshjSum)));
				}else {
					//均摊折扣
					double bili=iBilldel.getZkhhsje().doubleValue()/jshjSum;
					iBilldel.setZkhhsje(iBilldel.getHsje().add(divZkhsje.multiply(new BigDecimal(bili))));
					iBilldel.setZkhje(iBilldel.getXmje().add(divZkje.multiply(new BigDecimal(bili))));
					iBilldel.setZkhse(iBilldel.getSe().add(divZkse.multiply(new BigDecimal(bili))));
					iBilldel.setZkhsje(divZkhsje.multiply(new BigDecimal(bili)));
					iBilldel.setZkje(divZkje.multiply(new BigDecimal(bili)));
					iBilldel.setZkse(divZkse.multiply(new BigDecimal(bili)));
				}
				iBilldel.setFplsh(newFplsh);
				iBilldel.setFptqm(newFptqm);
			}
				//插入ibilldel
				Map<String,Object> delMap=new HashMap<String,Object>();
				delMap.put("list", iBilldelList);
				iBilldelMapper.insertBilldelList(delMap);
				jshj=jshj-limit;
				if(jshj<limit) {
					limit= jshj;
				}
			}
		}
	//按照金额均分
	@SuppressWarnings("unused")
	private void splitAverage(IBillmain iBillmain,int type,String[] typeArray) {
		double limit=getLimit();//每笔最大限额
		double jshjSum=iBillmain.getJshj().doubleValue();//加税合计
		double jshj=iBillmain.getJshj().doubleValue();//加税合计
		int splitTimes=(int) Math.ceil(jshj/limit);//均分次数
		List<IBillmain> list=new ArrayList<IBillmain>();
		for(int i=1;i<=splitTimes;i++) {
			IBillmain iBillmainTemp =new IBillmain();
			iBillmainTemp.setBmbbh(iBillmain.getBmbbh());
			iBillmainTemp.setBz(iBillmain.getBz());
			iBillmainTemp.setCzsj(iBillmain.getCzsj());
			iBillmainTemp.setDjlx(iBillmain.getDjlx());
			iBillmainTemp.setDjly(iBillmain.getDjly());
			iBillmainTemp.setDjzt(iBillmain.getDjzt());
			iBillmainTemp.setEmail(iBillmain.getEmail());
			iBillmainTemp.setFhr(iBillmain.getFhr());
			iBillmainTemp.setFjh(iBillmain.getFjh());
			String newFplsh=iBillmain.getFplsh()+'_'+i;
			iBillmainTemp.setFplsh(newFplsh);//---------
			String newFptqm=iBillmain.getFptqm()+'_'+i;
			iBillmainTemp.setFptqm(newFptqm);//---------
			iBillmainTemp.setFpzl(iBillmain.getFpzl());//发票种类41:卷票 51:电子票
			iBillmainTemp.setGfdzdh(iBillmain.getGfdzdh());
			iBillmainTemp.setGfmc(iBillmain.getGfmc());
			iBillmainTemp.setGfsh(iBillmain.getGfsh());
			iBillmainTemp.setGfyhzh(iBillmain.getGfyhzh());//购方银行帐号
			//均分金额
			iBillmainTemp.setHjje(new BigDecimal(iBillmain.getHjje().doubleValue()/splitTimes));
			iBillmainTemp.setHjse(new BigDecimal(iBillmain.getHjse().doubleValue()/splitTimes));
			iBillmainTemp.setHptzdbh(iBillmain.getHptzdbh());//红票通知单号
			iBillmainTemp.setJfzt(iBillmain.getJfzt());
			iBillmainTemp.setJshj(new BigDecimal(iBillmain.getJshj().doubleValue()/splitTimes));
			iBillmainTemp.setKplx(iBillmain.getKplx());
			iBillmainTemp.setKpr(iBillmain.getKpr());
			iBillmainTemp.setKpzdh(iBillmain.getKpzdh());
			iBillmainTemp.setNsrsbh(iBillmain.getNsrsbh());
			iBillmainTemp.setQdbz(iBillmain.getQdbz());
			iBillmainTemp.setQybh(iBillmain.getQybh());
			iBillmainTemp.setSj(iBillmain.getSj());
			iBillmainTemp.setXfdzdh(iBillmain.getXfdzdh());
			iBillmainTemp.setXfmc(iBillmain.getXfmc());
			iBillmainTemp.setXfsh(iBillmain.getXfsh());
			iBillmainTemp.setXfyhzh(iBillmain.getXfyhzh());
			iBillmainTemp.setYfpdm(iBillmain.getYfpdm());
			iBillmainTemp.setYfphm(iBillmain.getYfphm());
			iBillmainMapper.insertSelective(iBillmainTemp);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("fplsh", iBillmain.getFplsh());
			List<IBilldel> iBilldelList=iBilldelMapper.getBilldeList(map);
			BigDecimal sumZkhsje=new BigDecimal(0);BigDecimal sumZkje=new BigDecimal(0);BigDecimal sumZkse=new BigDecimal(0);
			int size=iBilldelList.size();
			for (IBilldel iBilldel : iBilldelList) {
				/*BigDecimal zkhhsje=iBilldel.getZkhhsje();
				BigDecimal zkhje=iBilldel.getZkhje();
				BigDecimal zkhse=iBilldel.getZkhse();*/
				sumZkhsje=sumZkhsje.add(iBilldel.getZkhsje());
				sumZkje=sumZkje.add(iBilldel.getZkje());
				sumZkse=sumZkse.add(iBilldel.getZkse());
			}
			BigDecimal divZkhsje=sumZkhsje.divide(new BigDecimal(splitTimes),2, BigDecimal.ROUND_HALF_UP);//均分折扣后的数据
			BigDecimal divZkje=sumZkje.divide(new BigDecimal(splitTimes),2, BigDecimal.ROUND_HALF_UP);
			BigDecimal divZkse=sumZkse.divide(new BigDecimal(splitTimes),2, BigDecimal.ROUND_HALF_UP);
			for(int j=0;j<iBilldelList.size();j++) {
				IBilldel iBilldel=iBilldelList.get(j);
				if(type==1) {
					//保单价
					iBilldel.setXmsl(new BigDecimal(iBilldel.getXmsl().doubleValue()/splitTimes));//项目数量均分
					iBilldel.setXmje(new BigDecimal(iBilldel.getXmje().doubleValue()/splitTimes));
					iBilldel.setSe(new BigDecimal(iBilldel.getSe().doubleValue()/splitTimes));
					iBilldel.setHsje(new BigDecimal(iBilldel.getHsje().doubleValue()/splitTimes));
				}else if(type==2) {
					//保数量
					iBilldel.setXmdj(new BigDecimal(iBilldel.getXmdj().doubleValue()/splitTimes));//项目单价均分
					iBilldel.setXmje(new BigDecimal(iBilldel.getXmje().doubleValue()/splitTimes));
					iBilldel.setSe(new BigDecimal(iBilldel.getSe().doubleValue()/splitTimes));
					iBilldel.setHsje(new BigDecimal(iBilldel.getHsje().doubleValue()/splitTimes));
				}
				
				//不均摊折扣
				if(Arrays.asList(typeArray).contains("2")) {
					iBilldel.setZkhsje(new BigDecimal(iBilldel.getZkhsje().doubleValue()/splitTimes));
					iBilldel.setZkje(new BigDecimal(iBilldel.getZkje().doubleValue()/splitTimes));
					iBilldel.setZkhhsje(new BigDecimal(iBilldel.getZkhhsje().doubleValue()/splitTimes));
					iBilldel.setZkhje(new BigDecimal(iBilldel.getZkhje().doubleValue()/splitTimes));
					iBilldel.setZkhse(new BigDecimal(iBilldel.getZkhse().doubleValue()/splitTimes));
				}else {
					//均摊折扣
					double bili=iBilldel.getZkhhsje().doubleValue()/jshjSum;
					//按比例均摊折扣
					/*iBilldel.setZkhhsje((iBilldel.getZkhsje().subtract(divZkhsje)).add(iBilldel.getZkhhsje()));
					iBilldel.setZkhje(iBilldel.getZkje().subtract(divZkje).add(iBilldel.getZkhje()));
					iBilldel.setZkhse(iBilldel.getZkse().subtract(divZkse).add(iBilldel.getZkhse()));*/
					iBilldel.setZkhhsje(iBilldel.getHsje().add(divZkhsje.multiply(new BigDecimal(bili))));
					iBilldel.setZkhje(iBilldel.getXmje().add(divZkje.multiply(new BigDecimal(bili))));
					iBilldel.setZkhse(iBilldel.getSe().add(divZkse.multiply(new BigDecimal(bili))));
					iBilldel.setZkhsje(divZkhsje.multiply(new BigDecimal(bili)));
					iBilldel.setZkje(divZkje.multiply(new BigDecimal(bili)));
					iBilldel.setZkse(divZkse.multiply(new BigDecimal(bili)));
				}
				iBilldel.setFplsh(newFplsh);
				iBilldel.setFptqm(newFptqm);
			}
				//插入ibilldel
				Map<String,Object> delMap=new HashMap<String,Object>();
				delMap.put("list", iBilldelList);
				iBilldelMapper.insertBilldelList(delMap);
		}
	}
	
	public boolean checkSplit(String[] fplsh,int row) {
		double limit=getLimit();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("fplshs", fplsh);
		List<IBillmain> list=iBillmainMapper.getIBillmainByFPLSH(map);
		for (IBillmain iBillmain : list) {
			int hjje=iBillmain.getHjje().intValue();
			Map<String,Object> map3=new HashMap<String,Object>();
			map3.put("fplsh", iBillmain.getFplsh());
			List<IBilldel> iBilldelList=iBilldelMapper.getBilldeList(map3);
			int size=iBilldelList.size();
			if(hjje<limit&&size<row) {
				return false;//合计金额小于 行数小于最大行数 限定金额不需要拆分
			}
		}
		return true;
	}
	@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) {
		String[] typeArray= {"1","2","3"};
		System.out.println(Arrays.asList(typeArray).contains("1"));
	}
	private double getLimit() {
		double limit=mManageMapper.getMManage().getZdxe().doubleValue();
		return limit;
	}
}
