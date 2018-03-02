package com.liqun.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 利群传过来
 * @author wks
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="FPXT_INPUT")
public class FPXTInput {
	
	@XmlElement(name="ID",required=true)
	private String id;
	@XmlElement(name="DATA",required=true)
	private String data;
	@Override
	public String toString() {
		return "FPXTInput [id=" + id + ", data=" + data + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
