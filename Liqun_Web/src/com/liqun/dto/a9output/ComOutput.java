package com.liqun.dto.a9output;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="COM_OUTPUT")
public class ComOutput {
	@Override
	public String toString() {
		return "ComOutput [id=" + id + ", code=" + code + ", mess=" + mess + ", data=" + data + "]";
	}
	@XmlElement(name="ID",required=true)
	private String id;
	@XmlElement(name="CODE",required=true)
	private String code;
	@XmlElement(name="MESS",required=true)
	private String mess;
	@XmlElement(name="DATA",required=true)
	private String data;
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
