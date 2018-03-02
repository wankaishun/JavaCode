package com.liqun.entity;

import java.io.Serializable;
import java.util.Date;


/***TableName：[DBTable("sys_log")]***/
public final class  SysLog  implements Serializable {
    /***private***/
    private Integer logId;
    private Integer userId;
    private Date operationTime;
    private Integer permissionId;
    private String ipAddress;
    private String content;
    private String flag;
    private String createUser;
    private Date createDate;
    private String updateUser;
    private Date updateDate;
      
    /***public***/
    public Integer getLogId() {
    	return logId;
    }
    public void setLogId (Integer logId) {
    	this.logId = logId ;
    }

    public Integer getUserId() {
    	return userId;
    }
    public void setUserId (Integer userId) {
    	this.userId = userId ;
    }

    public Date getOperationTime() {
    	return operationTime;
    }
    public void setOperationTime (Date operationTime) {
    	this.operationTime = operationTime ;
    }

    public Integer getPermissionId() {
    	return permissionId;
    }
    public void setPermissionId (Integer permissionId) {
    	this.permissionId = permissionId ;
    }

    public String getIpAddress() {
    	return ipAddress;
    }
    public void setIpAddress (String ipAddress) {
    	this.ipAddress = ipAddress ;
    }

    public String getContent() {
    	return content;
    }
    public void setContent (String content) {
    	this.content = content ;
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
		return "SysLog [  logId=" + logId + ", userId=" + userId + ", operationTime=" + operationTime + ", permissionId=" + permissionId + ", ipAddress=" + ipAddress + ", content=" + content + ", flag=" + flag +  ", createUser=" + createUser + ", createDate=" + createDate + ", updateUser=" + updateUser + ", updateDate=" + updateDate + " ]";
	}
    
	public SysLog(Integer logId ,Integer userId ,Date operationTime ,Integer permissionId ,String ipAddress ,String content ,String flag ,String reserve1 ,String reserve2 ,String reserve3 ,String reserve4 ,String reserve5 ,String createUser ,Date createDate ,String updateUser ,Date updateDate ) {
		super();
        this. logId = logId;
        this. userId = userId;
        this. operationTime = operationTime;
        this. permissionId = permissionId;
        this. ipAddress = ipAddress;
        this. content = content;
        this. flag = flag;
        this. createUser = createUser;
        this. createDate = createDate;
        this. updateUser = updateUser;
        this. updateDate = updateDate;
	}

	public SysLog() {
		super();
	}
}
