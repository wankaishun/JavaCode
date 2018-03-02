package com.liqun.dto.lqoutput.searchbill;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="FPXT_OUTPUT")
public class FpxtOutput {
	@XmlElement(name="ID",required=true)
	private String id;//业务标识
	@XmlElement(name="CODE",required=true)
	private String code;//结果代码： 000000 成功，否则失败
	@XmlElement(name="MESS",required=true)
	private String mess;//描述信息
	@XmlElement(name="DATA",required=true)
	private String data;//返回pdf下载地址，经过 Base64 编码
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMess() {
		return mess;
	}
	public void setMess(String mess) {
		this.mess = mess;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

}
