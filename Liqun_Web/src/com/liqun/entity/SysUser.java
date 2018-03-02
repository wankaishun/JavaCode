package com.liqun.entity;

import java.awt.Image;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import org.bson.types.Binary;
import org.bson.types.Decimal128;


/***TableName：[DBTable("sys_user")]***/
public final class  SysUser implements Serializable{
    /***private***/
    private Integer id;
    private String employeeNo;
    private String account;
    private String sex;
    private String mobile;
    private String password;
    private Integer roleId;
    private String roleName;
	private String email;
    private String realName;
    private String address;
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

    public String getEmployeeNo() {
    	return employeeNo;
    }
    public void setEmployeeNo (String employeeNo) {
    	this.employeeNo = employeeNo ;
    }

    public String getAccount() {
    	return account;
    }
    public void setAccount (String account) {
    	this.account = account ;
    }

    public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
    public String getSex() {
    	return sex;
    }
    public void setSex (String sex) {
    	this.sex = sex ;
    }

    public String getMobile() {
    	return mobile;
    }
    public void setMobile (String mobile) {
    	this.mobile = mobile ;
    }

    public String getPassword() {
    	return password;
    }
    public void setPassword (String password) {
    	this.password = password ;
    }

    public Integer getRoleId() {
    	return roleId;
    }
    public void setRoleId (Integer roleId) {
    	this.roleId = roleId ;
    }

    public String getEmail() {
    	return email;
    }
    public void setEmail (String email) {
    	this.email = email ;
    }

    public String getRealName() {
    	return realName;
    }
    public void setRealName (String realName) {
    	this.realName = realName ;
    }

    public String getAddress() {
    	return address;
    }
    public void setAddress (String address) {
    	this.address = address ;
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
		return "SysUser [  id=" + id + ", employeeNo=" + employeeNo + ", account=" + account + ", sex=" + sex + ", mobile=" + mobile + ", password=" + password + ", roleId=" + roleId + ", email=" + email + ", realName=" + realName + ", address=" + address + ", reserve1=" + reserve1 + ", reserve2=" + reserve2 + ", reserve3=" + reserve3 + ", reserve4=" + reserve4 + ", reserve5=" + reserve5 + ", flag=" + flag + ", createUser=" + createUser + ", createDate=" + createDate + ", updateUser=" + updateUser + ", updateDate=" + updateDate + " ]";
	}
    
	public SysUser(Integer id ,String employeeNo ,String account ,String sex ,String mobile ,String password ,Integer roleId ,String email ,String realName ,String address ,String reserve1 ,String reserve2 ,String reserve3 ,String reserve4 ,String reserve5 ,String flag ,String createUser ,Date createDate ,String updateUser ,Date updateDate ) {
		super();
        this. id = id;
        this. employeeNo = employeeNo;
        this. account = account;
        this. sex = sex;
        this. mobile = mobile;
        this. password = password;
        this. roleId = roleId;
        this. email = email;
        this. realName = realName;
        this. address = address;
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

	public SysUser() {
		super();
	}
}
