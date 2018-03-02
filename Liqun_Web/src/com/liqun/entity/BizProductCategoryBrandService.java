package com.liqun.entity;

import java.awt.Image;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import org.bson.types.Binary;
import org.bson.types.Decimal128;


/***TableName：[DBTable("biz_product_category_brand_service")]***/
public final class  BizProductCategoryBrandService  implements Serializable {
    /***private***/
    private Integer id;
    private Integer categoryId;
    private Integer brandId;
    private String canRepair;
    private String canInstall;
    private String reserve4;
    private String reserve5;
    private String flag;
    private String createUser;
    private Date createDate;
    private String updateUser;
    private Date updateDate;
      
    /***public***/
    public Integer getId() {
    	return id;
    }
    public void setId (Integer id) {
    	this.id = id ;
    }

    public Integer getcategoryId() {
    	return categoryId;
    }
    public void setcategoryId (Integer categoryId) {
    	this.categoryId = categoryId ;
    }

    public Integer getbrandId() {
    	return brandId;
    }
    public void setbrandId (Integer brandId) {
    	this.brandId = brandId ;
    }

    public String getCanRepair() {
    	return canRepair;
    }
    public void setCanRepair (String canRepair) {
    	this.canRepair = canRepair ;
    }

    public String getCanInstall() {
    	return canInstall;
    }
    public void setCanInstall (String canInstall) {
    	this.canInstall = canInstall ;
    }

    public String getReserve4() {
    	return reserve4;
    }
    public void setReserve4 (String reserve4) {
    	this.reserve4 = reserve4 ;
    }

    public String getReserve5() {
    	return reserve5;
    }
    public void setReserve5 (String reserve5) {
    	this.reserve5 = reserve5 ;
    }

    public String getFlag() {
    	return flag;
    }
    public void setFlag (String flag) {
    	this.flag = flag ;
    }

    public String getCreateUser() {
    	return createUser;
    }
    public void setCreateUser (String createUser) {
    	this.createUser = createUser ;
    }

    public Date getCreateDate() {
    	return createDate;
    }
    public void setCreateDate (Date createDate) {
    	this.createDate = createDate ;
    }

    public String getUpdateUser() {
    	return updateUser;
    }
    public void setUpdateUser (String updateUser) {
    	this.updateUser = updateUser ;
    }

    public Date getUpdateDate() {
    	return updateDate;
    }
    public void setUpdateDate (Date updateDate) {
    	this.updateDate = updateDate ;
    }
	@Override
	public String toString() {
		return "BizProductCategoryBrandService [id=" + id + ", categoryId="
				+ categoryId + ", brandId=" + brandId + ", canRepair="
				+ canRepair + ", canInstall=" + canInstall + ", reserve4="
				+ reserve4 + ", reserve5=" + reserve5 + ", flag=" + flag
				+ ", createUser=" + createUser + ", createDate=" + createDate
				+ ", updateUser=" + updateUser + ", updateDate=" + updateDate
				+ "]";
	}
	public BizProductCategoryBrandService() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BizProductCategoryBrandService(Integer id, Integer categoryId,
			Integer brandId, String canRepair, String canInstall,
			String reserve4, String reserve5, String flag, String createUser,
			Date createDate, String updateUser, Date updateDate) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.brandId = brandId;
		this.canRepair = canRepair;
		this.canInstall = canInstall;
		this.reserve4 = reserve4;
		this.reserve5 = reserve5;
		this.flag = flag;
		this.createUser = createUser;
		this.createDate = createDate;
		this.updateUser = updateUser;
		this.updateDate = updateDate;
	}
    
}
