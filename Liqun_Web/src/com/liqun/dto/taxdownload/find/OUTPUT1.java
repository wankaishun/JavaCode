package com.liqun.dto.taxdownload.find;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="OUTPUT")
public class OUTPUT1 implements Serializable {
	@XmlElement(name="RETURN",required=true)
	private RETURN returns;
	@XmlElement(name="FPJXZ",required=true)
	private DATA data;
	public RETURN getReturns() {
		return returns;
	}
	public void setReturns(RETURN returns) {
		this.returns = returns;
	}
	public DATA getData() {
		return data;
	}
	public void setData(DATA data) {
		this.data = data;
	}

	
}
