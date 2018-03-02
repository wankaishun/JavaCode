package com.liqun.controller.api;


import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liqun.controller.advice.ApiControllerAdvisable;
import com.liqun.entity.BmSpflZtree;
import com.liqun.entity.CommodityMaintenance;
import com.liqun.entity.NewCommodityMaintenance;
import com.liqun.entity.NewMenuItem;
import com.liqun.service.CommodityMaintenanceService;
import com.liqun.util.DataGridPage;
import com.liqun.util.ExcelUtil;

/**
 * 商品维护
 * @author hzy
 * 数据类
 */
@Controller
@RequestMapping("/api/commodityMaintenance")
@ApiControllerAdvisable
public class CommodityMaintenanceController {
	
	@Autowired
	private CommodityMaintenanceService commodityMaintenanceService;
	
	//检索商品信息
	@RequestMapping("/syscmList")
	@ResponseBody
	public Object syscmList(@RequestParam int page, @RequestParam int rows, String realName, String flag,
			String roleId) {
		Page<NewCommodityMaintenance> syscmlist = commodityMaintenanceService.findAll(PageRequest.of(page - 1, rows), realName, flag, roleId);

		return DataGridPage.pageToGrid(syscmlist);
	}
	
	//刷新页面
	@RequestMapping("/refresh")
	@ResponseBody
	public String refresh(@RequestParam int page, @RequestParam int rows, String realName, String flag,
			String roleId) {
		Page<NewCommodityMaintenance> syscmlist = commodityMaintenanceService.findAll(PageRequest.of(page - 1, rows), realName, flag, roleId);
		return "console/commodity/maintenance/commodityMaintenance";
	}
	
	//新增商品信息
	@RequestMapping("/insert")
	@ResponseBody
    public int insert(CommodityMaintenance commodityMaintenance)
    {
		commodityMaintenance.setSpzt(1);//追加商品状态 
		commodityMaintenance.setZhgxsj(new Date());//追加最后一次更新时间	
		return commodityMaintenanceService.insert(commodityMaintenance);
    }
	
	//修改商品信息
	@RequestMapping("/update")
	@ResponseBody
	public int update(CommodityMaintenance commodityMaintenance)
    {
		commodityMaintenance.setZhgxsj(new Date());//修改最后一次更新时间
    	return commodityMaintenanceService.update(commodityMaintenance);
    }
	
	//批量删除商品信息
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(@RequestParam(value="spid[]") Integer[] spid)
    {		
    	return commodityMaintenanceService.delete(spid);
    }
	
	//根据商品编号查询商品信息
	@RequestMapping("/edit")
	@ResponseBody
	public Object get(int spid)
    {	
	     return commodityMaintenanceService.get(spid);
	}
	
	//根据树形菜单检索信息
	@RequestMapping(value = "getCmTree", method = RequestMethod.POST)
	@ResponseBody
	public Object getCmTree() {
		List<NewMenuItem> list =commodityMaintenanceService.getCmTree(0);
		System.out.println(list);
		return list;
	}
	
	//根据主键查询树形菜单信息
	@RequestMapping(value = "getCmInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object getOrgInfo(String bm) {
		BmSpflZtree bmSpflZtree=commodityMaintenanceService.getCmInfo(bm);
		return bmSpflZtree;
	}
	
	//导出
	@GetMapping("/exportCm") 
	public Object exportCm(HttpServletResponse response,HttpServletRequest request) {
		 
		String realName=request.getParameter("realName");
		String flag=request.getParameter("flag");
		String roleId=request.getParameter("roleId");

		response.setContentType("application/binary;charset=UTF-8");
		try{
		     ServletOutputStream out=response.getOutputStream();
		     String fileName=new String((URLEncoder.encode("spxx", "UTF-8")+ new SimpleDateFormat("yyyy-MM-dd").format(new Date())).getBytes(),"UTF-8");
		     response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
		     List<NewCommodityMaintenance> list = commodityMaintenanceService.getIBillmainForExport(realName,flag,roleId);  
		     ExcelUtil<NewCommodityMaintenance> util = new ExcelUtil<NewCommodityMaintenance>(NewCommodityMaintenance.class);// 创建工具类.  
		        util.exportExcel(list, "spxx", 65536, out);// 导出  
		        System.out.println(out);
		        System.out.println("----执行完毕----------");  
		 } catch(Exception e){
		     e.printStackTrace();
		     return "导出信息失败";
		 }
		return null;
	}
}
