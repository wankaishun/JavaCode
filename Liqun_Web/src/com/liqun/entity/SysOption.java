package com.liqun.entity;

import java.awt.Image;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import org.bson.types.Binary;
import org.bson.types.Decimal128;


/***TableName：[DBTable("sys_option")]***/
public final class  SysOption  implements Serializable {
    /***private***/
    private String optionNo;
    private String optionName;
    private String itemId;
    private String itemValue;
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
    public String getOptionNo() {
    	return optionNo;
    }
    public void setOptionNo (String optionNo) {
    	this.optionNo = optionNo ;
    }

    public String getOptionName() {
    	return optionName;
    }
    public void setOptionName (String optionName) {
    	this.optionName = optionName ;
    }

    public String getItemId() {
    	return itemId;
    }
    public void setItemId (String itemId) {
    	this.itemId = itemId ;
    }

    public String getItemValue() {
    	return itemValue;
    }
    public void setItemValue (String itemValue) {
    	this.itemValue = itemValue ;
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
		return "SysOption [  optionNo=" + optionNo + ", optionName=" + optionName + ", itemId=" + itemId + ", itemValue=" + itemValue + ", reserve1=" + reserve1 + ", reserve2=" + reserve2 + ", reserve3=" + reserve3 + ", reserve4=" + reserve4 + ", reserve5=" + reserve5 + ", flag=" + flag + ", createUser=" + createUser + ", createDate=" + createDate + ", updateUser=" + updateUser + ", updateDate=" + updateDate + " ]";
	}
    
	public SysOption(String optionNo ,String optionName ,String itemId ,String itemValue ,String reserve1 ,String reserve2 ,String reserve3 ,String reserve4 ,String reserve5 ,String flag ,String createUser ,Date createDate ,String updateUser ,Date updateDate ) {
		super();
        this. optionNo = optionNo;
        this. optionName = optionName;
        this. itemId = itemId;
        this. itemValue = itemValue;
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

	public SysOption() {
		super();
	}
}
