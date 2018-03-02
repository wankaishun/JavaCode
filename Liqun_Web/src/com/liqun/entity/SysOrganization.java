package com.liqun.entity;

import java.util.Date;

public class SysOrganization {
    @Override
	public String toString() {
		return "SysOrganization [id=" + id + ", orgCode=" + orgCode + ", orgName=" + orgName + ", taxNumber="
				+ taxNumber + ", address=" + address + ", phoneNumber=" + phoneNumber + ", bankAccount=" + bankAccount
				+ ", ticketLimit=" + ticketLimit + ", parentId=" + parentId + ", isLeafNode=" + isLeafNode
				+ ", subsystemId=" + subsystemId + ", flag=" + flag + ", ticketHolder=" + ticketHolder + ", reviewer="
				+ reviewer + ", payee=" + payee + ", createUser=" + createUser + ", createTime=" + createTime
				+ ", updateUser=" + updateUser + ", updateTime=" + updateTime + "]";
	}

	private Integer id;

    private Integer orgCode;

    private String orgName;

    private Integer taxNumber;

    private String address;

    private Integer phoneNumber;

    private Integer bankAccount;

    private Double ticketLimit;

    private Integer parentId;

    private Byte isLeafNode;

    private Integer subsystemId;

    private Byte flag;

    private String ticketHolder;

    private String reviewer;

    private String payee;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(Integer orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public Integer getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(Integer taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Integer bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Double getTicketLimit() {
        return ticketLimit;
    }

    public void setTicketLimit(Double ticketLimit) {
        this.ticketLimit = ticketLimit;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Byte getIsLeafNode() {
        return isLeafNode;
    }

    public void setIsLeafNode(Byte isLeafNode) {
        this.isLeafNode = isLeafNode;
    }

    public Integer getSubsystemId() {
        return subsystemId;
    }

    public void setSubsystemId(Integer subsystemId) {
        this.subsystemId = subsystemId;
    }

    public Byte getFlag() {
        return flag;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }

    public String getTicketHolder() {
        return ticketHolder;
    }

    public void setTicketHolder(String ticketHolder) {
        this.ticketHolder = ticketHolder == null ? null : ticketHolder.trim();
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer == null ? null : reviewer.trim();
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee == null ? null : payee.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}