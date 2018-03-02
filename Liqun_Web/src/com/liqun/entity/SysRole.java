package com.liqun.entity;

import java.io.Serializable;
import java.util.Date;


/***TableName：[DBTable("sys_role")]***/
public final class  SysRole  implements Serializable {
    /***private***/
    private Integer roleId;
    private String roleCode;
    private String roleName;
    private String roleDesc;
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
    
    private String menuItems;
      
    public String getMenuItems() {
		return menuItems;
	}
	public void setMenuItems(String menuItems) {
		this.menuItems = menuItems;
	}
	/***public***/
    public Integer getRoleId() {
    	return roleId;
    }
    public void setRoleId (Integer roleId) {
    	this.roleId = roleId ;
    }

    public String getRoleCode() {
    	return roleCode;
    }
    public void setRoleCode (String roleCode) {
    	this.roleCode = roleCode ;
    }

    public String getRoleName() {
    	return roleName;
    }
    public void setRoleName (String roleName) {
    	this.roleName = roleName ;
    }

    public String getRoleDesc() {
    	return roleDesc;
    }
    public void setRoleDesc (String roleDesc) {
    	this.roleDesc = roleDesc ;
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
		return "SysRole [  roleId=" + roleId + ", roleCode=" + roleCode + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", reserve1=" + reserve1 + ", reserve2=" + reserve2 + ", reserve3=" + reserve3 + ", reserve4=" + reserve4 + ", reserve5=" + reserve5 + ", flag=" + flag + ", createUser=" + createUser + ", createDate=" + createDate + ", updateUser=" + updateUser + ", updateDate=" + updateDate + " ]";
	}
    
	public SysRole(Integer roleId ,String roleCode ,String roleName ,String roleDesc ,String reserve1 ,String reserve2 ,String reserve3 ,String reserve4 ,String reserve5 ,String flag ,String createUser ,Date createDate ,String updateUser ,Date updateDate ) {
		super();
        this. roleId = roleId;
        this. roleCode = roleCode;
        this. roleName = roleName;
        this. roleDesc = roleDesc;
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

	public SysRole() {
		super();
	}
}
