package com.liqun.entity;

import java.awt.Image;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import org.bson.types.Binary;
import org.bson.types.Decimal128;


/***TableName：[DBTable("biz_service_corps_info")]***/
public final class  BizServiceCorpsInfo  implements Serializable {
    /***private***/
    private Integer id;
    private String openId;
    private String memberName;
    private String mobile;
    private String provinceId;
    private String cityId;
    private String countyId;
    private String address;
    private String sex;
    private String email;
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

    public String getOpenId() {
    	return openId;
    }
    public void setOpenId (String openId) {
    	this.openId = openId ;
    }

    public String getMemberName() {
    	return memberName;
    }
    public void setMemberName (String memberName) {
    	this.memberName = memberName ;
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

    public String getSex() {
    	return sex;
    }
    public void setSex (String sex) {
    	this.sex = sex ;
    }

    public String getEmail() {
    	return email;
    }
    public void setEmail (String email) {
    	this.email = email ;
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
		return "BizServiceCorpsInfo [  id=" + id + ", openId=" + openId + ", memberName=" + memberName + ", mobile=" + mobile + ", provinceId=" + provinceId + ", cityId=" + cityId + ", countyId=" + countyId + ", address=" + address + ", sex=" + sex + ", email=" + email + ", reserve1=" + reserve1 + ", reserve2=" + reserve2 + ", reserve3=" + reserve3 + ", reserve4=" + reserve4 + ", reserve5=" + reserve5 + ", flag=" + flag + ", createUser=" + createUser + ", createDate=" + createDate + ", updateUser=" + updateUser + ", updateDate=" + updateDate + " ]";
	}
    
	public BizServiceCorpsInfo(Integer id ,String openId ,String memberName ,String mobile ,String provinceId ,String cityId ,String countyId ,String address ,String sex ,String email ,String reserve1 ,String reserve2 ,String reserve3 ,String reserve4 ,String reserve5 ,String flag ,String createUser ,Date createDate ,String updateUser ,Date updateDate ) {
		super();
        this. id = id;
        this. openId = openId;
        this. memberName = memberName;
        this. mobile = mobile;
        this. provinceId = provinceId;
        this. cityId = cityId;
        this. countyId = countyId;
        this. address = address;
        this. sex = sex;
        this. email = email;
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

	public BizServiceCorpsInfo() {
		super();
	}
}
