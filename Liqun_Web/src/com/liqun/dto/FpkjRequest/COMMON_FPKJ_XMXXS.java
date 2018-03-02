package com.liqun.dto.FpkjRequest;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="COMMON_FPKJ_XMXXS")
public class COMMON_FPKJ_XMXXS implements Serializable {

	private static final long serialVersionUID = 1L;
	@XmlAttribute(name = "class")
	private String classes;
	@XmlAttribute(name = "size")
	private String size;
	
	private  COMMON_FPKJ_XMXX commonFPJKXMXX;
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public COMMON_FPKJ_XMXX getCommonFPJKXMXX() {
		return commonFPJKXMXX;
	}
	public void setCommonFPJKXMXX(COMMON_FPKJ_XMXX commonFPJKXMXX) {
		this.commonFPJKXMXX = commonFPJKXMXX;
	}
	@Override
	public String toString() {
		return "COMMON_FPKJ_XMXXS [classes=" + classes + ", size=" + size + ", commonFPJKXMXX=" + commonFPJKXMXX + "]";
	}
	
	
}
