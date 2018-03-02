package com.liqun.dto.taxtuihui;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="OUTPUT")
public class OUTPUT implements Serializable {
	@XmlElement(name="RETURN",required=true)
	private RETURN returns;
	@XmlElement(name="FPJTH",required=true)
	private FPJTH fpjth;
	public RETURN getReturns() {
		return returns;
	}
	public void setReturns(RETURN returns) {
		this.returns = returns;
	}
	public FPJTH getFpjth() {
		return fpjth;
	}
	public void setFpjth(FPJTH fpjth) {
		this.fpjth = fpjth;
	}
	
}
