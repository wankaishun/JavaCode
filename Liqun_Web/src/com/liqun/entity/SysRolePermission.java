package com.liqun.entity;

import java.awt.Image;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import org.bson.types.Binary;
import org.bson.types.Decimal128;


/***TableName：[DBTable("sys_role_permission")]***/
public final class  SysRolePermission  implements Serializable {
    /***private***/
    private Integer id;
    private Integer roleId;
    private Integer permissionId;
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

    public Integer getRoleId() {
    	return roleId;
    }
    public void setRoleId (Integer roleId) {
    	this.roleId = roleId ;
    }

    public Integer getPermissionId() {
    	return permissionId;
    }
    public void setPermissionId (Integer permissionId) {
    	this.permissionId = permissionId ;
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
		return "SysRolePermission [  id=" + id + ", roleId=" + roleId + ", permissionId=" + permissionId + ", reserve1=" + reserve1 + ", reserve2=" + reserve2 + ", reserve3=" + reserve3 + ", reserve4=" + reserve4 + ", reserve5=" + reserve5 + ", flag=" + flag + ", createUser=" + createUser + ", createDate=" + createDate + ", updateUser=" + updateUser + ", updateDate=" + updateDate + " ]";
	}
    
	public SysRolePermission(Integer id ,Integer roleId ,Integer permissionId ,String reserve1 ,String reserve2 ,String reserve3 ,String reserve4 ,String reserve5 ,String flag ,String createUser ,Date createDate ,String updateUser ,Date updateDate ) {
		super();
        this. id = id;
        this. roleId = roleId;
        this. permissionId = permissionId;
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

	public SysRolePermission() {
		super();
	}
}
