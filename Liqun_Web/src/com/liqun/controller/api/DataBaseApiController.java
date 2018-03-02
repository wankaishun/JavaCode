package com.liqun.controller.api;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liqun.aop.DbLoggable;
import com.liqun.entity.BizProductBrand;
import com.liqun.entity.BizProductCategory;
import com.liqun.entity.BizProductCategoryBrandService;
import com.liqun.entity.BizProductCategoryBrandVW;
import com.liqun.entity.SysPermission;
import com.liqun.entity.SysUser;
import com.liqun.service.BizProductBrandService;
import com.liqun.service.BizProductCategoryBrandServiceService;
import com.liqun.service.BizProductCategoryService;
import com.liqun.service.SysLogService;
import com.liqun.util.DataGridPage;
//增删改操作类
@Controller
@RequestMapping("/api/database")
public class DataBaseApiController {
	
	@Autowired	SysLogService sysLogService;
	//region 品牌管理
	@Autowired BizProductBrandService bizProductBrandService;
	@GetMapping("/brandlist")
	@ResponseBody	
	public Object brandList(@RequestParam String productBrandName,@RequestParam String flag,@RequestParam int page, @RequestParam int rows) {
	    //数据库分页 当前页需要减一
		if(flag=="")flag=null;
		Page<BizProductBrand> brandList = bizProductBrandService.findAll(productBrandName,flag,PageRequest.of(page-1,rows));
		
		return DataGridPage.pageToGrid(brandList);
	}
	@GetMapping("/findBrandbyCategoryId")
	@ResponseBody	
	public Object findBrandbyCategoryId(@RequestParam String productBrandName,@RequestParam Long categoryId,@RequestParam int page, @RequestParam int rows) {
	    //数据库分页 当前页需要减一		
		Page<BizProductCategoryBrandVW> brandList = bizProductBrandService.findBrandbyCategoryId(productBrandName,categoryId,PageRequest.of(page-1,rows));		
		return DataGridPage.pageToGrid(brandList);
	}
	@GetMapping("/findbrandunlinkedbycategoryId")
	@ResponseBody
	public Object findBrandUnLinkedByCategoryId(@RequestParam String productBrandName,@RequestParam Long categoryId,@RequestParam int page, @RequestParam int rows) {
	    //数据库分页 当前页需要减一		
		Page<BizProductBrand> brandList = bizProductBrandService.findBrandUnLinkedByCategoryId(productBrandName,categoryId,PageRequest.of(page-1,rows));		
		return DataGridPage.pageToGrid(brandList);
	}
	@GetMapping("/brandexist")
	@ResponseBody
	public Object brandExist(@RequestParam String productBrandNo)
	{
		return	bizProductBrandService.brandExist(productBrandNo);		
	}

	@RequestMapping(value = "brandsave", method = RequestMethod.POST)
	@ResponseBody
	//@DbLoggable(describe="保存：保存品牌信息!")
	public Object brandSave(@RequestParam Integer id,@RequestParam String productBrandNo,@RequestParam String productBrandName,@RequestParam String flag) {	
		SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();	
		BizProductBrand brand=new BizProductBrand();
		brand.setId(id);
		if(productBrandNo!="") brand.setProductBrandNo(productBrandNo);
		if(productBrandName!="") brand.setProductBrandName(productBrandName);
		brand.setFlag(flag);
		if (brand.getId()!= null && brand.getId()!= 0) {
			// edit
			brand.setUpdateUser(currentUser.getAccount());
			brand.setUpdateDate(new Date());	
			sysLogService.insertLog(29, "修改品牌信息："+brand.getProductBrandName());
			return bizProductBrandService.UpdateBizProductBrand(brand);
		} else {
			// add
			if( bizProductBrandService.brandExist(brand.getProductBrandNo())>0 )
			{
				return "品牌编号重复！";
			}
		    brand.setCreateUser(currentUser.getAccount());
			brand.setCreateDate(new Date());	
			sysLogService.insertLog(29, "添加品牌信息："+brand.getProductBrandName());
			return bizProductBrandService.AddBizProductBrand(brand);
		}
	}
	//endregion
	
    //region 品类管理
	@Autowired BizProductCategoryService bizProductCategoryService;
	@GetMapping("/categorylist")
	@ResponseBody
	public Object CategoryList(@RequestParam String productCategoryName,@RequestParam String flag,@RequestParam int page, @RequestParam int rows) {
	    //数据库分页 当前页需要减一
		if(flag=="")flag=null;
		Page<BizProductCategory> categoryList = bizProductCategoryService.findAll(productCategoryName,flag,PageRequest.of(page-1,rows));
		
		return DataGridPage.pageToGrid(categoryList);
	}
	
	@GetMapping("/categoryexist")
	@ResponseBody
	public Object categoryExist(@RequestParam String productBrandNo)
	{
		return	bizProductCategoryService.categoryExist(productBrandNo);		
	}

