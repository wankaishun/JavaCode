package com.liqun.entity;

import java.io.Serializable;

/**
 * 日志查询实体
 * @author LINN
 *
 */
@SuppressWarnings("serial")
public final class SysLogInfo implements Serializable {
	/**
	 * 操作用户
	 */
	private String realname;
	/**
	 * 创建时间开始
	 */
	private String starttime;
	/**
	 * 创建时间终止
	 */
	private String endtime;
	/**
	 * 操作时间
	 */
	private String operationtime;
	/**
	 * 使用功能
	 */
	private String permissionname;
	/**
	 * 日志内容
	 */
	private String content;
	/**
	 * IP地址
	 */
	private String ipaddress;

	/**
	 * 获取操作用户
	 * 
	 * @return 操作用户
	 */
	public String getRealname() {
		return realname;
	}

	/**
	 * 设置操作用户
	 * 
	 * @param realname
	 *            操作用户
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
	 * 获取创建开始时间
	 * 
	 * @return 创建开始时间
	 */
	public String getStarttime() {
		return starttime;
	}

	/**
	 * 设置创建开始时间
	 * 
	 * @param starttime
	 *            创建开始时间
	 */
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	/**
	 * 获取创建结束时间
	 * 
	 * @return 创建结束时间
	 */
	public String getEndtime() {
		return endtime;
	}

	/**
	 * 设置创建结束时间
	 * 
	 * @param endtime
	 *            操作结束时间
	 */
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	/**
	 * 获取操作时间
	 * 
	 * @return 操作时间
	 */
	public String getOperationtime() {
		return operationtime;
	}

	/**
	 * 设置操作时间
	 * 
	 * @param operationtime
	 *            操作时间
	 */
	public void setOperationtime(String operationtime) {
		this.operationtime = operationtime;
	}

	/**
	 * 获取使用功能
	 * 
	 * @return 使用功能
	 */
	public String getPermissionname() {
		return permissionname;
	}

	/**
	 * 设置使用功能
	 * 
	 * @param permissionname
	 *            使用功能
	 */
	public void setPermissionname(String permissionname) {
		this.permissionname = permissionname;
	}

	/**
	 * 获取日志内容
	 * 
	 * @return 日志内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置日志内容
	 * 
	 * @param content
	 *            日志内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取IP地址
	 * 
	 * @return IP地址
	 */
	public String getIpaddress() {
		return ipaddress;
	}

	/**
	 * 设置IP地址
	 * 
	 * @param ipaddress
	 *            IP地址
	 */
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
}
