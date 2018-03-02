package com.liqun.dto.taxdownload;

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
	@XmlElement(name="FPJXZ",required=true)
	private FPJXZ fpjxz;
	public RETURN getReturns() {
		return returns;
	}
	public void setReturns(RETURN returns) {
		this.returns = returns;
	}
	public FPJXZ getFpjxz() {
		return fpjxz;
	}
	public void setFpjxz(FPJXZ fpjxz) {
		this.fpjxz = fpjxz;
	}
	
}
