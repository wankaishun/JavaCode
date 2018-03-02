package com.liqun.dto.a9output;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="SUCCESS")
public class Success {
	@XmlElement(name="CODE",required=true)
	private String code;
	@XmlElement(name="ID",required=true)
	private String id;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMess() {
		return mess;
	}
	public void setMess(String mess) {
		this.mess = mess;
	}
	public String getDATABUF() {
		return DATABUF;
	}
	public void setDATABUF(String dATABUF) {
		DATABUF = dATABUF;
	}
	@XmlElement(name="MESS",required=true)
	private String mess;
	@XmlElement(name="DATABUF",required=true)
	private String DATABUF;
}
