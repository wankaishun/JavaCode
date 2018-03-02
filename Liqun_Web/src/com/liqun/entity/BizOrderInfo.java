package com.liqun.entity;

import java.awt.Image;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import org.bson.types.Binary;
import org.bson.types.Decimal128;


/***TableName：[DBTable("biz_order_info")]***/
public final class  BizOrderInfo  implements Serializable {
    /***private***/
    private Integer id;
    private String orderNo;
    private String serviceType;
    private String orderStatus;
    private String productCategory;
    private String productBrand;
    private String productModel;
    private String contact;
    private String mobile;
    private String provinceId;
    private String cityId;
    private String countyId;
    private String address;
    private String remark;
    private String reserve1;
    private String reserve2;
    private String reserve3;
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

    public String getOrderNo() {
    	return orderNo;
    }
    public void setOrderNo (String orderNo) {
    	this.orderNo = orderNo ;
    }

    public String getServiceType() {
    	return serviceType;
    }
    public void setServiceType (String serviceType) {
    	this.serviceType = serviceType ;
    }

    public String getOrderStatus() {
    	return orderStatus;
    }
    public void setOrderStatus (String orderStatus) {
    	this.orderStatus = orderStatus ;
    }

    public String getProductCategory() {
    	return productCategory;
    }
    public void setProductCategory (String productCategory) {
    	this.productCategory = productCategory ;
    }

    public String getProductBrand() {
    	return productBrand;
    }
    public void setProductBrand (String productBrand) {
    	this.productBrand = productBrand ;
    }

    public String getProductModel() {
    	return productModel;
    }
    public void setProductModel (String productModel) {
    	this.productModel = productModel ;
    }

    public String getContact() {
    	return contact;
    }
    public void setContact (String contact) {
    	this.contact = contact ;
    }

    public String getMobile() {
    	return mobile;
    }
    public void setMobile (String mobile) {
    	this.mobile = mobile ;
    }

    public String getProvinceId() {
    	return provinceId;
    }
    public void setProvinceId (String provinceId) {
    	this.provinceId = provinceId ;
    }

    public String getCityId() {
    	return cityId;
    }
    public void setCityId (String cityId) {
    	this.cityId = cityId ;
    }

    public String getCountyId() {
    	return countyId;
    }
    public void setCountyId (String countyId) {
    	this.countyId = countyId ;
    }

    public String getAddress() {
    	return address;
    }
    public void setAddress (String address) {
    	this.address = address ;
    }

    public String getRemark() {
    	return remark;
    }
    public void setRemark (String remark) {
    	this.remark = remark ;
    }

    public String getReserve1() {
    	return reserve1;
    }
    public void setReserve1 (String reserve1) {
    	this.reserve1 = reserve1 ;
    }

    public String getReserve2() {
    	return reserve2;
    }
    public void setReserve2 (String reserve2) {
    	this.reserve2 = reserve2 ;
    }

    public String getReserve3() {
    	return reserve3;
    }
    public void setReserve3 (String reserve3) {
    	this.reserve3 = reserve3 ;
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

      
    /***Override***/
    @Override
     
	public String toString() {
		return "BizOrderInfo [  id=" + id + ", orderNo=" + orderNo + ", serviceType=" + serviceType + ", orderStatus=" + orderStatus + ", productCategory=" + productCategory + ", productBrand=" + productBrand + ", productModel=" + productModel + ", contact=" + contact + ", mobile=" + mobile + ", provinceId=" + provinceId + ", cityId=" + cityId + ", countyId=" + countyId + ", address=" + address + ", remark=" + remark + ", reserve1=" + reserve1 + ", reserve2=" + reserve2 + ", reserve3=" + reserve3 + ", reserve4=" + reserve4 + ", reserve5=" + reserve5 + ", flag=" + flag + ", createUser=" + createUser + ", createDate=" + createDate + ", updateUser=" + updateUser + ", updateDate=" + updateDate + " ]";
	}
    
	public BizOrderInfo(Integer id ,String orderNo ,String serviceType ,String orderStatus ,String productCategory ,String productBrand ,String productModel ,String contact ,String mobile ,String provinceId ,String cityId ,String countyId ,String address ,String remark ,String reserve1 ,String reserve2 ,String reserve3 ,String reserve4 ,String reserve5 ,String flag ,String createUser ,Date createDate ,String updateUser ,Date updateDate ) {
		super();
        this. id = id;
        this. orderNo = orderNo;
        this. serviceType = serviceType;
        this. orderStatus = orderStatus;
        this. productCategory = productCategory;
        this. productBrand = productBrand;
        this. productModel = productModel;
        this. contact = contact;
        this. mobile = mobile;
        this. provinceId = provinceId;
        this. cityId = cityId;
        this. countyId = countyId;
        this. address = address;
        this. remark = remark;
        this. reserve1 = reserve1;
        this. reserve2 = reserve2;
        this. reserve3 = reserve3;
        this. reserve4 = reserve4;
        this. reserve5 = reserve5;
        this. flag = flag;
        this. createUser = createUser;
        this. createDate = createDate;
        this. updateUser = updateUser;
        this. updateDate = updateDate;
	}

	public BizOrderInfo() {
		super();
	}
}