	@RequestMapping(value = "categorysave", method = RequestMethod.POST)
	@ResponseBody
	//@DbLoggable(describe="保存：保存品类信息!")
	public Object categorySave(@RequestParam Integer id,@RequestParam String productCategoryNo,@RequestParam String productCategoryName,@RequestParam String flag) {	
		SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();	
		BizProductCategory category=new BizProductCategory();
		category.setId(id);
		if(productCategoryNo!="") category.setProductCategoryNo(productCategoryNo);
		if(productCategoryName!="") category.setProductCategoryName(productCategoryName);
		category.setFlag(flag);
		if (category.getId()!= null && category.getId()!= 0) {
			// edit
			category.setUpdateUser(currentUser.getAccount());
			category.setUpdateDate(new Date());	
			sysLogService.insertLog(28, "修改品类信息："+category.getProductCategoryName());
			return bizProductCategoryService.updateBizProductCategory(category);
		} else {
			// add
			if( bizProductCategoryService.categoryExist(category.getProductCategoryNo())>0 )
			{
				return "品类编号重复！";
			}
			category.setCreateUser(currentUser.getAccount());
			category.setCreateDate(new Date());			
			sysLogService.insertLog(28, "添加品类信息："+category.getProductCategoryName());
			return bizProductCategoryService.AddBizProductCategory(category);
		}
	}
	//endregion
	
   //region 品类品牌
	@Autowired BizProductCategoryBrandServiceService bizProductCategoryBrandServiceService;
	
	@GetMapping("/addcategorybrandlinked")
	@ResponseBody
	//@DbLoggable(describe="添加：品牌品类关联信息!")
	public Object addCategoryBrandLinked(@RequestParam String BrandIds,@RequestParam Integer categoryId) {	    
		SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();	
		String[] BrandIdss=BrandIds.split(",");
		 BizProductCategoryBrandService bizProductCategoryBrandService=new BizProductCategoryBrandService();
		 Integer lines=0;
		 for (String BrandId : BrandIdss) {
			 bizProductCategoryBrandService.setbrandId(Integer.parseInt(BrandId));
			 bizProductCategoryBrandService.setcategoryId(categoryId);
			 bizProductCategoryBrandService.setCanRepair("1");
			 bizProductCategoryBrandService.setCanInstall("1");
			 bizProductCategoryBrandService.setCreateUser(currentUser.getAccount());
			 bizProductCategoryBrandService.setCreateDate(new Date());	
			 lines+= bizProductCategoryBrandServiceService.insert(bizProductCategoryBrandService);
			 sysLogService.insertLog(38, "添加绑定信息："+BrandId+"-"+categoryId);
		 }
		return lines;
	}	
	
	@GetMapping("/updatecaninstall")
	@ResponseBody
	//@DbLoggable(describe="修改：更新品牌品类是否支持报装!")
	public Object updateCanInstall(@RequestParam Integer id,@RequestParam String canInstall) {	    
		SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();		 
		 BizProductCategoryBrandService bizProductCategoryBrandService=new BizProductCategoryBrandService();
		 bizProductCategoryBrandService.setUpdateUser(currentUser.getAccount());
		 bizProductCategoryBrandService.setUpdateDate(new Date());	
		 bizProductCategoryBrandService.setId(id);
		 bizProductCategoryBrandService.setCanInstall(canInstall);
		 sysLogService.insertLog(38, "修改是否报报装信息："+id+"-"+canInstall);
		return bizProductCategoryBrandServiceService.updateCanInstall(bizProductCategoryBrandService);
	}	
	@GetMapping("/updatecanrepair")
	@ResponseBody
	//@DbLoggable(describe="修改：更新品牌品类是否支持报修!")
	public Object updateCanRepair(@RequestParam Integer id,@RequestParam String canRepair) {	    
		SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();		 
		 BizProductCategoryBrandService bizProductCategoryBrandService=new BizProductCategoryBrandService();
		 bizProductCategoryBrandService.setUpdateUser(currentUser.getAccount());
		 bizProductCategoryBrandService.setUpdateDate(new Date());	
		 bizProductCategoryBrandService.setId(id);
		 bizProductCategoryBrandService.setCanRepair(canRepair);
		 sysLogService.insertLog(38, "修改是否报修信息："+id+"-"+canRepair);
		return bizProductCategoryBrandServiceService.updateCanRepair(bizProductCategoryBrandService);
	}
	@GetMapping("/deletecategorybrand")
	@ResponseBody
	//@DbLoggable(describe="删除：删除品牌品类关联信息!")
	public Object deleteCategoryBrand(@RequestParam String ids) {	    
		SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();		 
		sysLogService.insertLog(38, "删除品牌品类管理信息："+ids);
		return bizProductCategoryBrandServiceService.delete(ids);
	}
	//endregion
}
