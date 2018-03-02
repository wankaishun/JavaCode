package com.liqun.entity;

import java.io.Serializable;
import java.util.Date;

public final class MstRegion  implements Serializable {

	private Integer id;
	private Integer parentId;
	private String regionName;
	private String parentPath;
	private String firstLetter;
	private Integer regionType;
	private String shippingTime;
	private Integer visible;
	private String rowId;

	private Integer testNum;
	private Integer standardRegionId;
	private String code;
	
	private Integer activeFlag;
	private Integer hasRead;
	private Integer isSupportCod;
	private String addTime;
	
	private Date modified;
	private String zipCode;
	private Integer receivingTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getParentPath() {
		return parentPath;
	}
	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}
	public String getFirstLetter() {
		return firstLetter;
	}
	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}
	public Integer getRegionType() {
		return regionType;
	}
	public void setRegionType(Integer regionType) {
		this.regionType = regionType;
	}
	public String getShippingTime() {
		return shippingTime;
	}
	public void setShippingTime(String shippingTime) {
		this.shippingTime = shippingTime;
	}
	public Integer getVisible() {
		return visible;
	}
	public void setVisible(Integer visible) {
		this.visible = visible;
	}
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public Integer getTestNum() {
		return testNum;
	}
	public void setTestNum(Integer testNum) {
		this.testNum = testNum;
	}
	public Integer getStandardRegionId() {
		return standardRegionId;
	}
	public void setStandardRegionId(Integer standardRegionId) {
		this.standardRegionId = standardRegionId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Integer getHasRead() {
		return hasRead;
	}
	public void setHasRead(Integer hasRead) {
		this.hasRead = hasRead;
	}
	public Integer getIsSupportCod() {
		return isSupportCod;
	}
	public void setIsSupportCod(Integer isSupportCod) {
		this.isSupportCod = isSupportCod;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public Integer getReceivingTime() {
		return receivingTime;
	}
	public void setReceivingTime(Integer receivingTime) {
		this.receivingTime = receivingTime;
	}
	@Override
	public String toString() {
		return "MstRegion [id=" + id + ", regionName=" + regionName + ", code=" + code + "]";
	}
	
	

}
